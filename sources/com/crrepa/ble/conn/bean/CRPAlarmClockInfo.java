package com.crrepa.ble.conn.bean;

import java.io.File;
import java.util.Date;
/* loaded from: classes9.dex */
public class CRPAlarmClockInfo {
    public static final byte EVERYDAY = Byte.MAX_VALUE;
    public static final int FIRST_CLOCK = 0;
    public static final byte FRIDAY = 32;
    public static final byte MONDAY = 2;
    public static final byte SATURDAY = 64;
    public static final int SECOND_CLOCK = 1;
    public static final byte SINGLE = 0;
    public static final byte SUNDAY = 1;
    public static final int THIRD_CLOCK = 2;
    public static final byte THURSDAY = 16;
    public static final byte TUESDAY = 4;
    public static final byte WEDNESDAY = 8;

    /* renamed from: a  reason: collision with root package name */
    public int f7638a;
    public int b;
    public int c;
    public int d;
    public boolean e;
    public Date f;

    public CRPAlarmClockInfo() {
    }

    public CRPAlarmClockInfo(int i, int i2, int i3, int i4, boolean z) {
        this.f7638a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = z;
    }

    public Date getDate() {
        return this.f;
    }

    public int getHour() {
        return this.b;
    }

    public int getId() {
        return this.f7638a;
    }

    public int getMinute() {
        return this.c;
    }

    public int getRepeatMode() {
        return this.d;
    }

    public boolean isEnable() {
        return this.e;
    }

    public void setDate(Date date) {
        this.f = date;
    }

    public void setEnable(boolean z) {
        this.e = z;
    }

    public void setHour(int i) {
        this.b = i;
    }

    public void setId(int i) {
        this.f7638a = i;
    }

    public void setMinute(int i) {
        this.c = i;
    }

    public void setRepeatMode(int i) {
        this.d = i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ");
        sb.append(this.f7638a);
        String str = File.separator;
        sb.append(str);
        sb.append("time: ");
        sb.append(this.b);
        sb.append(":");
        sb.append(this.c);
        sb.append(str);
        sb.append("repeatMode: ");
        sb.append(this.d);
        sb.append(str);
        sb.append("enable: ");
        sb.append(this.e);
        return sb.toString();
    }
}
