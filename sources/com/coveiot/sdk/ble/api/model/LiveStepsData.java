package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class LiveStepsData {
    public int liveSteps;
    public double meters = -1.0d;
    public double calories = -1.0d;

    public double getCalories() {
        return this.calories;
    }

    public int getLiveSteps() {
        return this.liveSteps;
    }

    public double getMeters() {
        return this.meters;
    }

    public void setCalories(double d) {
        this.calories = d;
    }

    public void setLiveSteps(int i) {
        this.liveSteps = i;
    }

    public void setMeters(double d) {
        this.meters = d;
    }

    public String toString() {
        return "LiveStepsData{liveSteps=" + this.liveSteps + ", meters=" + this.meters + ", calories=" + this.calories + '}';
    }
}
