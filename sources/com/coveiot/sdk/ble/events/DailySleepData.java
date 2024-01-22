package com.coveiot.sdk.ble.events;
/* loaded from: classes9.dex */
public class DailySleepData {
    public String activityType;
    public int awake;
    public int deep_sleep;
    public int light_sleep;
    public String mDate;
    public int total_sleep;
    public int un_sleep;

    public String getActivityType() {
        return this.activityType;
    }

    public int getAwake() {
        return this.awake;
    }

    public int getDeep_sleep() {
        return this.deep_sleep;
    }

    public int getLight_sleep() {
        return this.light_sleep;
    }

    public int getTotal_sleep() {
        return this.total_sleep;
    }

    public int getUn_sleep() {
        return this.un_sleep;
    }

    public String getmDate() {
        return this.mDate;
    }

    public void setActivityType(String str) {
        this.activityType = str;
    }

    public void setAwake(int i) {
        this.awake = i;
    }

    public void setDeep_sleep(int i) {
        this.deep_sleep = i;
    }

    public void setLight_sleep(int i) {
        this.light_sleep = i;
    }

    public void setTotal_sleep(int i) {
        this.total_sleep = i;
    }

    public void setUn_sleep(int i) {
        this.un_sleep = i;
    }

    public void setmDate(String str) {
        this.mDate = str;
    }
}
