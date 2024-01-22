package com.coveiot.covepreferences.data;
/* loaded from: classes8.dex */
public class VibrationAlarmData {
    public static VibrationAlarmData r;

    /* renamed from: a  reason: collision with root package name */
    public String f7049a;
    public String b;
    public String c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public String n;
    public int o;
    public int p;
    public int q = 1;

    public static VibrationAlarmData getInstance() {
        VibrationAlarmData vibrationAlarmData = new VibrationAlarmData();
        r = vibrationAlarmData;
        return vibrationAlarmData;
    }

    public String getAlarmId() {
        return this.b;
    }

    public int getAlarmType() {
        return this.q;
    }

    public String getEventDays() {
        return this.n;
    }

    public String getEvent_name() {
        return this.f7049a;
    }

    public int getEvent_time_hour() {
        return this.d;
    }

    public int getEvent_time_minutes() {
        return this.e;
    }

    public int getSnoozeDuration() {
        return this.o;
    }

    public int getSnoozeRepeatCount() {
        return this.p;
    }

    public boolean getSwitch_on_off() {
        return this.m;
    }

    public String getWeeks() {
        return this.c;
    }

    public boolean isFriday() {
        return this.k;
    }

    public boolean isMonday() {
        return this.g;
    }

    public boolean isSaturday() {
        return this.l;
    }

    public boolean isSunday() {
        return this.f;
    }

    public boolean isThrusday() {
        return this.j;
    }

    public boolean isTuesday() {
        return this.h;
    }

    public boolean isWednesday() {
        return this.i;
    }

    public void setAlarmId(String str) {
        this.b = str;
    }

    public void setAlarmType(int i) {
        this.q = i;
    }

    public void setEventDays(String str) {
        this.n = str;
    }

    public void setEvent_name(String str) {
        this.f7049a = str;
    }

    public void setEvent_time_hour(int i) {
        this.d = i;
    }

    public void setEvent_time_minutes(int i) {
        this.e = i;
    }

    public void setFriday(boolean z) {
        this.k = z;
    }

    public void setMonday(boolean z) {
        this.g = z;
    }

    public void setSaturday(boolean z) {
        this.l = z;
    }

    public void setSnoozeDuration(int i) {
        this.o = i;
    }

    public void setSnoozeRepeatCount(int i) {
        this.p = i;
    }

    public void setSunday(boolean z) {
        this.f = z;
    }

    public void setSwitch_on_off(boolean z) {
        this.m = z;
    }

    public void setThrusday(boolean z) {
        this.j = z;
    }

    public void setTuesday(boolean z) {
        this.h = z;
    }

    public void setWednesday(boolean z) {
        this.i = z;
    }

    public void setWeeks(String str) {
        this.c = str;
    }
}
