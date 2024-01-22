package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AmbientSoundResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public AmbientSoundDayData f3578a;
    public boolean b;

    @Nullable
    public final AmbientSoundDayData getAmbientSoundDayData() {
        return this.f3578a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setAmbientSoundDayData(@Nullable AmbientSoundDayData ambientSoundDayData) {
        this.f3578a = ambientSoundDayData;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }
}
