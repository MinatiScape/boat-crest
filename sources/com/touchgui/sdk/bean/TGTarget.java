package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGTarget {
    public static final int TYPE_CALORIE = 1;
    public static final int TYPE_DISTANCE = 2;
    public static final int TYPE_STANDING_TIME = 5;
    public static final int TYPE_STEP = 0;
    public static final int TYPE_WORKOUT_TIME = 6;
    private int calorie;
    private int distance;
    private int sleepHour;
    private int sleepMinute;
    private int standingTime;
    private int step;
    private int workoutTime;

    public int getCalorie() {
        return this.calorie;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getSleepHour() {
        return this.sleepHour;
    }

    public int getSleepMinute() {
        return this.sleepMinute;
    }

    public int getStandingTime() {
        return this.standingTime;
    }

    public int getStep() {
        return this.step;
    }

    public int getWorkoutTime() {
        return this.workoutTime;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setSleepHour(int i) {
        this.sleepHour = i;
    }

    public void setSleepMinute(int i) {
        this.sleepMinute = i;
    }

    public void setStandingTime(int i) {
        this.standingTime = i;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public void setWorkoutTime(int i) {
        this.workoutTime = i;
    }
}
