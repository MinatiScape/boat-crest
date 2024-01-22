package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WatchFacePositionResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f3683a = 0;
    public boolean b;

    @Nullable
    public final Integer getWatchFacePosition() {
        return this.f3683a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setWatchFacePosition(@Nullable Integer num) {
        this.f3683a = num;
    }
}
