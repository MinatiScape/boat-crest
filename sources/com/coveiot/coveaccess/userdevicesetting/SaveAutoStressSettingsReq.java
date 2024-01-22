package com.coveiot.coveaccess.userdevicesetting;

import com.coveiot.coveaccess.model.server.AutoStressAlert;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SaveAutoStressSettingsReq {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Boolean f6876a;
    @SerializedName("interval")
    private String b;
    @SerializedName("alert")
    @Expose
    private AutoStressAlert c;

    public Boolean getActive() {
        return this.f6876a;
    }

    public AutoStressAlert getAlert() {
        return this.c;
    }

    public String getInterval() {
        return this.b;
    }

    public void setActive(Boolean bool) {
        this.f6876a = bool;
    }

    public void setAlert(AutoStressAlert autoStressAlert) {
        this.c = autoStressAlert;
    }

    public void setInterval(String str) {
        this.b = str;
    }
}
