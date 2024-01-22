package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class SleepHourData extends HourData {
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;

    public int getAwakePerHour() {
        return this.j;
    }

    public int getDeepSleepPerHour() {
        return this.h;
    }

    public int getLightSleepPerHour() {
        return this.i;
    }

    public int getTotalSleepPerHour() {
        return this.g;
    }

    public int getUnSleepPerHour() {
        return this.k;
    }

    public void setAwakePerHour(int i) {
        this.j = i;
    }

    public void setDeepSleepPerHour(int i) {
        this.h = i;
    }

    public void setLightSleepPerHour(int i) {
        this.i = i;
    }

    public void setTotalSleepPerHour(int i) {
        this.g = i;
    }

    public void setUnSleepPerHour(int i) {
        this.k = i;
    }

    public String toString() {
        return "SleepHourData{mTotalSleepPerHour=" + this.g + ", mDeepSleepPerHour=" + this.h + ", mLightSleepPerHour=" + this.i + ", mAwakePerHour=" + this.j + ", mUnSleepPerHour=" + this.k + ", mMinuteWiseData=" + this.mMinuteWiseData + '}';
    }
}
