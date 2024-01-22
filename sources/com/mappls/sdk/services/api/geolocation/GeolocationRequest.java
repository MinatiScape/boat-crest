package com.mappls.sdk.services.api.geolocation;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes6.dex */
class GeolocationRequest {
    private final Integer cellId;
    private final Integer locationAreaCode;
    private final Integer mobileCountryCode;
    private final Integer mobileNetworkCode;

    public GeolocationRequest(Integer num, Integer num2, Integer num3, Integer num4) {
        this.cellId = num;
        this.locationAreaCode = num2;
        this.mobileCountryCode = num3;
        this.mobileNetworkCode = num4;
    }

    public Integer getCellId() {
        return this.cellId;
    }

    public Integer getLocationAreaCode() {
        return this.locationAreaCode;
    }

    public Integer getMobileCountryCode() {
        return this.mobileCountryCode;
    }

    public Integer getMobileNetworkCode() {
        return this.mobileNetworkCode;
    }
}
