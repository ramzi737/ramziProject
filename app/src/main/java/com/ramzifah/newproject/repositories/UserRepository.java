package com.ramzifah.newproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ramzifah.newproject.model.AppDataBase;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.model.MyUserTable.myUserQuery;

import java.util.List;

// فئة مستودع البيانات الخاصة بالمستخدمين (Repository) للتعامل مع قاعدة البيانات بعيداً عن الواجهات
/**
 * مستودع المستخدمين (UserRepository)
 * الدور في هيكلية MVVM:
 * - هذه الفئة هي طبقة الوصول للبيانات (Data Layer) التي تتعامل مباشرة مع خدمات Firebase السحابية.
 * - تتولى جميع عمليات المصادقة (Firebase Authentication) وقراءة/كتابة بيانات المستخدمين (Firestore).
 * - تُرجع LiveData<MyUser> مباشرة بدون تغليف Resource، بحيث تصل النتيجة إلى الـ ViewModel
 *   ثم إلى الواجهة التي تراقبها عبر observe وتُحدّث الـ UI تلقائياً.
 * الخدمات المستخدمة:
 * 1. FirebaseAuth      → تسجيل الدخول وإنشاء الحسابات وإدارة الجلسة.
 * 2. FirebaseFirestore → تخزين بيانات المستخدمين في مجموعة "users".
 * العمليات المتاحة:
 * - registerUser   → تسجيل مستخدم جديد (Auth + Firestore).
 * - loginUser      → تسجيل دخول مستخدم موجود (Auth + Firestore).
 * - getCurrentUser → جلب المستخدم المسجل دخوله حالياً.
 * - logout         → تسجيل الخروج.
 */
public class UserRepository {
    /**
     * كائن Firebase Authentication المسؤول عن تسجيل الدخول وإنشاء الحسابات.
     */
    private final FirebaseAuth mAuth;
    /**
     * كائن Firebase Firestore المسؤول عن قراءة وكتابة بيانات المستخدمين في قاعدة البيانات السحابية.
     */
    private final FirebaseFirestore db;

    /**
     * المُنشئ (Constructor) - يهيئ اتصالات Firebase.
     *
     * @param application كائن التطبيق (Application) - غير مستخدم حالياً لكن يُحفظ ليتوافق مع نمط المستودعات.
     */
    public UserRepository(Application application) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    /** تسجيل مستخدم جديد في التطبيق */
    public LiveData<MyUser> registerUser(MyUser user) {// insert(MyUser user) او
        // المرحلة 1: إنشاء كائن LiveData قابل للتعديل لإرسال النتيجة لاحقاً
        MutableLiveData<MyUser> userLiveData = new MutableLiveData<>();
        // المرحلة 3: إنشاء حساب في Firebase Authentication بالبريد وكلمة المرور
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> { //هل تم التسجيل بنجاح
                    if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                        // المرحلة 4: استخراج UID الخاص بالمستخدم الجديد من Firebase Auth
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        user.setFirestoreUid(uid); // حفظ الـ UID داخل كائن المستخدم
                        // المرحلة 5: حفظ بيانات المستخدم كاملة في Firestore ضمن مجموعة "users"
                        // المستند يُنشأ بنفس الـ UID لضمان الربط بين Auth و Firestore
                        db.collection("users").document(uid).set(user)
                                .addOnSuccessListener(aVoid -> userLiveData.setValue(user)) // نجاح الحفظ
                                .addOnFailureListener(e -> userLiveData.setValue(null));     // فشل الحفظ
                    } else {
                        userLiveData.setValue(null);// فشل إنشاء الحساب في Authentication (بريد مكرر، كلمة مرور ضعيفة...)
                    }
                });
        // المرحلة 6: إرجاع LiveData فوراً - النتيجة ستصل لاحقاً عند اكتمال العمليات السحابية
        return userLiveData;
    }

    /**
     * تسجيل الدخول لمستخدم موجود
     */
    public LiveData<MyUser> login(String email, String password) {
        // المرحلة 1: إنشاء كائن LiveData قابل للتعديل لإرسال النتيجة لاحقاً
        MutableLiveData<MyUser> userLiveData = new MutableLiveData<>();
        // المرحلة 3: محاولة تسجيل الدخول في Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            //هل تم الدخول بنجاح؟
            if (task.isSuccessful() && mAuth.getCurrentUser() != null) {
                // المرحلة 4: استخراج UID الخاص بالمستخدم الذي سجل دخوله بنجاح
                String uid = mAuth.getCurrentUser().getUid();
                // المرحلة 5: جلب بيانات المستخدم الكاملة من Firestore
                // المستند يُقرأ من مجموعة "users" باستخدام الـ UID كمعرّف
                db.collection("users").document(uid).get()
                        .addOnCompleteListener(firestoreTask -> {
                            if (firestoreTask.isSuccessful() && firestoreTask.getResult() != null &&
                                    firestoreTask.getResult().exists()) {
                                // المرحلة 6أ: المستند موجود - تحويله إلى كائن MyUser وإرساله
                                MyUser fetchedUser = firestoreTask.getResult().toObject(MyUser.class);
                                userLiveData.setValue(fetchedUser);//هذه النتيجة ستصل لجواب ال observe
                            } else {
                                // المرحلة 6ب: فشل الجلب أو المستند غير موجود - إرسال null
                                userLiveData.setValue(null);//هذه النتيجة ستصل لجواب ال observe
                            }
                        });
            } else {
                // فشل تسجيل الدخول (بريد أو كلمة مرور غير صحيحة)
                userLiveData.setValue(null);
            }
        });

        // المرحلة 7: إرجاع LiveData فوراً - النتيجة ستصل لاحقاً عند اكتمال العمليات السحابية
        return userLiveData;
    }

    /**
     * جلب المستخدم الحالي المسجل دخوله في Firebase Authentication.
     *
     * @return كائن FirebaseUser الحالي، أو null إذا لم يسجل أي مستخدم دخوله.
     */
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    /**
     * تسجيل خروج المستخدم الحالي من Firebase Authentication.
     */
    public void logout() {
        mAuth.signOut();
    }
}
