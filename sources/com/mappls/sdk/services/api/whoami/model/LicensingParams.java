package com.mappls.sdk.services.api.whoami.model;

import androidx.annotation.Keep;
import com.coveiot.android.tappy.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class LicensingParams {
    @SerializedName("clusterId")
    @Expose
    private Boolean clusterDevice;
    @SerializedName("deviceFingerprint")
    @Expose
    private Boolean deviceFingerprint;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    private Boolean userId;
    @SerializedName("vin")
    @Expose
    private Boolean vin;

    public Boolean getClusterDevice() {
        return this.clusterDevice;
    }

    public Boolean getDeviceFingerprint() {
        return this.deviceFingerprint;
    }

    public Boolean getUserId() {
        return this.userId;
    }

    public Boolean getVin() {
        return this.vin;
    }

    public void setClusterDevice(Boolean bool) {
        this.clusterDevice = bool;
    }

    public void setDeviceFingerprint(Boolean bool) {
        this.deviceFingerprint = bool;
    }

    public void setUserId(Boolean bool) {
        this.userId = bool;
    }

    public void setVin(Boolean bool) {
        this.vin = bool;
    }
}
