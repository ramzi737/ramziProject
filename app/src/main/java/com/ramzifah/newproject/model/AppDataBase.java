package com.ramzifah.newproject.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ramzifah.newproject.model.MySubjectTable.MySubject;
import com.ramzifah.newproject.model.MySubjectTable.mySubjectQuery;
import com.ramzifah.newproject.model.MyTaskTable.myTask;
import com.ramzifah.newproject.model.MyTaskTable.myTaskQuery;
import com.ramzifah.newproject.model.MyUserTable.MyUser;
import com.ramzifah.newproject.model.MyUserTable.myUserQuery;

/**
 * الفئة المسؤولة عن بناء وإدارة قاعدة البيانات (Room Database) بكل جداولها،
 * وتوفر لنا كائناً موحداً (Singleton) للتعامل مع قاعدة البيانات في التطبيق.
 */
@Database(entities = {MyUser.class, MySubject.class, myTask.class}, version = 2) // تحديد جداول قاعدة البيانات ورقم الإصدار
public abstract class AppDataBase extends RoomDatabase {

    /**
     * كائن فريد (Singleton) للتعامل مع قاعدة البيانات لمنع إنشاء عدة اتصالات في نفس الوقت.
     */
    private static AppDataBase db;

    /**
     * دالة مجردة تعيد كائن عمليات الاستعلام (DAO) الخاص بجدول المستخدمين.
     * @return كائن myUserQuery
     */
    public abstract myUserQuery getMyUserQuery();

    /**
     * دالة مجردة تعيد كائن عمليات الاستعلام (DAO) الخاص بجدول المواضيع.
     * @return كائن mySubjectQuery
     */
    public abstract mySubjectQuery getMySubjectQuery();

    /**
     * دالة مجردة تعيد كائن عمليات الاستعلام (DAO) الخاص بجدول المهمات.
     * @return كائن myTaskQuery
     */
    public abstract myTaskQuery getMyTaskQuery();

    /**
     * بناء قاعدة البيانات (Singleton Pattern) وإعادة كائن يؤشر عليها.
     * 
     * @param context سياق التطبيق
     * @return كائن قاعدة البيانات AppDataBase
     */
    public static AppDataBase getDB(Context context) {
        // التحقق مما إذا كانت قاعدة البيانات لم تُبنى بعد
        if (db == null) {
            db = Room.databaseBuilder(context,
                            AppDataBase.class,
                            "samihDataBase") // اسم ملف قاعدة البيانات على الجهاز
                    .fallbackToDestructiveMigration() // إعادة بناء قاعدة البيانات في حال تغير الإصدار تفادياً للانهيار
                    .allowMainThreadQueries() // السماح بتنفيذ الاستعلامات على الخيط الرئيسي (Main Thread) لتسهيل العمليات البسيطة
                    .build();
        }
        return db;
    }
}
