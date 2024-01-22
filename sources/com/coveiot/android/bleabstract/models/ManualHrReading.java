package com.coveiot.android.bleabstract.models;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class ManualHrReading implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public long f3434a;
    public int b;
    public String c;

    public ManualHrReading(long j, int i, String str) {
        this.f3434a = j;
        this.b = i;
        this.c = str;
    }

    public int getHr() {
        return this.b;
    }

    public String getMacAddress() {
        return this.c;
    }

    public long getTimeStamp() {
        return this.f3434a;
    }

    public String toString() {
        return "ManualBpReading{timeStamp=" + this.f3434a + ", hr=" + this.b + ", macAddress=" + this.c + '}';
    }
}
