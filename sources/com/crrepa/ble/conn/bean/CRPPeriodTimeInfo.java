package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPPeriodTimeInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7659a;
    public int b;
    public int c;
    public int d;

    public CRPPeriodTimeInfo() {
    }

    public CRPPeriodTimeInfo(int i, int i2, int i3, int i4) {
        this.f7659a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getEndHour() {
        return this.c;
    }

    public int getEndMinute() {
        return this.d;
    }

    public int getStartHour() {
        return this.f7659a;
    }

    public int getStartMinute() {
        return this.b;
    }

    public void setEndHour(int i) {
        this.c = i;
    }

    public void setEndMinute(int i) {
        this.d = i;
    }

    public void setStartHour(int i) {
        this.f7659a = i;
    }

    public void setStartMinute(int i) {
        this.b = i;
    }
}
