package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPDrinkWaterPeriodInfo {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7645a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public CRPDrinkWaterPeriodInfo() {
    }

    public CRPDrinkWaterPeriodInfo(boolean z, int i, int i2, int i3, int i4) {
        this.f7645a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public CRPDrinkWaterPeriodInfo(boolean z, int i, int i2, int i3, int i4, int i5) {
        this.f7645a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
    }

    public int getCount() {
        return this.d;
    }

    public int getCurrentCups() {
        return this.f;
    }

    public int getPeriod() {
        return this.e;
    }

    public int getStartHour() {
        return this.b;
    }

    public int getStartMinute() {
        return this.c;
    }

    public boolean isEnable() {
        return this.f7645a;
    }

    public void setCount(int i) {
        this.d = i;
    }

    public void setCurrentCups(int i) {
        this.f = i;
    }

    public void setEnable(boolean z) {
        this.f7645a = z;
    }

    public void setPeriod(int i) {
        this.e = i;
    }

    public void setStartHour(int i) {
        this.b = i;
    }

    public void setStartMinute(int i) {
        this.c = i;
    }
}
