package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class ManualSpo2Reading {

    /* renamed from: a  reason: collision with root package name */
    public long f3435a;
    public int b;
    public boolean c;

    public boolean getLevelInterpretation() {
        return this.c;
    }

    public int getSpo2() {
        return this.b;
    }

    public long getTimeStamp() {
        return this.f3435a;
    }

    public void setLevelInterpretation(boolean z) {
        this.c = z;
    }

    public void setSpo2(int i) {
        this.b = i;
    }

    public void setTimeStamp(long j) {
        this.f3435a = j;
    }
}
