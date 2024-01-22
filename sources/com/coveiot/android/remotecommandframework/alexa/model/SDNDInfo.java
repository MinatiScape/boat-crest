package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SDNDInfo extends SCommandInfo {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean active = true;
    @SerializedName("schedules")
    @Nullable
    private List<SScheduleInfo> schedules;

    public final boolean getActive() {
        return this.active;
    }

    @Nullable
    public final List<SScheduleInfo> getSchedules() {
        return this.schedules;
    }

    public final void setActive(boolean z) {
        this.active = z;
    }

    public final void setSchedules(@Nullable List<SScheduleInfo> list) {
        this.schedules = list;
    }
}
