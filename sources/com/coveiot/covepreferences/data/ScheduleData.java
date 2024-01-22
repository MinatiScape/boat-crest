package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class ScheduleData {
    public static ScheduleData j;

    /* renamed from: a  reason: collision with root package name */
    public int f7042a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public String h;
    public String i;

    public static ScheduleData getINSTANCE() {
        return j;
    }

    public static ScheduleData getInstance() {
        ScheduleData scheduleData = new ScheduleData();
        j = scheduleData;
        return scheduleData;
    }

    public static void setINSTANCE(ScheduleData scheduleData) {
        j = scheduleData;
    }

    public int getAdvance() {
        return this.g;
    }

    public String getContent() {
        return this.i;
    }

    public int getDay() {
        return this.d;
    }

    public int getHour() {
        return this.e;
    }

    public int getMinute() {
        return this.f;
    }

    public int getMonth() {
        return this.c;
    }

    public int getScheduleId() {
        return this.f7042a;
    }

    public String getTitle() {
        return this.h;
    }

    public int getYear() {
        return this.b;
    }

    public void setAdvance(int i) {
        this.g = i;
    }

    public void setContent(String str) {
        this.i = str;
    }

    public void setDay(int i) {
        this.d = i;
    }

    public void setHour(int i) {
        this.e = i;
    }

    public void setMinute(int i) {
        this.f = i;
    }

    public void setMonth(int i) {
        this.c = i;
    }

    public void setScheduleId(int i) {
        this.f7042a = i;
    }

    public void setTitle(String str) {
        this.h = str;
    }

    public void setYear(int i) {
        this.b = i;
    }
}
