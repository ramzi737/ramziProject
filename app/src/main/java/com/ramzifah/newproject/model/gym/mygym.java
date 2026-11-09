package com.ramzifah.newproject.model.gym;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * فئة (Entity) تُمثل بيانات التمرين أو الجيم (Gym Workout) في قاعدة البيانات.
 */
@Entity // تحديد أن هذا الكلاس يمثل جدولاً لمعطيات الجيم
public class mygym {
    @PrimaryKey(autoGenerate = true) // تحديد هذا الحقل كمفتاح رئيسي مع توليد تلقائي للرقم
    public long key_id;

    public int userage; // عمر المستخدم
    public int reps; // عدد التكرارات (Reps)
    public int sets; // عدد المجموعات (Sets)
    public int workoutDurationMinutes; // مدة التمرين بالدقائق
    public int restTimeSeconds; // وقت الراحة بين المجموعات بالثواني

    @Override // دالة لطباعة محتويات كائن التمرين بشكل نصي واضح للتصحيح والمتابعة
    public String toString() {
        return "mygym{" +
                "key_id=" + key_id +
                ", userage=" + userage +
                ", reps=" + reps +
                ", sets=" + sets +
                ", workoutDurationMinutes=" + workoutDurationMinutes +
                ", restTimeSeconds=" + restTimeSeconds +
                '}';
    }

    /** إرجاع المعرف الفريد للتمرين */
    public long getKey_id() {
        return key_id;
    }

    /** تعيين المعرف الفريد للتمرين */
    public void setKey_id(long key_id) {
        this.key_id = key_id;
    }

    /** إرجاع عمر المستخدم */
    public int getUserage() {
        return userage;
    }

    /** تعيين عمر المستخدم */
    public void setUserage(int userage) {
        this.userage = userage;
    }

    /** إرجاع عدد التكرارات */
    public int getReps() {
        return reps;
    }

    /** تعيين عدد التكرارات */
    public void setReps(int reps) {
        this.reps = reps;
    }

    /** إرجاع عدد المجموعات */
    public int getSets() {
        return sets;
    }

    /** تعيين عدد المجموعات */
    public void setSets(int sets) {
        this.sets = sets;
    }

    /** إرجاع مدة التمرين بالدقائق */
    public int getWorkoutDurationMinutes() {
        return workoutDurationMinutes;
    }

    /** تعيين مدة التمرين بالدقائق */
    public void setWorkoutDurationMinutes(int workoutDurationMinutes) {
        this.workoutDurationMinutes = workoutDurationMinutes;
    }

    /** إرجاع وقت الراحة بالثواني */
    public int getRestTimeSeconds() {
        return restTimeSeconds;
    }

    /** تعيين وقت الراحة بالثواني */
    public void setRestTimeSeconds(int restTimeSeconds) {
        this.restTimeSeconds = restTimeSeconds;
    }
}
