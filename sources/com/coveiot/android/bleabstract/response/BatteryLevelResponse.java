package com.coveiot.android.bleabstract.response;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class BatteryLevelResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f3580a = 0;
    public boolean b;

    @Nullable
    public final Integer getBatteryLevel() {
        return this.f3580a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setBatteryLevel(@Nullable Integer num) {
        this.f3580a = num;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }
}
