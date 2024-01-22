package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BpResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BpDayData f3586a;
    public boolean b;

    @Nullable
    public final BpDayData getBpDayData() {
        return this.f3586a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setBpDayData(@Nullable BpDayData bpDayData) {
        this.f3586a = bpDayData;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }
}
