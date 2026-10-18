package com.ramzifah.newproject.model.MyTaskTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface myTaskQuery {

    /**
     * اعادة جميع معطيات جدول المهمات
     *
     * @return قائمة من المهمات
     */
    @Query("SELECT * FROM myTask ORDER BY importance DESC")
     LiveData<List<myTask>> getAllTasks();



    /**
     * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
     *
     * @param userid_p رقم المستعمل
     * @return
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p ORDER BY time DESC")
    LiveData<List<myTask>> getAllTaskOrederByid(long userid_p);

    /**
     * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
     *
     * @param userid_p      رقم المستعمل
     * @param isCompleted_p هل تمت ام لا
     * @return قائمة مهمات
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
            "ORDER BY importance DESC")
    LiveData<List<myTask>> getAllTaskOrederBy(long userid_p, boolean isCompleted_p);

    /**
     * ادخال مهمات
     *
     * @param t مجموعة مهمات
     */
    @Insert
    void insertTask(myTask... t); // ثلاثة نقاط تعني مجموعة

    /**
     * تعديل المهممات
     *
     * @param tasks مجموعة مهمات للتعديل (التعديل حسب المفتاح الرئيسي)
     */
    @Update
    void updateTask(myTask... tasks);

    /**
     * حذف مهمة او مهمات
     *
     * @param tasks حذف المهمات (حسب المفتاح الرئيسي)
     */
    @Delete
    void deleteTask(myTask... tasks);

    /**
     *
     * @param kid
     */
    @Query("DELETE FROM MyTask WHERE keyId=:kid")
    void deleteTask(long kid);

//    /**
//     * استخراج جميع المهمات التابعة لرقم الموضوع
//     * @param key_id رقم الموضوع
//     * @return
//     */
//    @Query("DELETE FROM MyTask WHERE keyId=:kid")
//    LiveData<List<myTask>>  getTasksBySubjId(long key_id);

    LiveData<List<myTask>> getTasksByUserId(long userId);

    LiveData<List<myTask>> getTasksByTitle(String title);

    LiveData<List<myTask>> getTasksByDescription(String description);

    LiveData<List<myTask>> getTasksByPriority(int priority);

    LiveData<List<myTask>> getTasksByUserIdAndTitle(long userId, String title);
}

