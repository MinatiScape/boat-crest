package com.coveiot.android.bleabstract.response;

import com.coveiot.android.bleabstract.models.DailyCalorieData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class GetCalorieDataResponse {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3603a;
    @Nullable
    public DailyCalorieData b;

    public GetCalorieDataResponse(@Nullable DailyCalorieData dailyCalorieData) {
        this.b = dailyCalorieData;
    }

    @Nullable
    public final DailyCalorieData getCalorieData() {
        return this.b;
    }

    public final boolean isComplete() {
        return this.f3603a;
    }

    public final void setComplete(boolean z) {
        this.f3603a = z;
    }

    @NotNull
    public String toString() {
        return "CalorieDataResponse(" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
