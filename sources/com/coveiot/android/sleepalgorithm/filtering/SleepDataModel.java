package com.coveiot.android.sleepalgorithm.filtering;

import java.util.Arrays;
/* loaded from: classes6.dex */
public class SleepDataModel {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f5684a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public SleepDataModel(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f5684a = bArr;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = i9;
    }

    public int getCountOfAwakeMinutes() {
        return this.h;
    }

    public int getCountOfDeepSleepMinutes() {
        return this.g;
    }

    public int getCountOfLightSleepMinutes() {
        return this.f;
    }

    public int getCountOfREMMinutes() {
        return this.j;
    }

    public int getCountTotalSleep() {
        return this.i;
    }

    public byte[] getFilteredSleepData() {
        return this.f5684a;
    }

    public int getSleepEndHour() {
        return this.d;
    }

    public int getSleepEndMinute() {
        return this.e;
    }

    public int getSleepStartHour() {
        return this.b;
    }

    public int getSleepStartMinute() {
        return this.c;
    }

    public String toString() {
        return "SleepDataModel{mFilteredSleepData=" + Arrays.toString(this.f5684a) + "\n, mSleepStartHour=" + this.b + "\n, mSleepStartMinute=" + this.c + "\n, mSleepEndHour=" + this.d + "\n, mSleepEndMinute=" + this.e + "\n, mCountOfLightSleepMinutes=" + this.f + "\n, mCountOfDeepSleepMinutes=" + this.g + "\n, mCountOfAwakeMinutes=" + this.h + "\n, mCountTotalSleep=" + this.i + "\n, mCountOfREMMinutes=" + this.j + '}';
    }
}
