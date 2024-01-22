package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGRealTimeData {
    private int heartRate;
    private int totalActiveTime;
    private int totalCalories;
    private int totalDistances;
    private int totalSteps;

    public int getHeartRate() {
        return this.heartRate;
    }

    public int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public int getTotalCalories() {
        return this.totalCalories;
    }

    public int getTotalDistances() {
        return this.totalDistances;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public void setTotalActiveTime(int i) {
        this.totalActiveTime = i;
    }

    public void setTotalCalories(int i) {
        this.totalCalories = i;
    }

    public void setTotalDistances(int i) {
        this.totalDistances = i;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }

    public String toString() {
        return "RealTimeData{totalSteps=" + this.totalSteps + ", totalCalories=" + this.totalCalories + ", totalDistances=" + this.totalDistances + ", totalActiveTime=" + this.totalActiveTime + ", heartRate=" + this.heartRate + '}';
    }
}
