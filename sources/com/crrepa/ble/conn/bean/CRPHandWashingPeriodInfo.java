package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPHandWashingPeriodInfo {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7650a;
    public int b;
    public int c;
    public int d;
    public int e;

    public CRPHandWashingPeriodInfo() {
    }

    public CRPHandWashingPeriodInfo(boolean z, int i, int i2, int i3, int i4) {
        this.f7650a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public int getCount() {
        return this.d;
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
        return this.f7650a;
    }

    public void setCount(int i) {
        this.d = i;
    }

    public void setEnable(boolean z) {
        this.f7650a = z;
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
