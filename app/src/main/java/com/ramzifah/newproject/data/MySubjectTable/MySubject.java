package com.ramzifah.newproject.data.MySubjectTable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class MySubject {
    @Entity
    public class mySubject
    {
        @PrimaryKey(autoGenerate = true)
        public long key_id;
        public String title;
    }
}
