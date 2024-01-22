package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class PeriodicSpo2Reading {

    /* renamed from: a  reason: collision with root package name */
    public int f3440a;
    public boolean b;

    public boolean getLevelInterpretation() {
        return this.b;
    }

    public int getSpo2() {
        return this.f3440a;
    }

    public void setLevelInterpretation(boolean z) {
        this.b = z;
    }

    public void setSpo2(int i) {
        this.f3440a = i;
    }
}
