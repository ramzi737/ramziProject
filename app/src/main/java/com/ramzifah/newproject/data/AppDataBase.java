package com.ramzifah.newproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ramzifah.newproject.data.MySubjectTable.MySubject;
import com.ramzifah.newproject.data.MySubjectTable.mySubjectQuery;
import com.ramzifah.newproject.data.MyTaskTable.myTask;
import com.ramzifah.newproject.data.MyTaskTable.myTaskQuery;
import com.ramzifah.newproject.data.MyTaskTable.myTask;
import com.ramzifah.newproject.data.MyUserTable.MyUser;
import com.ramzifah.newproject.data.MyUserTable.myUserQuery;



    /**
     * الفئة المسؤولة عن بناء قاعدة البيانات بكل جداولها
     * وتوفر لنا كائن للتعامل مع قاعدة البيانات
     */
    @Database(entities = {MyUser.class, MySubject.class, myTask.class}, version = 1)
    public abstract class AppDataBase extends RoomDatabase {

        /**
         * كائن للتعامل مع قاعدة البيانات
         */
        private static AppDataBase db;

        /**
         * يعيد كائن لعمليات جدول المستعملين
         * @return
         */
        public abstract myUserQuery getMyUserQuery();

        /**
         * يعيد كائن لعمليات جدول الموضيع
         * @return
         */
        public abstract mySubjectQuery getMySubjectQuery();

        /**
         * يعيد كائن لعمليات جدول المهمات
         * @return
         */
        public abstract myTaskQuery getMyTaskQuery();

        /**
         * بناء قاعدة البيانات وإعادة كائن يؤشر عليها
         * @param context
         * @return
         */
        public static AppDataBase getDB(Context context) {
            if (db == null) {
                db = Room.databaseBuilder(context,
                AppDataBase.class,
                "samihDataBase") // اسم قاعدة البيانات
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
            }
            return db;
        }
    }

