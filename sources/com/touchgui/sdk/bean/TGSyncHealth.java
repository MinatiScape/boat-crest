package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGSyncHealth {
    private int bpDay;
    private int heartRateDay;
    private int sleepDay;
    private int stepDay;

    public int getBpDay() {
        return this.bpDay;
    }

    public int getHeartRateDay() {
        return this.heartRateDay;
    }

    public int getSleepDay() {
        return this.sleepDay;
    }

    public int getStepDay() {
        return this.stepDay;
    }

    public void setBpDay(int i) {
        this.bpDay = i;
    }

    public void setHeartRateDay(int i) {
        this.heartRateDay = i;
    }

    public void setSleepDay(int i) {
        this.sleepDay = i;
    }

    public void setStepDay(int i) {
        this.stepDay = i;
    }

    public String toString() {
        return "HealthData{stepDay=" + this.stepDay + ", sleepDay=" + this.sleepDay + ", heartRateDay=" + this.heartRateDay + ", bpDay=" + this.bpDay + '}';
    }
}
