package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class PaceSample {
    private long timeStamp;
    private float value;

    public PaceSample(float f, long j) {
        this.value = f;
        this.timeStamp = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public float getValue() {
        return this.value;
    }
}
