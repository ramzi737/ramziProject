package com.ramzifah.newproject.model.MyUserTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * واجهة الاستعلامات (DAO) الخاصة بجدول المستخدمين (Users Table).
 * تحتوي على الدوال والعمليات للتواصل مع قاعدة البيانات (استعلام، إدخال، تعديل، حذف).
 */
@Dao // لتحديد أن هذه الواجهة تحوي استعلامات على قاعدة البيانات (Data Access Object)
public interface myUserQuery
{
    /**
     * استخراج جميع المستخدمين المسجلين في قاعدة البيانات.
     * @return قائمة بجميع المستخدمين.
     */
    @Query("SELECT * FROM MyUser")
    List<MyUser> getAll();

    /**
     * استخراج المستخدمين حسب مصفوفة الأرقام المميزة (IDs).
     * @param userIds مصفوفة أرقام معرفات المستخدمين.
     * @return قائمة المستخدمين المطابقين.
     */
    @Query("SELECT * FROM MyUser WHERE keyid IN (:userIds)")
    List<MyUser> loadAllByIds(int[] userIds);

    /**
     * التحقق من وجود مستخدم والبحث عنه بناءً على البريد الإلكتروني وكلمة السر (لتسجيل الدخول).
     * @param myEmail البريد الإلكتروني.
     * @param myPassw كلمة السر.
     * @return كائن المستخدم إذا تطابق البريد وكلمة السر، أو null خلاف ذلك.
     */
    @Query("SELECT * FROM MyUser WHERE email = :myEmail AND passw = :myPassw LIMIT 1")
    MyUser checkEmailPassw(String myEmail, String myPassw);

    /**
     * فحص ما إذا كان البريد الإلكتروني موجوداً مسبقاً في قاعدة البيانات (لمنع التكرار عند التسجيل).
     * @param myEmail البريد الإلكتروني المراد فحص تطابقه.
     * @return كائن المستخدم إذا وجد البريد، أو null خلاف ذلك.
     */
    @Query("SELECT * FROM MyUser WHERE email = :myEmail LIMIT 1")
    MyUser checkEmail(String myEmail);

    /**
     * إضافة مستخدم أو مجموعة مستخدمين جدد دفعة واحدة.
     * @param users المستخدمون المراد إضافتهم.
     */
    @Insert
    void insertAll(MyUser... users);

    /**
     * حذف مستخدم معين باستخدام كائن المستخدم.
     * @param user المستخدم المراد حذفه.
     */
    @Delete
    void delete(MyUser user);

    /**
     * حذف مستخدم محدد باستخدام رقمه المميز (ID).
     * @param id رقم المستخدم المميز.
     */
    @Query("Delete From MyUser WHERE keyid=:id ")
    void delete(int id);

    /**
     * إضافة مستخدم واحد جديد إلى قاعدة البيانات.
     * @param myUser المستخدم المراد إضافته.
     */
    @Insert
    void insert(MyUser myUser);

    /**
     * تعديل وتحديث بيانات مستخدم أو قائمة مستخدمين في قاعدة البيانات.
     * @param values المستخدمون المراد تحديث بياناتهم.
     */
    @Update
    void update(MyUser... values);

}
