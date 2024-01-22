package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class RrResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public RrDayData f3654a;
    public boolean b;

    @Nullable
    public final RrDayData getRrResponse() {
        return this.f3654a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setRrResponse(@Nullable RrDayData rrDayData) {
        this.f3654a = rrDayData;
    }
}
