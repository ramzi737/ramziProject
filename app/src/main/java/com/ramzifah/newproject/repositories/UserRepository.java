package com.ramzifah.newproject.repositories;

import android.app.Application;

import com.ramzifah.newproject.model.AppDataBase;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.model.MyUserTable.myUserQuery;

import java.util.List;

// فئة مستودع البيانات الخاصة بالمستخدمين (Repository) للتعامل مع قاعدة البيانات بعيداً عن الواجهات
public class UserRepository {
    private myUserQuery userQuery; // كائن واجهة الاستعلامات (DAO) لتنفيذ عمليات جدول المستخدمين

    /**
     * منشئ الكلاس (Constructor).
     * يقوم بتهيئة قاعدة البيانات واسترجاع كائن الـ DAO الخاص بالمستعملين.
     *
     * @param application تطبيق أندرويد
     */
    public UserRepository(Application application) {
        // جلب كائن قاعدة البيانات الأساسي وتمرير السياق (Application Context)
        AppDataBase db = AppDataBase.getDB(application);
        
        // الحصول على واجهة الاستعلامات الخاصة بالمستخدمين (myUserQuery)
        userQuery = db.getMyUserQuery();
    }

    /**
     * استخراج جميع المستعملين الموجودين في قاعدة البيانات.
     *
     * @return قائمة بجميع المستعملين.
     */
    public List<MyUser> getAllUsers() {
        // استدعاء دالة الاستعلام لجلب كل المستخدمين
        return userQuery.getAll();
    }

    /**
     * استخراج مستعملين حسب معرفاتهم (User IDs).
     *
     * @param userIds مصفوفة معرفات المستعملين.
     * @return قائمة المستعملين المطابقين.
     */
    public List<MyUser> loadAllByIds(int[] userIds) {
        // استدعاء دالة الاستعلام لجلب المستخدمين حسب مصفوفة الـ IDs
        return userQuery.loadAllByIds(userIds);
    }

    /**
     * التحقق من وجود مستعمل حسب البريد الإلكتروني وكلمة السر.
     *
     * @param myEmail البريد الإلكتروني.
     * @param myPassw كلمة السر.
     * @return كائن المستعمل إذا تم العثور عليه، أو null خلاف ذلك.
     */
    public MyUser checkEmailPassw(String myEmail, String myPassw) {
        // فحص البريد وكلمة المرور لتسجيل الدخول
        return userQuery.checkEmailPassw(myEmail, myPassw);
    }

    /**
     * فحص ما إذا كان البريد الإلكتروني موجوداً مسبقاً في قاعدة البيانات.
     *
     * @param myEmail البريد الإلكتروني المراد فحص تطابقه.
     * @return كائن المستعمل إذا كان موجوداً، أو null خلاف ذلك.
     */
    public MyUser checkEmail(String myEmail) {
        // فحص هل البريد الإلكتروني مسجل من قبل (يُستخدم عادة عند التسجيل لمنع التكرار)
        return userQuery.checkEmail(myEmail);
    }

    /**
     * إدراج مستعمل جديد في قاعدة البيانات.
     *
     * @param myUser المستعمل المراد إضافته.
     */
    public void insert(MyUser myUser) {
        // إضافة مستعمل واحد لقاعدة البيانات
        userQuery.insert(myUser);
    }

    /**
     * إدراج مجموعة من المستعملين الجدد في قاعدة البيانات.
     *
     * @param users المستعملون المراد إضافتهم.
     */
    public void insertAll(MyUser... users) {
        // إضافة عدة مستعملين دفعة واحدة
        userQuery.insertAll(users);
    }

    /**
     * تحديث بيانات مستعمل أو مجموعة مستعملين في قاعدة البيانات.
     *
     * @param users المستعملون المراد تحديث بياناتهم.
     */
    public void update(MyUser... users) {
        // تحديث بيانات مستعمل أو قائمة مستعملين
        userQuery.update(users);
    }

    /**
     * حذف مستعمل معين من قاعدة البيانات.
     *
     * @param user المستعمل المراد حذفه.
     */
    public void delete(MyUser user) {
        // حذف مستعمل معين باستخدام كائن المستخدم
        userQuery.delete(user);
    }

    /**
     * حذف مستعمل محدد باستخدام رقمه المميز (ID).
     *
     * @param id رقم المستعمل المميز.
     */
    public void delete(int id) {
        // حذف مستعمل محدد بناءً على رقم الـ ID الخاص به
        userQuery.delete(id);
    }

    public MyUser login(String email, String password) {
        return userQuery.checkEmailPassw(email , password);

    }
}
