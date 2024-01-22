package com.coveiot.android.bleabstract.models;

import java.util.ArrayList;
/* loaded from: classes2.dex */
public class RawAccelerometerHistoryData {

    /* renamed from: a  reason: collision with root package name */
    public int f3442a;
    public long b;
    public ArrayList<AccelerometerData> c;
    public int d;
    public int e;
    public int f;

    public RawAccelerometerHistoryData(int i, long j, ArrayList<AccelerometerData> arrayList, int i2, int i3, int i4) {
        this.f3442a = i;
        this.b = j;
        this.c = arrayList;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public ArrayList<AccelerometerData> getAccelerometerData() {
        return this.c;
    }

    public int getDuration() {
        return this.f;
    }

    public int getInterval() {
        return this.e;
    }

    public int getRecordNumber() {
        return this.f3442a;
    }

    public int getSamplingRate() {
        return this.d;
    }

    public long getTimeStamp() {
        return this.b;
    }
}
