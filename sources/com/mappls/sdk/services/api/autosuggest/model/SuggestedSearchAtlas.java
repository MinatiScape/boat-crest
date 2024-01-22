package com.mappls.sdk.services.api.autosuggest.model;

import androidx.annotation.Keep;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class SuggestedSearchAtlas {
    @SerializedName("hyperLink")
    @Expose
    public String hyperLink;
    @SerializedName("identifier")
    @Expose
    public String identifier;
    @SerializedName("keyword")
    @Expose
    public String keyword;
    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    @Expose
    public String location;
    @SerializedName(alternate = {"eLoc"}, value = "mapplsPin")
    @Expose
    private String mapplsPin;
    @SerializedName("orderIndex")
    @Expose
    public long orderIndex;

    public String getHyperLink() {
        return this.hyperLink;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public long getOrderIndex() {
        return this.orderIndex;
    }

    public String getSearchStringToShow() {
        return this.keyword + HexStringBuilder.DEFAULT_SEPARATOR + this.identifier + HexStringBuilder.DEFAULT_SEPARATOR + this.location;
    }

    public void setHyperLink(String str) {
        this.hyperLink = str;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setOrderIndex(long j) {
        this.orderIndex = j;
    }
}
