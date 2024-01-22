package com.mappls.sdk.services.api.autosuggest.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class AtlasExplaination {
    @SerializedName("isKeyword")
    @Expose
    private boolean isKeyword;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("refLocation")
    @Expose
    private String refLocation;

    public String getKeyword() {
        return this.keyword;
    }

    public String getRefLocation() {
        return this.refLocation;
    }

    public boolean isIsKeyword() {
        return this.isKeyword;
    }
}
