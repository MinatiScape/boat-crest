package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class ManualBpData {
    private byte[] byteValues;
    private int diastolicbp;
    private int hr;
    private int systolicbp;
    private long timeStamp;

    public ManualBpData(byte[] bArr) {
        this.byteValues = bArr;
        processData();
    }

    private void processData() {
        byte[] bArr = this.byteValues;
        long j = bArr[0] & 255;
        this.timeStamp = j;
        long j2 = ((bArr[1] & 255) << 8) | j;
        this.timeStamp = j2;
        long j3 = j2 | ((bArr[2] & 255) << 16);
        this.timeStamp = j3;
        long j4 = j3 | ((bArr[3] & 255) << 24);
        this.timeStamp = j4;
        this.timeStamp = j4 * 1000;
        this.hr = bArr[4] & 255;
        this.systolicbp = bArr[5] & 255;
        this.diastolicbp = bArr[6] & 255;
    }

    public int getDiastolicbp() {
        return this.diastolicbp;
    }

    public int getHr() {
        return this.hr;
    }

    public int getSystolicbp() {
        return this.systolicbp;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
