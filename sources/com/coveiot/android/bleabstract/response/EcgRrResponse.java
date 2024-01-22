package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EcgRrResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public EcgRrDayData f3598a;
    public boolean b;

    @Nullable
    public final EcgRrDayData getEcgRrResponse() {
        return this.f3598a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setEcgRrResponse(@Nullable EcgRrDayData ecgRrDayData) {
        this.f3598a = ecgRrDayData;
    }
}
