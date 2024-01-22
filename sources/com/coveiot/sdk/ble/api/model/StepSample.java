package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class StepSample {
    private long timeStamp;
    private int value;

    public StepSample(int i, long j) {
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
