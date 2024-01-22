package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class HeartRateResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public HeartRateDayData f3635a;
    public boolean b;

    @Nullable
    public final HeartRateDayData getHeartRateData() {
        return this.f3635a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setHeartRateData(@Nullable HeartRateDayData heartRateDayData) {
        this.f3635a = heartRateDayData;
    }
}
