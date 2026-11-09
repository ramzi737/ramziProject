package com.ramzifah.newproject.model.MySubjectTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * فئة (Entity) تُمثل جدول المواضيع (Subjects Table) في قاعدة بيانات Room.
 */
@Entity // تحديد أن هذا الكلاس يمثل جدولاً في قاعدة البيانات
public class MySubject {
    @PrimaryKey(autoGenerate = true) // تحديد هذا الحقل كمفتاح رئيسي (Primary Key) مع توليد رقمه تلقائياً
    public long key_id;

    public String title; // عنوان الموضوع

    /**
     * إرجاع عنوان الموضوع.
     */
    public String getTitle() {
        return title;
    }

    /**
     * إرجاع المعرف الفريد للموضوع (ID).
     */
    public long getKey_id() {
        return key_id;
    }

    /**
     * تعيين المعرف الفريد للموضوع (ID).
     */
    public void setKey_id(long key_id) {
        this.key_id = key_id;
    }

    /**
     * تعيين عنوان الموضوع.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
