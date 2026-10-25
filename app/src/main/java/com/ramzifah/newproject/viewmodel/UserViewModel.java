package com.ramzifah.newproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.repositories.UserRepository;

import java.util.List;

/**
 * نموذج عرض المستخدمين (UserViewModel) مسئول عن تجهيز وإدارة البيانات المتعلقة بالمستخدمين (MyUser) لصالح واجهات المستخدم (Views/Activities).
 * يقوم بالاحتفاظ بالبيانات وتجاوز عمليات إعادة إنشاء النشاط (Configuration Changes) مثل دوران الشاشة،
 * ويتعامل مع {@link UserRepository} لتنفيذ العمليات المختلفة.
 */
public class UserViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final List<MyUser> allUsers;

    /**
     * منشئ الكلاس (Constructor).
     * يقوم بتهيئة المستودع (UserRepository) وجلب قائمة جميع المستخدمين الأولية.
     *
     * @param application سياق التطبيق (Application Context) الممرر للـ AndroidViewModel.
     */
    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    /**
     * جلب جميع المستخدمين.
     *
     * @return قائمة بجميع المستخدمين.
     */
    public List<MyUser> getAllUsers() {
        return allUsers;
    }

    /**
     * استخراج مستعملين حسب معرفاتهم (User IDs).
     *
     * @param userIds مصفوفة معرفات المستعملين.
     * @return قائمة المستعملين المطابقين.
     */
    public List<MyUser> loadAllByIds(int[] userIds) {
        return repository.loadAllByIds(userIds);
    }

    /**
     * التحقق من وجود مستعمل حسب البريد الإلكتروني وكلمة السر.
     *
     * @param myEmail البريد الإلكتروني.
     * @param myPassw كلمة السر.
     * @return كائن المستعمل إذا تم العثور عليه، أو null خلاف ذلك.
     */
    public MyUser checkEmailPassw(String myEmail, String myPassw) {
        return repository.checkEmailPassw(myEmail, myPassw);
    }

    /**
     * فحص ما إذا كان البريد الإلكتروني موجوداً مسبقاً في قاعدة البيانات.
     *
     * @param myEmail البريد الإلكتروني المراد فحص تطابقه.
     * @return كائن المستعمل إذا كان موجوداً، أو null خلاف ذلك.
     */
    public MyUser checkEmail(String myEmail) {
        return repository.checkEmail(myEmail);
    }

    /**
     * إدراج مستعمل جديد في قاعدة البيانات عبر المستودع.
     *
     * @param myUser المستعمل المراد إضافته.
     */
    public void insert(MyUser myUser) {
        repository.insert(myUser);
    }

    /**
     * إدراج مجموعة من المستعملين الجدد في قاعدة البيانات عبر المستودع.
     *
     * @param users المستعملون المراد إضافتهم.
     */
    public void insertAll(MyUser... users) {
        repository.insertAll(users);
    }

    /**
     * تحديث بيانات مستعمل أو مجموعة مستعملين في قاعدة البيانات عبر المستودع.
     *
     * @param users المستعملون المراد تحديث بياناتهم.
     */
    public void update(MyUser... users) {
        repository.update(users);
    }

    /**
     * حذف مستعمل معين من قاعدة البيانات عبر المستودع.
     *
     * @param user المستعمل المراد حذفه.
     */
    public void delete(MyUser user) {
        repository.delete(user);
    }

    /**
     * حذف مستعمل محدد باستخدام رقمه المميز (ID) عبر المستودع.
     *
     * @param id رقم المستعمل المميز.
     */
    public void delete(int id) {
        repository.delete(id);
    }
}
