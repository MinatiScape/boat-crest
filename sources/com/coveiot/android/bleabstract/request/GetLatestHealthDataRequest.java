package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.HealthVitalsType;
/* loaded from: classes2.dex */
public class GetLatestHealthDataRequest extends BleBaseRequest {
    public HealthVitalsType f;

    public GetLatestHealthDataRequest(HealthVitalsType healthVitalsType) {
        this.f = healthVitalsType;
    }

    public HealthVitalsType getHealthVitalsType() {
        return this.f;
    }
}
