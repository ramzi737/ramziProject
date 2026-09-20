package com.ramzifah.newproject.data.MyTaskTable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface MyTaskQuery {
    /**
     * واجهة استعلامات على جدول معطيات
     */
    @Dao
    public interface myTaskQuery {

        /**
         * اعادة جميع معطيات جدول المهمات
         * @return قائمة من المهمات
         */
        @Query("SELECT * FROM MyTask ORDER BY importance DESC")
        List<MyTask> getAllTasks();

        /**
         * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
         * @param userid_p رقم المستعمل
         * @return
         */
        @Query("SELECT * FROM MyTask WHERE userId=:userid_p ORDER BY time DESC")
        List<MyTask> getAllTaskOrederBy(long userid_p);

        /**
         * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
         * @param userid_p رقم المستعمل
         * @param isCompleted_p هل تمت ام لا
         * @return قائمة مهمات
         */
        @Query("SELECT * FROM MyTask WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
                "ORDER BY importance DESC")
        List<MyTask> getAllTaskOrederBy(long userid_p, boolean isCompleted_p);

        /**
         * ادخال مهمات
         * @param t مجموعة مهمات
         */
        @Insert
        void insertTask(MyTask... t); // ثلاثة نقاط تعني مجموعة

        /**
         * تعديل المهممات
         * @param tasks مجموعة مهمات للتعديل (التعديل حسب المفتاح الرئيسي)
         */
        @Update
        void updateTask(MyTask... tasks);

        /**
         * حذف مهمة او مهمات
         * @param tasks حذف المهمات (حسب المفتاح الرئيسي)
         */
        @Delete
        void deleteTask(MyTask... tasks);

        @Query("DELETE FROM MyTask WHERE keyId=:kid")
        void deleteTask(long kid);

        /**
         * استخراج جميع المهمات التابعة لرقم الموضوع
         * @param key_id رقم الموضوع
         * @return
         */
        @Query("SELECT * FROM MyTask WHERE subjId=:key_id " +
                "ORDER BY importance DESC")
        List<MyTask> getTasksBySubjId(long key_id);
    }
}
