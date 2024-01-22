package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class SedentaryReminderData {
    public static SedentaryReminderData o;

    /* renamed from: a  reason: collision with root package name */
    public int f7043a;
    public int b;
    public int c;
    public boolean d;
    public int e;
    public int f;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public boolean l = true;
    public boolean m = true;
    public String n = "-------";

    public static SedentaryReminderData getInstance() {
        if (o == null) {
            o = new SedentaryReminderData();
        }
        return o;
    }

    public boolean getAlarm_on_off() {
        return this.d;
    }

    public int getBeggining_time_hour() {
        return this.b;
    }

    public int getBeggining_time_minutes() {
        return this.c;
    }

    public int getEnd_time_hour() {
        return this.e;
    }

    public int getEnd_time_minutes() {
        return this.f;
    }

    public int getRemind_in() {
        return this.f7043a;
    }

    public String getWeeks() {
        return this.n;
    }

    public boolean isFriday() {
        return this.l;
    }

    public boolean isMonday() {
        return this.h;
    }

    public boolean isSaturday() {
        return this.m;
    }

    public boolean isSunday() {
        return this.g;
    }

    public boolean isThrusday() {
        return this.k;
    }

    public boolean isTuesday() {
        return this.i;
    }

    public boolean isWednesday() {
        return this.j;
    }

    public void setAlarm_on_off(boolean z) {
        this.d = z;
    }

    public void setBeggining_time_hour(int i) {
        this.b = i;
    }

    public void setBeggining_time_minutes(int i) {
        this.c = i;
    }

    public void setEnd_time_hour(int i) {
        this.e = i;
    }

    public void setEnd_time_minutes(int i) {
        this.f = i;
    }

    public void setFriday(boolean z) {
        this.l = z;
    }

    public void setMonday(boolean z) {
        this.h = z;
    }

    public void setRemind_in(int i) {
        this.f7043a = i;
    }

    public void setSaturday(boolean z) {
        this.m = z;
    }

    public void setSunday(boolean z) {
        this.g = z;
    }

    public void setThrusday(boolean z) {
        this.k = z;
    }

    public void setTuesday(boolean z) {
        this.i = z;
    }

    public void setWednesday(boolean z) {
        this.j = z;
    }

    public void setWeeks(String str) {
        this.n = str;
    }
}
