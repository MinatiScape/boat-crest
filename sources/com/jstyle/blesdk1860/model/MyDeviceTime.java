package com.jstyle.blesdk1860.model;

import java.util.Calendar;
/* loaded from: classes11.dex */
public class MyDeviceTime extends SendData {

    /* renamed from: a  reason: collision with root package name */
    public int f12527a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public MyDeviceTime() {
        Calendar calendar = Calendar.getInstance();
        this.f12527a = calendar.get(1);
        this.b = calendar.get(2) + 1;
        this.c = calendar.get(5);
        this.d = calendar.get(11);
        this.e = calendar.get(12);
        this.f = calendar.get(13);
    }

    public int getDay() {
        return this.c;
    }

    public int getHour() {
        return this.d;
    }

    public int getMinute() {
        return this.e;
    }

    public int getMonth() {
        return this.b;
    }

    public int getSecond() {
        return this.f;
    }

    public int getYear() {
        return this.f12527a;
    }

    public void setDay(int i) {
        this.c = i;
    }

    public void setHour(int i) {
        this.d = i;
    }

    public void setMinute(int i) {
        this.e = i;
    }

    public void setMonth(int i) {
        this.b = i;
    }

    public void setSecond(int i) {
        this.f = i;
    }

    public void setYear(int i) {
        this.f12527a = i;
    }

    public String toString() {
        return "MyDeviceTime{year=" + this.f12527a + ", month=" + this.b + ", day=" + this.c + ", hour=" + this.d + ", minute=" + this.e + ", second=" + this.f + '}';
    }
}
