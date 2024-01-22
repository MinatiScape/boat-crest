package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class RespiratoryRateSettings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

    /* renamed from: a  reason: collision with root package name */
    private boolean f6894a;

    public boolean isActive() {
        return this.f6894a;
    }

    public void setActive(boolean z) {
        this.f6894a = z;
    }

    public String toString() {
        return "RespiratoryRateSettings{active=" + this.f6894a + '}';
    }
}
