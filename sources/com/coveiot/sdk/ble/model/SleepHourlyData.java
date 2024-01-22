package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class SleepHourlyData extends HourlyData {
    public int mAwakePerHour;
    public int mDeepSleepPerHour;
    public int mLightSleepPerHour;
    public int mTotalSleepPerHour;
    public int mUnSleepPerHour;

    public int getAwakePerHour() {
        return this.mAwakePerHour;
    }

    public int getDeepSleepPerHour() {
        return this.mDeepSleepPerHour;
    }

    public int getLightSleepPerHour() {
        return this.mLightSleepPerHour;
    }

    public int getTotalSleepPerHour() {
        return this.mTotalSleepPerHour;
    }

    public int getUnSleepPerHour() {
        return this.mUnSleepPerHour;
    }

    public void setAwakePerHour(int i) {
        this.mAwakePerHour = i;
    }

    public void setDeepSleepPerHour(int i) {
        this.mDeepSleepPerHour = i;
    }

    public void setLightSleepPerHour(int i) {
        this.mLightSleepPerHour = i;
    }

    public void setTotalSleepPerHour(int i) {
        this.mTotalSleepPerHour = i;
    }

    public void setUnSleepPerHour(int i) {
        this.mUnSleepPerHour = i;
    }
}
