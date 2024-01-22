package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleSedentaryData {
    private int recordNumber;
    private long timeStamp;

    public BleSedentaryData(int i, long j) {
        this.recordNumber = i;
        this.timeStamp = j;
    }

    public int getRecordNumber() {
        return this.recordNumber;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
