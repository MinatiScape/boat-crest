package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AutoSpo2Settings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

    /* renamed from: a  reason: collision with root package name */
    private boolean f6889a;

    public boolean isActive() {
        return this.f6889a;
    }

    public void setActive(boolean z) {
        this.f6889a = z;
    }

    public String toString() {
        return "AutoSpo2Settings{active=" + this.f6889a + '}';
    }
}
