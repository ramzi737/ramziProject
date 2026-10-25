package com.ramzifah.newproject.model.gym;

import androidx.room.PrimaryKey;

public class mygym {
    @PrimaryKey(autoGenerate = true)
    public long key_id;

    public int userage;
    public int reps;
    public int sets;
    public int workoutDurationMinutes;
    public int restTimeSeconds;

    @Override
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

    public long getKey_id() {
        return key_id;
    }

    public void setKey_id(long key_id) {
        this.key_id = key_id;
    }

    public int getUserage() {
        return userage;
    }

    public void setUserage(int userage) {
        this.userage = userage;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWorkoutDurationMinutes() {
        return workoutDurationMinutes;
    }

    public void setWorkoutDurationMinutes(int workoutDurationMinutes) {
        this.workoutDurationMinutes = workoutDurationMinutes;
    }

    public int getRestTimeSeconds() {
        return restTimeSeconds;
    }

    public void setRestTimeSeconds(int restTimeSeconds) {
        this.restTimeSeconds = restTimeSeconds;
    }
}
