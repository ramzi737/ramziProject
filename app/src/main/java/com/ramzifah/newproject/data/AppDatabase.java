package com.ramzifah.newproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ramzifah.newproject.data.MySubjectTable.MySubject;
import com.ramzifah.newproject.data.MySubjectTable.MySubjectQuery;
import com.ramzifah.newproject.data.MyTaskTable.MyTask;
import com.ramzifah.newproject.data.MyTaskTable.MyTaskQuery;
import com.ramzifah.newproject.data.MyUserTable.MyUser;
import com.ramzifah.newproject.data.MyUserTable.MyUserQuery;

public class AppDatabase {
    @Database(entities = {MyUser.class, MySubject.class, MyTask.class}, version = 1)[cite: 6]
    /**
     * الفئة المسؤولة عن بناء قاعدة البيانات بكل جداولها
     * وتوفر لنا كائن للتعامل مع قاعدة البيانات
     */
    public abstract class AppDataBase extends RoomDatabase {[cite: 6]

        /**
         * كائن للتعامل مع قاعدة البيانات
         */
        private static AppDataBase db;[cite: 6]

        /**
         * يعيد كائن لعمليات جدول المستعملين
         * @return
         */
        public abstract MyUserQuery getMyUserQuery();[cite: 6]

        /**
         * يعيد كائن لعمليات جدول الموضيع
         * @return
         */
        public abstract MySubjectQuery getMySubjectQuery();[cite: 6]

        /**
         * يعيد كائن لعمليات جدول المهمات
         * @return
         */
        public abstract MyTaskQuery getMyTaskQuery();[cite: 6]

        /**
         * بناء قاعدة البيانات وإعادة كائن يؤشر عليها
         * @param context
         * @return
         */
        public static AppDataBase getDB(Context context) {[cite: 6]
            if (db == null) {[cite: 6]
                db = Room.databaseBuilder(context,[cite: 6]
                AppDataBase.class,[cite: 6]
                "samihDataBase") // اسم قاعدة البيانات[cite: 6]
                    .fallbackToDestructiveMigration()[cite: 6]
                    .allowMainThreadQueries()[cite: 6]
                    .build();[cite: 6]
            }
            return db;[cite: 6]
        }
    }
}
