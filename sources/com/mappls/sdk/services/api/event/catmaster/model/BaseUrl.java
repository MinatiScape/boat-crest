package com.mappls.sdk.services.api.event.catmaster.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes2.dex */
public class BaseUrl {
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }
}
