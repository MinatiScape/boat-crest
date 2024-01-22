package com.mappls.sdk.services.api.whoami.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class LicensingOutputParams {
    @SerializedName("deviceRegion")
    @Expose
    private List<String> deviceRegion;

    public List<String> getDeviceRegion() {
        return this.deviceRegion;
    }

    public void setDeviceRegion(List<String> list) {
        this.deviceRegion = list;
    }
}
