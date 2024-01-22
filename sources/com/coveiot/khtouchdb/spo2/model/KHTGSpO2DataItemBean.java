package com.coveiot.khtouchdb.spo2.model;
/* loaded from: classes8.dex */
public final class KHTGSpO2DataItemBean {

    /* renamed from: a  reason: collision with root package name */
    public int f7195a;
    public int b;
    public int c;
    public int d;

    public final int getHeartRate() {
        return this.d;
    }

    public final int getTimeSeconds() {
        return this.b;
    }

    public final int getUtcSeconds() {
        return this.f7195a;
    }

    public final int getValue() {
        return this.c;
    }

    public final void setHeartRate(int i) {
        this.d = i;
    }

    public final void setTimeSeconds(int i) {
        this.b = i;
    }

    public final void setUtcSeconds(int i) {
        this.f7195a = i;
    }

    public final void setValue(int i) {
        this.c = i;
    }
}
