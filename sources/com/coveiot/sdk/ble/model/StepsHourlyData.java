package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class StepsHourlyData extends HourlyData {
    public double mCaloriesPerHour;
    public double mDistancePerHour;
    public int mStepsPerHour;

    public double getCaloriesPerHour() {
        return this.mCaloriesPerHour;
    }

    public int getStepsPerHour() {
        return this.mStepsPerHour;
    }

    public double getmistancePerHour() {
        return this.mDistancePerHour;
    }

    public void setCaloriesPerHour(double d) {
        this.mCaloriesPerHour = d;
    }

    public void setDistancePerHour(double d) {
        this.mDistancePerHour = d;
    }

    public void setStepsPerHour(int i) {
        this.mStepsPerHour = i;
    }
}
