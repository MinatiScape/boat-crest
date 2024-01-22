package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public class ManualBpReading {

    /* renamed from: a  reason: collision with root package name */
    public long f3432a;
    public int b;
    public int c;
    public int d;

    public ManualBpReading(long j, int i, int i2, int i3) {
        this.f3432a = j;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public int getDiastolicbp() {
        return this.c;
    }

    public int getHr() {
        return this.d;
    }

    public int getSystolicbp() {
        return this.b;
    }

    public long getTimeStamp() {
        return this.f3432a;
    }

    public String toString() {
        return "ManualBpReading{timeStamp=" + this.f3432a + ", systolicbp=" + this.b + ", diastolicbp=" + this.c + ", hr=" + this.d + '}';
    }
}
