package com.coveiot.android.activitymodes.models;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: classes2.dex */
public final class LocationSample {

    /* renamed from: a  reason: collision with root package name */
    public double f2852a;
    public double b;
    public long c;

    public LocationSample() {
        this(0.0d, 0.0d, 0L, 7, null);
    }

    public LocationSample(double d, double d2, long j) {
        this.f2852a = d;
        this.b = d2;
        this.c = j;
    }

    public final double getLatitude() {
        return this.f2852a;
    }

    public final double getLongitude() {
        return this.b;
    }

    public final long getTimestamp() {
        return this.c;
    }

    public final void setLatitude(double d) {
        this.f2852a = d;
    }

    public final void setLongitude(double d) {
        this.b = d;
    }

    public final void setTimestamp(long j) {
        this.c = j;
    }

    public /* synthetic */ LocationSample(double d, double d2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0d : d, (i & 2) == 0 ? d2 : 0.0d, (i & 4) != 0 ? 0L : j);
    }
}
