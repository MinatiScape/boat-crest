package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class ManualSpo2Data {
    private int spo2Value;
    private long timeStamp;

    public ManualSpo2Data(long j, int i) {
        this.timeStamp = j;
        this.spo2Value = i;
    }

    public int getSpo2Value() {
        return this.spo2Value;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
