package com.ramzifah.newproject.data.MyUserTable;

import androidx.room.Dao;

import java.util.List;

public interface MyUserQuery {
    /**
     * واجهة تحوي عمليات\دوال\استعلامات على قاعدة البيانات
     */
    @Dao//لتحديد ان الواجهة تحتوي استعلامات على قاعدة بيانات
    public interface myUserQuery
    {   //استخراج جميع المستعملين

        List<MyUser> getAll();
        // استخراج مستعمل حسب رقم المميز لهid

        List<MyUser> loadAllByIds(int[] userIds);
        //هل المستعمل موجود حسب الايميل وكلمة السر

        MyUser checkEmailPassw(String myEmail, String myPassw);
        //فحص هل الايميل موجود من قبل

        MyUser checkEmail(String myEmail);
        // اضافة مستعمل او مجموعة مستعملين

        void insertAll(MyUser... users);
        // حذف
        void delete(MyUser user);
        //حذف حسب الرقم المميز id

        void delete(int id);
        //اضافة مستعمل واحد

        void insert(MyUser myUser);
        //تعديل مستعمل او قائمة مستعملين

        void update(MyUser...values);
    }

}
