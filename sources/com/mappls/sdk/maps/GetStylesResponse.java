package com.mappls.sdk.maps;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
@Keep
/* loaded from: classes11.dex */
public class GetStylesResponse {
    @SerializedName("baseUrl")
    @Expose
    private String baseUrl;
    @SerializedName("baseUrl_logo")
    @Expose
    private String baseUrlLogo;
    @SerializedName("data")
    @Expose
    private List<StyleData> data = null;
    @SerializedName("logos")
    @Expose
    private List<LogoData> logoData;

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getBaseUrlLogo() {
        return this.baseUrlLogo;
    }

    public List<StyleData> getData() {
        return this.data;
    }

    public List<LogoData> getLogoData() {
        return this.logoData;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public void setBaseUrlLogo(String str) {
        this.baseUrlLogo = str;
    }

    public void setData(List<StyleData> list) {
        this.data = list;
    }

    public void setLogoData(List<LogoData> list) {
        this.logoData = list;
    }
}
