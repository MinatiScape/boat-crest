package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class SpeedSample {
    private long timeStamp;
    private long value;

    public SpeedSample(long j, long j2) {
        this.value = j;
        this.timeStamp = j2;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public long getValue() {
        return this.value;
    }
}
