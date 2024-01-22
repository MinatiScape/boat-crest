package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class MyAutomaticHRMonitoring extends SendData {

    /* renamed from: a  reason: collision with root package name */
    public int f12525a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public int getEndHour() {
        return this.d;
    }

    public int getEndMinute() {
        return this.e;
    }

    public int getOpen() {
        return this.f12525a;
    }

    public int getStartHour() {
        return this.b;
    }

    public int getStartMinute() {
        return this.c;
    }

    public int getTime() {
        return this.g;
    }

    public int getWeek() {
        return this.f;
    }

    public void setEndHour(int i) {
        this.d = i;
    }

    public void setEndMinute(int i) {
        this.e = i;
    }

    public void setOpen(int i) {
        this.f12525a = i;
    }

    public void setStartHour(int i) {
        this.b = i;
    }

    public void setStartMinute(int i) {
        this.c = i;
    }

    public void setTime(int i) {
        this.g = i;
    }

    public void setWeek(int i) {
        this.f = i;
    }

    public String toString() {
        return "MyAutomaticHRMonitoring{open=" + this.f12525a + ", startHour=" + this.b + ", startMinute=" + this.c + ", endHour=" + this.d + ", endMinute=" + this.e + ", week=" + this.f + ", time=" + this.g + '}';
    }
}
