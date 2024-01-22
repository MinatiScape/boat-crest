package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.DailyDistanceData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetDistanceDataResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3607a;
    @Nullable
    public DailyDistanceData b;

    public GetDistanceDataResponse(@Nullable DailyDistanceData dailyDistanceData) {
        this.b = dailyDistanceData;
    }

    @Nullable
    public final DailyDistanceData getDistanceDataData() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3607a;
    }

    public final void setComplete(boolean z) {
        this.f3607a = z;
    }

    @NotNull
    public String toString() {
        return "DistanceDataResponse(" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
