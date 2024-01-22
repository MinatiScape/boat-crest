package com.mappls.sdk.services.api.sdkconfig;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes7.dex */
public class ServerInfo {
    @SerializedName("ch")
    @Expose
    private List<String> ch;
    @SerializedName("expiry")
    @Expose
    private Long expiry;
    @SerializedName("baseUrl")
    @Expose
    private UrlData urlData;

    public List<String> getCh() {
        return this.ch;
    }

    public Long getExpiry() {
        return this.expiry;
    }

    public UrlData getUrlData() {
        return this.urlData;
    }

    public void setCh(List<String> list) {
        this.ch = list;
    }

    public void setExpiry(Long l) {
        this.expiry = l;
    }

    public void setUrlData(UrlData urlData) {
        this.urlData = urlData;
    }
}
