package com.ramzifah.newproject.model.MyTaskTable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * واجهة الاستعلامات (DAO) الخاصة بجدول المهمات (Tasks Table).
 * تحتوي على جميع الاستعلامات والعمليات اللازمة للتعامل مع جدول المهام في قاعدة البيانات.
 */
@Dao // تحديد أن هذه الواجهة هي DAO (Data Access Object) لتعمل مع Room
public interface myTaskQuery {

    /**
     * إعادة جميع معطيات جدول المهمات مرتبة تنازلياً حسب الأولوية.
     *
     * @return قائمة من المهمات كـ LiveData لتحديث واجهة المستخدم تلقائياً.
     */
    @Query("SELECT * FROM myTask ORDER BY priorty DESC")
    LiveData<List<myTask>> getAllTasks();

    /**
     * إرجاع المهام الخاصة بمستخدم معين مرتبة تنازلياً حسب الوقت.
     *
     * @param userid_p رقم المستخدم (User ID).
     * @return قائمة مهام المستخدم.
     */
    @Query("SELECT * FROM myTask WHERE userId=:userid_p ORDER BY time DESC")
    LiveData<List<myTask>> getAllTaskOrederByid(long userid_p);

    /**
     * إرجاع المهام حسب المستخدم وحالة إنجازها، مرتبة تنازلياً حسب الأولوية.
     *
     * @param userid_p      رقم المستخدم.
     * @param isCompleted_p هل المهمة مكتملة أم لا (true/false).
     * @return قائمة المهمات المطابقة.
     */
    @Query("SELECT * FROM MyTask WHERE userId=:userid_p AND isCompleted=:isCompleted_p " +
            "ORDER BY priorty DESC")
    LiveData<List<myTask>> getAllTaskOrederBy(long userid_p, boolean isCompleted_p);

    /**
     * إدخال مهمة أو مجموعة مهمات جديدة في قاعدة البيانات.
     *
     * @param t مصفوفة أو مجموعة مهمات للإضافة.
     */
    @Insert
    void insertTask(myTask... t); // ثلاث نقاط تعني إمكانية إدخال عنصر واحد أو مجموعة عناصر

    /**
     * تحديث بيانات مهمة محددة (العنوان، الوصف، والأولوية) باستخدام معرف المهمة.
     *
     * @param taskId      رقم المهمة المراد تعديلها.
     * @param title       العنوان الجديد.
     * @param description الوصف الجديد.
     * @param priority    الأولوية الجديدة.
     */
    @Query("UPDATE MyTask SET shortTitle = :title, description = :description, priorty = :priority WHERE keyId = :taskId")
    void updateTask(long taskId, String title, String description, int priority);

    /**
     * حذف مهمة أو مجموعة مهمات من قاعدة البيانات (حسب المفتاح الرئيسي).
     *
     * @param tasks المهام المراد حذفها.
     */
    @Delete
    void deleteTask(myTask... tasks);

    /**
     * حذف مهمة محددة باستخدام معرفها الفريد (Task ID).
     *
     * @param kid رقم المهمة المراد حذفها.
     */
    @Query("DELETE FROM MyTask WHERE keyId=:kid")
    void deleteTask(long kid);

    /**
     * جلب جميع المهام التابعة لمستخدم معين.
     *
     * @param userId رقم المستخدم.
     * @return قائمة المهام الخاصة بالمستخدم.
     */
    @Query("SELECT * FROM myTask WHERE userId=:userId")
    LiveData<List<myTask>> getTasksByUserId(long userId);

    /**
     * البحث عن المهام التي تتطابق مع عنوان معين.
     *
     * @param title عنوان المهمة للبحث.
     * @return قائمة المهام المطابقة.
     */
    @Query("SELECT * FROM myTask WHERE shortTitle=:title")
    LiveData<List<myTask>> getTasksByTitle(String title);

    /**
     * البحث عن المهام التي تتطابق مع الوصف المدخل.
     *
     * @param description الوصف المراد البحث عنه.
     * @return قائمة المهام المطابقة.
     */
    @Query("SELECT * FROM myTask WHERE description=:description")
    LiveData<List<myTask>> getTasksByDescription(String description);

    /**
     * جلب المهام مفلترة حسب مستوى الأولوية.
     *
     * @param priority مستوى الأولوية المطلوبة.
     * @return قائمة المهام ذات الأولوية المحددة.
     */
    @Query("SELECT * FROM myTask WHERE priorty=:priority")
    LiveData<List<myTask>> getTasksByPriority(int priority);

    /**
     * جلب المهام الخاصة بمستخدم معين والتي تتطابق مع عنوان معين.
     *
     * @param userId رقم المستخدم.
     * @param title  عنوان المهمة.
     * @return قائمة المهام المطابقة.
     */
    @Query("SELECT * FROM myTask WHERE userId=:userId AND shortTitle=:title")
    LiveData<List<myTask>> getTasksByUserIdAndTitle(long userId, String title);

    /**
     * تحديث مجموعة من المهمات في قاعدة البيانات.
     *
     * @param tasks مصفوفة المهام المراد تحديثها.
     */
    @Update
    void updateTask(myTask[] tasks);
}
