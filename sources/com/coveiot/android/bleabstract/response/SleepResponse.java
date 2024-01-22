package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SleepResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public SleepDayData f3659a;
    public boolean b;

    @Nullable
    public final SleepDayData getSleepDayData() {
        return this.f3659a;
    }

    public final boolean isComplete() {
        return this.b;
    }

    public final void setComplete(boolean z) {
        this.b = z;
    }

    public final void setSleepDayData(@Nullable SleepDayData sleepDayData) {
        this.f3659a = sleepDayData;
    }

    @NotNull
    public String toString() {
        return "SleepResponse(sleepDayData=" + this.f3659a + ", isComplete=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
