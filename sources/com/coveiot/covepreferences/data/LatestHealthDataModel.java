package com.coveiot.covepreferences.data;

import com.coveiot.android.bleabstract.models.HealthVitalsType;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class LatestHealthDataModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public HealthVitalsType f7030a;
    public long b;
    public int c;

    @Nullable
    public final HealthVitalsType getHealthDataType() {
        return this.f7030a;
    }

    public final long getTimestamp() {
        return this.b;
    }

    public final int getValue() {
        return this.c;
    }

    public final void setHealthDataType(@Nullable HealthVitalsType healthVitalsType) {
        this.f7030a = healthVitalsType;
    }

    public final void setTimestamp(long j) {
        this.b = j;
    }

    public final void setValue(int i) {
        this.c = i;
    }
}
