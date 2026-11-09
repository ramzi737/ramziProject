package com.ramzifah.newproject.model.MyTaskTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * فئة (Entity) تُمثل جدول المهام (Tasks Table) في قاعدة بيانات Room.
 */
@Entity // تحديد أن هذا الكلاس يمثل جدولاً للمهام
public class myTask
{
    @PrimaryKey(autoGenerate = true) // تحديد هذا الحقل كمفتاح رئيسي مع توليد تلقائي للرقم
    public long keyId; // رقم المهمة الفريد (Task ID)

    public int priorty; // درجة الأهمية أو الأولوية للمهمة (مثلاً من 1 إلى 5)

    public String shortTitle; // عنوان قصير للمهمة

    public String text; // محتوى أو نص المهمة التفصيلي

    public long time; // زمن إنشاء المهمة (Timestamp)

    public boolean isCompleted; // هل تمت واكتملت المهمة أم لا (true/false)

    public long subjId; // رقم الموضوع المرتبط بهذه المهمة (Subject ID)

    public long userId; // رقم المستخدم الذي قام بإنشاء أو إضافة هذه المهمة (User ID)

    public String description; // وصف إضافي للمهمة
}
