package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class MySedentaryReminder extends SendData {

    /* renamed from: a  reason: collision with root package name */
    public int f12529a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public boolean h;

    public int getEndHour() {
        return this.c;
    }

    public int getEndMinute() {
        return this.d;
    }

    public int getIntervalTime() {
        return this.f;
    }

    public int getLeastStep() {
        return this.g;
    }

    public int getStartHour() {
        return this.f12529a;
    }

    public int getStartMinute() {
        return this.b;
    }

    public int getWeek() {
        return this.e;
    }

    public boolean isEnable() {
        return this.h;
    }

    public void setEnable(boolean z) {
        this.h = z;
    }

    public void setEndHour(int i) {
        this.c = i;
    }

    public void setEndMinute(int i) {
        this.d = i;
    }

    public void setIntervalTime(int i) {
        this.f = i;
    }

    public void setLeastStep(int i) {
        this.g = i;
    }

    public void setStartHour(int i) {
        this.f12529a = i;
    }

    public void setStartMinute(int i) {
        this.b = i;
    }

    public void setWeek(int i) {
        this.e = i;
    }

    public String toString() {
        return "MySedentaryReminder{startHour=" + this.f12529a + ", startMinute=" + this.b + ", endHour=" + this.c + ", endMinute=" + this.d + ", week=" + this.e + ", intervalTime=" + this.f + ", leastStep=" + this.g + ", enable=" + this.h + '}';
    }
}
