package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class HeartSample {
    private long timeStamp;
    private int value;

    public HeartSample(int i, long j) {
        this.value = i;
        this.timeStamp = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public int getValue() {
        return this.value;
    }
}
