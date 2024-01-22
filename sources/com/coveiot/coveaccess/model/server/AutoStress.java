package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AutoStress {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("alert")
    private AutoStressAlert autoStressAlert;
    @SerializedName("interval")
    private String interval;

    public Boolean getActive() {
        return this.active;
    }

    public AutoStressAlert getAlert() {
        return this.autoStressAlert;
    }

    public String getInterval() {
        return this.interval;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setAlert(AutoStressAlert autoStressAlert) {
        this.autoStressAlert = autoStressAlert;
    }

    public void setInterval(String str) {
        this.interval = str;
    }
}
