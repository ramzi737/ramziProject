package com.ramzifah.newproject.model.MySubjectTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * واجهة الاستعلامات (DAO) الخاصة بجدول المواضيع (Subjects Table).
 * تحتوي على الدوال والعمليات للتواصل مع قاعدة البيانات (استعلام، إدخال، تعديل، حذف).
 */
@Dao // تحديد أن هذه الواجهة هي DAO (Data Access Object) لتعمل مع Room
public interface mySubjectQuery {
    /**
     * إعادة جميع معطيات جدول المواضيع.
     * 
     * @return قائمة بجميع المواضيع.
     */
    @Query("SELECT * FROM MySubject")
    List<MySubject> getAllSubjects();

    /**
     * إدراج موضوع أو مجموعة مواضيع جديدة في قاعدة البيانات.
     * 
     * @param s المواضيع المراد إضافتها (ثلاث نقاط تعني استقبال عنصر واحد أو مصفوفة/مجموعة).
     */
    @Insert
    void insert(MySubject... s);

    /**
     * تعديل بيانات موضوع أو مجموعة مواضيع في قاعدة البيانات.
     * 
     * @param s المواضيع المراد تحديثها.
     */
    @Update
    void update(MySubject... s);

    /**
     * حذف موضوع أو مجموعة مواضيع من قاعدة البيانات (حسب المفتاح الرئيسي للكائن).
     * 
     * @param s المواضيع المراد حذفها.
     */
    @Delete
    void deleteTask(MySubject... s);

    /**
     * حذف موضوع محدد باستخدام رقمه المميز (ID).
     * 
     * @param keyid رقم الموضوع المميز المراد حذفه.
     */
    @Query("DELETE FROM MySubject WHERE key_id=:keyid")
    void delete(long keyid);

    /**
     * البحث عن موضوع معين والتحقق من وجوده بناءً على عنوانه (Title).
     * 
     * @param sub عنوان الموضوع المراد البحث عنه.
     * @return كائن الموضوع المطابق إذا وجد.
     */
    @Query("SELECT * From MySubject WHERE title=:sub")
    MySubject checkSubject(String sub);
}
