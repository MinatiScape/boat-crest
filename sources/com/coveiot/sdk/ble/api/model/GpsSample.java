package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class GpsSample {
    private double latitude;
    private double longitude;
    private long timeStamp;

    public GpsSample(double d, double d2, long j) {
        this.latitude = d;
        this.longitude = d2;
        this.timeStamp = j;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
