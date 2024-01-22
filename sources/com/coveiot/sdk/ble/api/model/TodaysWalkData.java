package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class TodaysWalkData {
    public double totalCalories;
    public int totalDistance;
    public int totalSteps;

    public double getTotalCalories() {
        return this.totalCalories;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public int getTotalSteps() {
        return this.totalSteps;
    }

    public void setTotalCalories(double d) {
        this.totalCalories = d;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public void setTotalSteps(int i) {
        this.totalSteps = i;
    }
}
