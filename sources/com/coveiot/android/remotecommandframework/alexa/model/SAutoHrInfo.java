package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SAutoHrInfo extends SCommandInfo {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    @Nullable
    private Boolean active;
    @SerializedName("interval")
    @Nullable
    private String interval;

    @Nullable
    public final Boolean getActive() {
        return this.active;
    }

    @Nullable
    public final String getInterval() {
        return this.interval;
    }

    public final void setActive(@Nullable Boolean bool) {
        this.active = bool;
    }

    public final void setInterval(@Nullable String str) {
        this.interval = str;
    }
}
