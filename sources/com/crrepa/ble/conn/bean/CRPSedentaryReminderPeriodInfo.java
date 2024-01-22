package com.crrepa.ble.conn.bean;
/* loaded from: classes9.dex */
public class CRPSedentaryReminderPeriodInfo {

    /* renamed from: a  reason: collision with root package name */
    public byte f7663a;
    public byte b;
    public byte c;
    public byte d;

    public CRPSedentaryReminderPeriodInfo() {
    }

    public CRPSedentaryReminderPeriodInfo(byte b, byte b2, byte b3, byte b4) {
        this.f7663a = b;
        this.b = b2;
        this.c = b3;
        this.d = b4;
    }

    public byte getEndHour() {
        return this.d;
    }

    public byte getPeriod() {
        return this.f7663a;
    }

    public byte getStartHour() {
        return this.c;
    }

    public byte getSteps() {
        return this.b;
    }

    public void setEndHour(byte b) {
        this.d = b;
    }

    public void setPeriod(byte b) {
        this.f7663a = b;
    }

    public void setStartHour(byte b) {
        this.c = b;
    }

    public void setSteps(byte b) {
        this.b = b;
    }
}
