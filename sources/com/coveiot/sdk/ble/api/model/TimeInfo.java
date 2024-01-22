package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class TimeInfo implements Serializable {
    private int hour;
    private int minute;

    public TimeInfo(int i, int i2) {
        this.hour = i;
        this.minute = i2;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getTimeInMinutes() {
        return this.minute + (this.hour * 60);
    }
}
