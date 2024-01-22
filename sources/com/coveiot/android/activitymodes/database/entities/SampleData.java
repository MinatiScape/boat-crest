package com.coveiot.android.activitymodes.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
/* loaded from: classes2.dex */
public final class SampleData {
    @NonNull
    @ColumnInfo(name = "timestamp")

    /* renamed from: a  reason: collision with root package name */
    public long f2819a;
    @ColumnInfo(name = "calories")
    public float b;
    @ColumnInfo(name = "distance")
    public int c;
    @ColumnInfo(name = "hr_value")
    public int d;
    @ColumnInfo(name = "speed_value")
    public float e;
    @ColumnInfo(name = "latitude")
    public double f;
    @ColumnInfo(name = "longitude")
    public double g;

    public final float getCalories() {
        return this.b;
    }

    public final int getDistance() {
        return this.c;
    }

    public final int getHr_value() {
        return this.d;
    }

    public final double getLatitude() {
        return this.f;
    }

    public final double getLongitude() {
        return this.g;
    }

    public final float getSpeed_value() {
        return this.e;
    }

    public final long getTimeStamp() {
        return this.f2819a;
    }

    public final void setCalories(float f) {
        this.b = f;
    }

    public final void setDistance(int i) {
        this.c = i;
    }

    public final void setHr_value(int i) {
        this.d = i;
    }

    public final void setLatitude(double d) {
        this.f = d;
    }

    public final void setLongitude(double d) {
        this.g = d;
    }

    public final void setSpeed_value(float f) {
        this.e = f;
    }

    public final void setTimeStamp(long j) {
        this.f2819a = j;
    }
}
