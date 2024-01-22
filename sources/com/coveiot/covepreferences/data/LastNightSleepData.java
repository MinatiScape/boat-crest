package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class LastNightSleepData {

    /* renamed from: a  reason: collision with root package name */
    public int f7029a;
    public int b;
    public int c;

    public LastNightSleepData() {
    }

    public int getDuration() {
        return this.c;
    }

    public int getEndHour() {
        return this.b;
    }

    public int getStartHour() {
        return this.f7029a;
    }

    public void setDuration(int i) {
        this.c = i;
    }

    public void setEndHour(int i) {
        this.b = i;
    }

    public void setStartHour(int i) {
        this.f7029a = i;
    }

    public LastNightSleepData(int i, int i2, int i3) {
        this.f7029a = i;
        this.b = i2;
        this.c = i3;
    }
}
