package com.mappls.sdk.services.api.sdkconfig.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes7.dex */
public class SDKConfigResponse {
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("environment")
    @Expose
    private String environment;
    @SerializedName("version")
    @Expose
    private String version;

    public String getData() {
        return this.data;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getVersion() {
        return this.version;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
