package com.mappls.sdk.services.api.session.devicelist.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class Device {
    @SerializedName("deviceAlias")
    @Expose
    private String deviceAlias;
    @SerializedName("deviceFingerprint")
    @Expose
    private String deviceFingerprint;

    public String getDeviceAlias() {
        return this.deviceAlias;
    }

    public String getDeviceFingerprint() {
        return this.deviceFingerprint;
    }

    public void setDeviceAlias(String str) {
        this.deviceAlias = str;
    }

    public void setDeviceFingerprint(String str) {
        this.deviceFingerprint = str;
    }
}
