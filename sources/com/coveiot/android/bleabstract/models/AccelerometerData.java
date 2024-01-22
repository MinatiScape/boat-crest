package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class AccelerometerData {

    /* renamed from: a  reason: collision with root package name */
    public int f3388a;
    public int b;
    public int c;

    public AccelerometerData(int i, int i2, int i3) {
        this.f3388a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getX() {
        return this.f3388a;
    }

    public int getY() {
        return this.b;
    }

    public int getZ() {
        return this.c;
    }

    public String toString() {
        return "{x=" + this.f3388a + ", y=" + this.b + ", z=" + this.c + '}';
    }
}
