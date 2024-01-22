package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class WomenWellnessData {
    public static WomenWellnessData k;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7052a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public static WomenWellnessData getInstance() {
        if (k == null) {
            k = new WomenWellnessData();
        }
        return k;
    }

    public int getLastPeriodDay() {
        return this.h;
    }

    public int getLastPeriodMonth() {
        return this.g;
    }

    public int getLastPeriodYear() {
        return this.f;
    }

    public int getMenstruationCycleLength() {
        return this.j;
    }

    public int getMenstruationPeriodLength() {
        return this.i;
    }

    public int getOvulationReminderAdvance() {
        return this.e;
    }

    public int getPeriodReminderAdvance() {
        return this.d;
    }

    public int getReminderHour() {
        return this.b;
    }

    public int getReminderMinute() {
        return this.c;
    }

    public boolean isEnabled() {
        return this.f7052a;
    }

    public void setEnabled(boolean z) {
        this.f7052a = z;
    }

    public void setLastPeriodDay(int i) {
        this.h = i;
    }

    public void setLastPeriodMonth(int i) {
        this.g = i;
    }

    public void setLastPeriodYear(int i) {
        this.f = i;
    }

    public void setMenstruationCycleLength(int i) {
        this.j = i;
    }

    public void setMenstruationPeriodLength(int i) {
        this.i = i;
    }

    public void setOvulationReminderAdvance(int i) {
        this.e = i;
    }

    public void setPeriodReminderAdvance(int i) {
        this.d = i;
    }

    public void setReminderHour(int i) {
        this.b = i;
    }

    public void setReminderMinute(int i) {
        this.c = i;
    }
}
