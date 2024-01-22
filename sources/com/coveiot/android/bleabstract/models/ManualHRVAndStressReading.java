package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class ManualHRVAndStressReading {

    /* renamed from: a  reason: collision with root package name */
    public long f3433a;
    public int b;
    public int c;
    public int d;
    public int e;

    public int getHr() {
        return this.d;
    }

    public int getHrv() {
        return this.c;
    }

    public int getStress() {
        return this.b;
    }

    public long getTimeStamp() {
        return this.f3433a;
    }

    public int getVascularAging() {
        return this.e;
    }

    public void setHr(int i) {
        this.d = i;
    }

    public void setHrv(int i) {
        this.c = i;
    }

    public void setStress(int i) {
        this.b = i;
    }

    public void setTimeStamp(long j) {
        this.f3433a = j;
    }

    public void setVascularAging(int i) {
        this.e = i;
    }
}
