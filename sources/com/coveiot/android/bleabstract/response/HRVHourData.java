package com.coveiot.android.bleabstract.response;
/* loaded from: classes2.dex */
public class HRVHourData extends TempHourData {
    public double g;
    public double h;
    public double i;

    public double getAvgHRV() {
        return this.i;
    }

    public double getMaxHRV() {
        return this.h;
    }

    public double getMinHRV() {
        return this.g;
    }

    public void setAvgHRV(double d) {
        this.i = d;
    }

    public void setMaxHRV(double d) {
        this.h = d;
    }

    public void setMinHRV(double d) {
        this.g = d;
    }
}
