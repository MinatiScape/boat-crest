package com.htsmart.wristband2.bean;
/* loaded from: classes11.dex */
public class BatteryStatus {

    /* renamed from: a  reason: collision with root package name */
    public boolean f11952a;
    public int b;
    public int c;

    public BatteryStatus(boolean z, int i, int i2) {
        this.f11952a = z;
        this.c = i;
        this.b = i2;
    }

    public int getBatteryCount() {
        return this.c;
    }

    public int getPercentage() {
        return this.b;
    }

    public boolean isCharging() {
        return this.f11952a;
    }

    public void setBatteryCount(int i) {
        this.c = i;
    }

    public void setCharging(boolean z) {
        this.f11952a = z;
    }

    public void setPercentage(int i) {
        this.b = i;
    }
}
