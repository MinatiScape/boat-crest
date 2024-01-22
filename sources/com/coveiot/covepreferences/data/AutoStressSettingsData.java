package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class AutoStressSettingsData {
    public static AutoStressSettingsData q;

    /* renamed from: a  reason: collision with root package name */
    public int f7014a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public boolean f;
    public int g;
    public int h;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public boolean l = true;
    public boolean m = true;
    public boolean n = true;
    public boolean o = true;
    public String p = "-------";

    public static AutoStressSettingsData getInstance() {
        if (q == null) {
            q = new AutoStressSettingsData();
        }
        return q;
    }

    public boolean getAutoStress() {
        return this.e;
    }

    public int getBeggining_time_hour() {
        return this.c;
    }

    public int getBeggining_time_minutes() {
        return this.d;
    }

    public int getEnd_time_hour() {
        return this.g;
    }

    public int getEnd_time_minutes() {
        return this.h;
    }

    public int getMeasuringInterval() {
        return this.b;
    }

    public int getRemind_in() {
        return this.f7014a;
    }

    public String getWeeks() {
        return this.p;
    }

    public boolean isFriday() {
        return this.n;
    }

    public boolean isHighStressReminder() {
        return this.f;
    }

    public boolean isMonday() {
        return this.j;
    }

    public boolean isSaturday() {
        return this.o;
    }

    public boolean isSunday() {
        return this.i;
    }

    public boolean isThrusday() {
        return this.m;
    }

    public boolean isTuesday() {
        return this.k;
    }

    public boolean isWednesday() {
        return this.l;
    }

    public void setAutoStress(boolean z) {
        this.e = z;
    }

    public void setBeggining_time_hour(int i) {
        this.c = i;
    }

    public void setBeggining_time_minutes(int i) {
        this.d = i;
    }

    public void setEnd_time_hour(int i) {
        this.g = i;
    }

    public void setEnd_time_minutes(int i) {
        this.h = i;
    }

    public void setFriday(boolean z) {
        this.n = z;
    }

    public void setHighStressReminder(boolean z) {
        this.f = z;
    }

    public void setMeasuringInterval(int i) {
        this.b = i;
    }

    public void setMonday(boolean z) {
        this.j = z;
    }

    public void setRemind_in(int i) {
        this.f7014a = i;
    }

    public void setSaturday(boolean z) {
        this.o = z;
    }

    public void setSunday(boolean z) {
        this.i = z;
    }

    public void setThrusday(boolean z) {
        this.m = z;
    }

    public void setTuesday(boolean z) {
        this.k = z;
    }

    public void setWednesday(boolean z) {
        this.l = z;
    }

    public void setWeeks(String str) {
        this.p = str;
    }
}
