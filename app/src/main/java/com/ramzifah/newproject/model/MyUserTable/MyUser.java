package com.ramzifah.newproject.model.MyUserTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * فئة (Entity) تُمثل جدول المستخدمين (Users Table) في قاعدة بيانات Room.
 */
@Entity // تحديد أن هذا الكلاس يمثل جدولاً في قاعدة البيانات
public class MyUser
{
    @PrimaryKey(autoGenerate = true) // تحديد هذا الحقل كمفتاح رئيسي (Primary Key) مع توليد رقمه تلقائياً
    public long keyid;

    @ColumnInfo(name = "full_Name") // تخصيص اسم لعمود الاسم الكامل في قاعدة البيانات
    public String fullName;

    public String email; // البريد الإلكتروني (إذا لم يُحدد اسم للعمود، يصبح اسم الحقل هو اسم العمود)
    public String phone; // رقم الهاتف
    public String passw; // كلمة المرور

    @Override // دالة لطباعة محتويات كائن المستخدم بشكل نصي واضح عند الحاجة (للتصحيح والمتابعة)
    public String toString() {
        return "MyUser{" +
                "keyid=" + keyid +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}
