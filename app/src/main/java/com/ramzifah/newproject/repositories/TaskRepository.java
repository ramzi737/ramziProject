package com.ramzifah.newproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ramzifah.newproject.model.AppDataBase;
import com.ramzifah.newproject.model.MyTaskTable.myTask;
import com.ramzifah.newproject.model.MyTaskTable.myTaskQuery;

import java.util.List;

public class TaskRepository
{
    private myTaskQuery taskQuery;//واجهة الاستعلامات
    private LiveData<List<myTask>> allTasks;//مبنى معطيات يحوي جميع المهلام المستخرجة
    /**
     * منشئ الكلاس (Constructor).
     * يقوم بتهيئة قاعدة البيانات واسترجاع كائن الـ DAO الخاص بالمهام.
     *
     */
public TaskRepository(Application application){
    AppDataBase dp=AppDataBase.getDb(application)
            myTaskQuery.getAllTasks();
}
    /**
     *LiveData جلب جميع المهام الموجودة في قاعدة البيانات كـ.
     *
     * @return قائمة بجميع المهام المحدثة تلقائياً.
     */
public LiveData<List<myTask>>getAllTasks(){
    return allTasks;
}
    /**
     * جلب المهام المرتبطة بمعرف مستخدم معين (User ID).
     *
     * @param userId معرف المستخدم المراد جلب مهامه.
     * @return قائمة المهام الخاصة بالمستخدم المحدد.
     */
public LiveData<List<myTask>>getTasksByUserId(long userId){
    return taskQuery.getTasksByUserId(userId);
}
    /**
     * جلب مهمة معينة بناءً على معرفها الفريد (Task ID).
     *
     * @param taskId معرف المهمة الفريد.
     * @return كائن المهمة المطلوب.
     */
public LiveData
}

