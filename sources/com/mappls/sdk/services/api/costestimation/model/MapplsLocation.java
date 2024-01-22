package com.mappls.sdk.services.api.costestimation.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class MapplsLocation {
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }
}
