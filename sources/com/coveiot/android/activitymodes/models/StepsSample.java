package com.coveiot.android.activitymodes.models;

import kotlin.jvm.internal.DefaultConstructorMarker;
/* loaded from: classes2.dex */
public final class StepsSample {

    /* renamed from: a  reason: collision with root package name */
    public int f2853a;
    public float b;
    public int c;
    public long d;

    public StepsSample() {
        this(0, 0.0f, 0, 0L, 15, null);
    }

    public StepsSample(int i, float f, int i2, long j) {
        this.f2853a = i;
        this.b = f;
        this.c = i2;
        this.d = j;
    }

    public final float getCalories() {
        return this.b;
    }

    public final int getDistance() {
        return this.c;
    }

    public final int getStepCount() {
        return this.f2853a;
    }

    public final long getTimeStamp() {
        return this.d;
    }

    public final void setCalories(float f) {
        this.b = f;
    }

    public final void setDistance(int i) {
        this.c = i;
    }

    public final void setStepCount(int i) {
        this.f2853a = i;
    }

    public final void setTimeStamp(long j) {
        this.d = j;
    }

    public /* synthetic */ StepsSample(int i, float f, int i2, long j, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0.0f : f, (i3 & 4) == 0 ? i2 : 0, (i3 & 8) != 0 ? 0L : j);
    }
}
