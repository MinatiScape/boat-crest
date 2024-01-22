package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class PeriodicSpo2Response {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3648a;
    @Nullable
    public Spo2DayData b;

    @Nullable
    public final Spo2DayData getSpo2DayData() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3648a;
    }

    public final void setComplete(boolean z) {
        this.f3648a = z;
    }

    public final void setSpo2DayData(@Nullable Spo2DayData spo2DayData) {
        this.b = spo2DayData;
    }
}
