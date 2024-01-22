package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class DoNotDisturbData {
    public static DoNotDisturbData g;

    /* renamed from: a  reason: collision with root package name */
    public int f7021a = 0;
    public int b = 0;
    public boolean c = false;
    public boolean d = false;
    public int e = 23;
    public int f = 59;

    public static DoNotDisturbData getInstance() {
        if (g == null) {
            g = new DoNotDisturbData();
        }
        return g;
    }

    public int getBeggining_time_hour() {
        return this.f7021a;
    }

    public int getBeggining_time_minutes() {
        return this.b;
    }

    public int getEnd_time_hour() {
        return this.e;
    }

    public int getEnd_time_minutes() {
        return this.f;
    }

    public boolean isDnd_on_off() {
        return this.c;
    }

    public boolean isSchedule_on_off() {
        return this.d;
    }

    public void setBeggining_time_hour(int i) {
        this.f7021a = i;
    }

    public void setBeggining_time_minutes(int i) {
        this.b = i;
    }

    public void setDnd_on_off(boolean z) {
        this.c = z;
    }

    public void setEnd_time_hour(int i) {
        this.e = i;
    }

    public void setEnd_time_minutes(int i) {
        this.f = i;
    }

    public void setSchedule_on_off(boolean z) {
        this.d = z;
    }
}
