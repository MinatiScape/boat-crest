package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AmbientSoundSettings {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

    /* renamed from: a  reason: collision with root package name */
    private boolean f6888a;

    public boolean isActive() {
        return this.f6888a;
    }

    public void setActive(boolean z) {
        this.f6888a = z;
    }

    public String toString() {
        return "AmbientSoundSettings{active=" + this.f6888a + '}';
    }
}
