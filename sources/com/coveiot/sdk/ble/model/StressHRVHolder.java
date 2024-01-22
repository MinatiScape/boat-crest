package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class StressHRVHolder {
    private double hrv;
    private double stress;
    private int type;

    public StressHRVHolder(int i, double d, double d2) {
        this.type = i;
        this.stress = d;
        this.hrv = d2;
    }

    public double getHrv() {
        return this.hrv;
    }

    public double getStress() {
        return this.stress;
    }

    public int getType() {
        return this.type;
    }
}
