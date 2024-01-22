package com.mappls.sdk.services.api.geolocation.model;

import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes6.dex */
public class GeolocationResponse {
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    @Expose
    private Geolocation location;

    public Geolocation getLocation() {
        return this.location;
    }

    public void setLocation(Geolocation geolocation) {
        this.location = geolocation;
    }
}
