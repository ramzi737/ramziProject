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
    AppDataBase db = AppDataBase.getDB(application);
    taskQuery = db.getMyTaskQuery();
    allTasks = taskQuery.getAllTasks();

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
public LiveData<List<myTask>> getTaskById(long taskId){
    return taskQuery.getAllTaskOrederByid(taskId);
}
/**
 * البحث عن المهام التي تتطابق عناوينها مع النص المدخل.
 *
 * @param title النص أو الكلمة المراد البحث عنها في العنوان.
 * @return قائمة المهام المطابقة.
 */
public LiveData<List<myTask>> getTasksByTitle(String title) {
    return taskQuery.getTasksByTitle(title);
}
/**
 * البحث عن المهام التي تتطابق أوصافها مع النص المدخل.
 *
 * @param description النص أو الكلمة المراد البحث عنها في الوصف.
 * @return قائمة المهام المطابقة.
 */
public LiveData<List<myTask>> getTasksByDescription(String description) {
    return taskQuery.getTasksByDescription(description);
}
/**
 * جلب المهام المفلترة حسب مستوى الأولوية.
 *
 * @param priority مستوى الأولوية المطلوب.
 * @return قائمة المهام ذات الأولوية المحددة.
 */
public LiveData<List<myTask>> getTasksByPriority(int priority) {
    return taskQuery.getTasksByPriority(priority);
}
/**
 * جلب المهام الخاصة بمستخدم معين والتي تتطابق مع عنوان معين.
 *
 * @param userId معرف المستخدم.
 * @param title  عنوان المهمة للبحث.
 * @return قائمة المهام المطابقة.
 */
public LiveData<List<myTask>> getTasksByUserIdAndTitle(long userId, String title) {
    return taskQuery.getTasksByUserIdAndTitle(userId, title);
}
/**
 * إدراج مهمة أو عدة مهمات جديدة في قاعدة البيانات.
 *
 * @param tasks المهام المراد إضافتها.
 */
    public void insert(myTask... tasks) {
        taskQuery.insertTask(tasks);
    }
    /**
     * تحديث مهمة أو عدة مهمات في قاعدة البيانات.
     *
     * @param tasks المهام المراد تحديثها.
     */
    public void update(myTask... tasks) {
        taskQuery.updateTask(tasks);
    }
    /**
     * تحديث بيانات مهمة محددة (العنوان، الوصف، والأولوية) باستخدام معرف المهمة.
     *
     * @param taskId      معرف المهمة المراد تعديلها.
     * @param title       العنوان الجديد.
     * @param description الوصف الجديد.
     * @param priority    الأولوية الجديدة.
     */
    public void updateTask(long taskId, String title, String description, int priority) {
        taskQuery.updateTask(taskId, title, description, priority);
    }
    /**
     * حذف مهمة أو عدة مهمات من قاعدة البيانات.
     *
     * @param tasks المهام المراد حذفها.
     */
    public void delete(myTask... tasks) {
        taskQuery.deleteTask(tasks);
    }
    /**
     * حذف مهمة محددة باستخدام معرفها الفريد (Task ID).
     *
     * @param taskId معرف المهمة المراد حذفها.
     */
    public void deleteTaskById(long taskId) {
        taskQuery.deleteTask(taskId);
    }
}






