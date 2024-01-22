package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TemperatureResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public TemperatureDayData f3676a;
    public boolean b;

    @Nullable
    public final TemperatureDayData getTemperatureData() {
        return this.f3676a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setTemperatureData(@Nullable TemperatureDayData temperatureDayData) {
        this.f3676a = temperatureDayData;
    }
}
