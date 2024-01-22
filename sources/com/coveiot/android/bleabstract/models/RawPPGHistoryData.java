package com.coveiot.android.bleabstract.models;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class RawPPGHistoryData {

    /* renamed from: a  reason: collision with root package name */
    public int f3444a;
    public long b;
    public ArrayList<Integer> c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;

    public RawPPGHistoryData(int i, long j, ArrayList<Integer> arrayList, int i2, int i3, int i4, int i5, int i6) {
        this.f3444a = i;
        this.b = j;
        this.c = arrayList;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
    }

    public int getDuration() {
        return this.g;
    }

    public int getInterval() {
        return this.f;
    }

    public int getMovementLevel() {
        return this.h;
    }

    public ArrayList<Integer> getPpgData() {
        return this.c;
    }

    public int getPpgType() {
        return this.e;
    }

    public int getRecordNumber() {
        return this.f3444a;
    }

    public int getSamplingRate() {
        return this.d;
    }

    public long getTimeStamp() {
        return this.b;
    }
}
