package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SScheduleInfo {
    @SerializedName("endTime")
    @Nullable
    private String endTime;
    @SerializedName("startTime")
    @Nullable
    private String startTime;

    @Nullable
    public final String getEndTime() {
        return this.endTime;
    }

    @Nullable
    public final String getStartTime() {
        return this.startTime;
    }

    public final void setEndTime(@Nullable String str) {
        this.endTime = str;
    }

    public final void setStartTime(@Nullable String str) {
        this.startTime = str;
    }
}
