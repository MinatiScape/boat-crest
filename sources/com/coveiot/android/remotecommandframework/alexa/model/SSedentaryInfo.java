package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SSedentaryInfo extends SCommandInfo {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    @Nullable
    private Boolean active;
    @SerializedName("endTime")
    @Nullable
    private String endTime;
    @SerializedName("interval")
    @Nullable
    private String interval = "00:60:00";
    @SerializedName("startTime")
    @Nullable
    private String startTime;

    @Nullable
    public final Boolean getActive() {
        return this.active;
    }

    @Nullable
    public final String getEndTime() {
        return this.endTime;
    }

    @Nullable
    public final String getInterval() {
        return this.interval;
    }

    @Nullable
    public final String getStartTime() {
        return this.startTime;
    }

    public final void setActive(@Nullable Boolean bool) {
        this.active = bool;
    }

    public final void setEndTime(@Nullable String str) {
        this.endTime = str;
    }

    public final void setInterval(@Nullable String str) {
        this.interval = str;
    }

    public final void setStartTime(@Nullable String str) {
        this.startTime = str;
    }
}
