package com.mappls.sdk.services.api.whoami.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class LicensingHeader {
    @SerializedName("Authorization")
    @Expose
    private Boolean authorization;
    @SerializedName("x-dh")
    @Expose
    private Boolean xDh;
    @SerializedName("x-ms-seh")
    @Expose
    private Boolean xMsSeh;

    public Boolean getAuthorization() {
        return this.authorization;
    }

    public Boolean getxDh() {
        return this.xDh;
    }

    public Boolean getxMsSeh() {
        return this.xMsSeh;
    }

    public void setAuthorization(Boolean bool) {
        this.authorization = bool;
    }

    public void setxDh(Boolean bool) {
        this.xDh = bool;
    }

    public void setxMsSeh(Boolean bool) {
        this.xMsSeh = bool;
    }
}
