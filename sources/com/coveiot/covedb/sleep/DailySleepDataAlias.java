package com.coveiot.covedb.sleep;
/* loaded from: classes8.dex */
public class DailySleepDataAlias {
    public String _id;
    public int awake;
    public String date;
    public int deep_sleep;
    public int lastNightDeepSleep;
    public int lastNightLightSleep;
    public int lastNightSleep;
    public int lastNightawake;
    public int light_sleep;
    public String mac_address;
    public int target;
    public int value;

    public int getAwake() {
        return this.awake;
    }

    public String getDate() {
        return this.date;
    }

    public int getDeepSleep() {
        return this.deep_sleep;
    }

    public int getLastNightDeepSleep() {
        return this.lastNightDeepSleep;
    }

    public int getLastNightLightSleep() {
        return this.lastNightLightSleep;
    }

    public int getLastNightSleep() {
        return this.lastNightSleep;
    }

    public int getLastNightawake() {
        return this.lastNightawake;
    }

    public int getLightSleep() {
        return this.light_sleep;
    }

    public String getMac_address() {
        return this.mac_address;
    }

    public int getTarget() {
        return this.target;
    }

    public int getValue() {
        return this.value;
    }

    public String get_id() {
        return this._id;
    }

    public void setLastNightDeepSleep(int i) {
        this.lastNightDeepSleep = i;
    }

    public void setLastNightLightSleep(int i) {
        this.lastNightLightSleep = i;
    }

    public void setLastNightawake(int i) {
        this.lastNightawake = i;
    }

    public void setTarget(int i) {
        this.target = i;
    }
}
