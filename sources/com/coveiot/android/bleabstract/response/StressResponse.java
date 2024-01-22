package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class StressResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public StressDayData f3672a;
    @Nullable
    public HRVDayData b;
    public boolean c;

    @Nullable
    public final HRVDayData getHrvDayData() {
        return this.b;
    }

    @Nullable
    public final StressDayData getStressDayData() {
        return this.f3672a;
    }

    public final boolean isComplete() {
        return this.c;
    }

    public final void setComplete(boolean z) {
        this.c = z;
    }

    public final void setHrvDayData(@Nullable HRVDayData hRVDayData) {
        this.b = hRVDayData;
    }

    public final void setStressDayData(@Nullable StressDayData stressDayData) {
        this.f3672a = stressDayData;
    }
}
