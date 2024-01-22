package com.coveiot.android.activitymodes.models;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: classes2.dex */
public final class HeartRateSample {

    /* renamed from: a  reason: collision with root package name */
    public int f2851a;
    public long b;

    public HeartRateSample(int i, long j) {
        this.f2851a = i;
        this.b = j;
    }

    public final int getHr_value() {
        return this.f2851a;
    }

    public final long getTimestamp() {
        return this.b;
    }

    public final void setHr_value(int i) {
        this.f2851a = i;
    }

    public final void setTimestamp(long j) {
        this.b = j;
    }

    public /* synthetic */ HeartRateSample(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 0L : j);
    }
}
