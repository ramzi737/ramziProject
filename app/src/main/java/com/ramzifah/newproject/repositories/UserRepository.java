package com.ramzifah.newproject.repositories;

import android.app.Application;

import com.ramzifah.newproject.model.AppDataBase;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.model.MyUserTable.myUserQuery;

import java.util.List;

public class UserRepository {
    private myUserQuery userQuery;//واجهة الاستعلامات

    /**
     * منشئ الكلاس (Constructor).
     * يقوم بتهيئة قاعدة البيانات واسترجاع كائن الـ DAO الخاص بالمستعملين.
     *
     * @param application تطبيق أندرويد
     */
    public UserRepository(Application application) {
        AppDataBase db = AppDataBase.getDB(application);
        userQuery = db.getMyUserQuery();
    }

    /**
     * استخراج جميع المستعملين الموجودين في قاعدة البيانات.
     *
     * @return قائمة بجميع المستعملين.
     */
    public List<MyUser> getAllUsers() {
        return userQuery.getAll();
    }

    /**
     * استخراج مستعملين حسب معرفاتهم (User IDs).
     *
     * @param userIds مصفوفة معرفات المستعملين.
     * @return قائمة المستعملين المطابقين.
     */
    public List<MyUser> loadAllByIds(int[] userIds) {
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
        return userQuery.checkEmailPassw(myEmail, myPassw);
    }

    /**
     * فحص ما إذا كان البريد الإلكتروني موجوداً مسبقاً في قاعدة البيانات.
     *
     * @param myEmail البريد الإلكتروني المراد فحص تطابقه.
     * @return كائن المستعمل إذا كان موجوداً، أو null خلاف ذلك.
     */
    public MyUser checkEmail(String myEmail) {
        return userQuery.checkEmail(myEmail);
    }

    /**
     * إدراج مستعمل جديد في قاعدة البيانات.
     *
     * @param myUser المستعمل المراد إضافته.
     */
    public void insert(MyUser myUser) {
        userQuery.insert(myUser);
    }

    /**
     * إدراج مجموعة من المستعملين الجدد في قاعدة البيانات.
     *
     * @param users المستعملون المراد إضافتهم.
     */
    public void insertAll(MyUser... users) {
        userQuery.insertAll(users);
    }

    /**
     * تحديث بيانات مستعمل أو مجموعة مستعملين في قاعدة البيانات.
     *
     * @param users المستعملون المراد تحديث بياناتهم.
     */
    public void update(MyUser... users) {
        userQuery.update(users);
    }

    /**
     * حذف مستعمل معين من قاعدة البيانات.
     *
     * @param user المستعمل المراد حذفه.
     */
    public void delete(MyUser user) {
        userQuery.delete(user);
    }

    /**
     * حذف مستعمل محدد باستخدام رقمه المميز (ID).
     *
     * @param id رقم المستعمل المميز.
     */
    public void delete(int id) {
        userQuery.delete(id);
    }
}
