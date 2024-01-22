package com.mappls.sdk.maps;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class LogoData {
    @SerializedName("logoId")
    @Expose
    private String logoId;
    @SerializedName("url")
    @Expose
    private String logoUrl;
    @SerializedName("modified")
    @Expose
    private Long modified;

    public String getLogoId() {
        return this.logoId;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public Long getModified() {
        return this.modified;
    }

    public void setLogoId(String str) {
        this.logoId = str;
    }

    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    public void setModified(Long l) {
        this.modified = l;
    }
}
