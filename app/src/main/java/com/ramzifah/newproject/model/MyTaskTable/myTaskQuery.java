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
    @Query("SELECT * FROM myTask ORDER BY priorty DESC")
    LiveData<List<myTask>> getAllTasks();


    /**
     * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
     *
     * @param userid_p رقم المستعمل
     * @return
     */
    @Query("SELECT * FROM myTask WHERE userId=:userid_p ORDER BY time DESC")
    LiveData<List<myTask>> getAllTaskOrederByid(long userid_p);

    /**
     * ارجاع المهمات حسب المستعمل واذا انتهت ام لا ومرتبة تنازليا حسب الاهمية
     *
     * @param userid_p      رقم المستعمل
     * @param isCompleted_p هل تمت ام لا
     * @return قائمة مهمات
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
            "ORDER BY priorty DESC")
    LiveData<List<myTask>> getAllTaskOrederBy(long userid_p, boolean isCompleted_p);

    /**
     * ادخال مهمات
     *
     * @param t مجموعة مهمات
     */
    @Insert
    void insertTask(myTask... t); // ثلاثة نقاط تعني مجموعة

    @Query("UPDATE MyTask SET shortTitle = :title, description = :description, priorty = :priority WHERE keyId = :taskId")
    void updateTask(long taskId, String title, String description, int priority);

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
@Query("select* FROM myTask WHERE userId=:userId")
    LiveData<List<myTask>> getTasksByUserId(long userId);
    @Query("select* FROM myTask WHERE shortTitle=:title")
    LiveData<List<myTask>> getTasksByTitle(String title);
    @Query("select* FROM myTask WHERE description=:description")
    LiveData<List<myTask>> getTasksByDescription(String description);
    @Query("select* FROM myTask WHERE  priorty=:priority")
    LiveData<List<myTask>> getTasksByPriority(int priority);
    @Query("select* FROM myTask WHERE userId=:userId and shortTitle=:title")
    LiveData<List<myTask>> getTasksByUserIdAndTitle(long userId, String title);

    /**
     *
     * @param tasks
     */
    @Update
    void updateTask(myTask[] tasks);
}

