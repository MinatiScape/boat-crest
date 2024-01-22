package com.coveiot.android.activitymodes.database.models;
/* loaded from: classes2.dex */
public final class HeartRateZoneRanges {

    /* renamed from: a  reason: collision with root package name */
    public int f2826a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public HeartRateZoneRanges(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f2826a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
    }

    public final int getZone1LowLimit() {
        return this.f2826a;
    }

    public final int getZone2LowLimit() {
        return this.b;
    }

    public final int getZone3LowLimit() {
        return this.c;
    }

    public final int getZone4LowLimit() {
        return this.d;
    }

    public final int getZone5HighLimit() {
        return this.f;
    }

    public final int getZone5LowLimit() {
        return this.e;
    }

    public final void setZone1LowLimit(int i) {
        this.f2826a = i;
    }

    public final void setZone2LowLimit(int i) {
        this.b = i;
    }

    public final void setZone3LowLimit(int i) {
        this.c = i;
    }

    public final void setZone4LowLimit(int i) {
        this.d = i;
    }

    public final void setZone5HighLimit(int i) {
        this.f = i;
    }

    public final void setZone5LowLimit(int i) {
        this.e = i;
    }
}
