package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class VibrationModel {

    /* renamed from: a  reason: collision with root package name */
    public int f3453a;
    public int b;
    public int c;

    public VibrationModel(int i, int i2, int i3) {
        this.f3453a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getDuration() {
        return this.c;
    }

    public int getStrength() {
        return this.b;
    }

    public int getType() {
        return this.f3453a;
    }
}
