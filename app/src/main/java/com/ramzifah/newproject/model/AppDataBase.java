package com.ramzifah.newproject.model;

import android.app.Application;
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
import com.ramzifah.newproject.repositories.TaskRepository;


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

    public static AppDataBase getDb(Application application) {
    }

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

        public TaskRepository myTaskQuery() {
        }
    }

