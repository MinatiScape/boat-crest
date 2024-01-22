package com.crrepa.ble.conn.bean;

import java.util.Date;
/* loaded from: classes9.dex */
public class CRPPhysiologcalPeriodInfo {

    /* renamed from: a  reason: collision with root package name */
    public int f7660a;
    public int b;
    public Date c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public int h;
    public int i;

    public CRPPhysiologcalPeriodInfo() {
    }

    public CRPPhysiologcalPeriodInfo(int i, int i2, Date date, boolean z, boolean z2, boolean z3, boolean z4, int i3, int i4) {
        this.f7660a = i;
        this.b = i2;
        this.c = date;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = z4;
        this.h = i3;
        this.i = i4;
    }

    public int getMenstrualPeriod() {
        return this.b;
    }

    public int getPhysiologcalPeriod() {
        return this.f7660a;
    }

    public int getReminderHour() {
        return this.h;
    }

    public int getReminderMinute() {
        return this.i;
    }

    public Date getStartDate() {
        return this.c;
    }

    public boolean isMenstrualReminder() {
        return this.d;
    }

    public boolean isOvulationDayReminder() {
        return this.f;
    }

    public boolean isOvulationEndReminder() {
        return this.g;
    }

    public boolean isOvulationReminder() {
        return this.e;
    }

    public void setMenstrualPeriod(int i) {
        this.b = i;
    }

    public void setMenstrualReminder(boolean z) {
        this.d = z;
    }

    public void setOvulationDayReminder(boolean z) {
        this.f = z;
    }

    public void setOvulationEndReminder(boolean z) {
        this.g = z;
    }

    public void setOvulationReminder(boolean z) {
        this.e = z;
    }

    public void setPhysiologcalPeriod(int i) {
        this.f7660a = i;
    }

    public void setReminderHour(int i) {
        this.h = i;
    }

    public void setReminderMinute(int i) {
        this.i = i;
    }

    public void setStartDate(Date date) {
        this.c = date;
    }
}
