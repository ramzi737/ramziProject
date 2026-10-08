package com.ramzifah.newproject.model.MyTaskTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class myTask
{
    @PrimaryKey(autoGenerate = true)
    /** رقم المهمة */
    public long keyId;

    /** درجة الاهمية 1-5 */
    public int importance;

    /** عنوان قصير */
    public String shortTitle;

    /** نص المهمة */
    public String text;

    /** زمن بناء المهمة */
    public long time;

    /** هل تمت المهمة */
    public boolean isCompleted;

    /** رقم موضوع المهمة */
    public long subjId;

    /** رقم المستعمل الذي اضاف المهمة */
    public long userId;
}
