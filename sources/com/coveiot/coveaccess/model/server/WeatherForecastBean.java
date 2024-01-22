package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class WeatherForecastBean {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("unitSystem")
    private String unitSystem;

    public Boolean getActive() {
        return this.active;
    }

    public String getUnitSystem() {
        return this.unitSystem;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setUnitSystem(String str) {
        this.unitSystem = str;
    }
}
