package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public class UpdateLocationApiReq {
    private double latitude;
    private double longitude;
    private String panicCode;

    public UpdateLocationApiReq(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getPanicCode() {
        return this.panicCode;
    }

    public void setPanicCode(String str) {
        this.panicCode = str;
    }

    public String toString() {
        return "UpdateLocationApiReq{, panicCode='" + this.panicCode + "', latitude='" + this.latitude + "', longitude='" + this.longitude + "'}";
    }
}
