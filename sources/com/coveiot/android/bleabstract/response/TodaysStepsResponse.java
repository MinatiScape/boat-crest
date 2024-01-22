package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TodaysStepsResponse {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public TodaysStepsData f3680a;

    @Nullable
    public final TodaysStepsData getTodaysStepsData() {
        return this.f3680a;
    }

    public final void setTodaysStepsData(@Nullable TodaysStepsData todaysStepsData) {
        this.f3680a = todaysStepsData;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TodaysStepsResponse(todaysStepsData=");
        TodaysStepsData todaysStepsData = this.f3680a;
        sb.append(todaysStepsData != null ? todaysStepsData.toString() : null);
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        return sb.toString();
    }
}
