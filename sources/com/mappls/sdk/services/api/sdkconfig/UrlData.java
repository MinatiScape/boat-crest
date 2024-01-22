package com.mappls.sdk.services.api.sdkconfig;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.predictive.distance.PredictiveDistanceCriteria;
@Keep
/* loaded from: classes7.dex */
public class UrlData {
    @SerializedName("anchor")
    @Expose
    private String anchorUrl;
    @SerializedName("apis")
    @Expose
    private String apisUrl;
    @SerializedName("atlas")
    @Expose
    private String atlasUrl;
    @SerializedName("explore")
    @Expose
    private String exploreUrl;
    @SerializedName("intouch")
    @Expose
    private String intouchUrl;
    @SerializedName("lms")
    @Expose
    private String lmsUrl;
    @SerializedName("mgis_api")
    @Expose
    private String mgisApiUrl;
    @SerializedName("mgis")
    @Expose
    private String mgisUrl;
    @SerializedName("outpost")
    @Expose
    private String outpostUrl;
    @SerializedName(PredictiveDistanceCriteria.SPEED_TYPES_TRAFFIC)
    @Expose
    private String trafficUrl;

    public String getAnchorUrl() {
        return this.anchorUrl;
    }

    public String getApisUrl() {
        return this.apisUrl;
    }

    public String getAtlasUrl() {
        return this.atlasUrl;
    }

    public String getExploreUrl() {
        return this.exploreUrl;
    }

    public String getIntouchUrl() {
        return this.intouchUrl;
    }

    public String getLmsUrl() {
        return this.lmsUrl;
    }

    public String getMgisApiUrl() {
        return this.mgisApiUrl;
    }

    public String getMgisUrl() {
        return this.mgisUrl;
    }

    public String getOutpostUrl() {
        return this.outpostUrl;
    }

    public String getTrafficUrl() {
        return this.trafficUrl;
    }

    public void setAnchorUrl(String str) {
        this.anchorUrl = str;
    }

    public void setApisUrl(String str) {
        this.apisUrl = str;
    }

    public void setAtlasUrl(String str) {
        this.atlasUrl = str;
    }

    public void setExploreUrl(String str) {
        this.exploreUrl = str;
    }

    public void setIntouchUrl(String str) {
        this.intouchUrl = str;
    }

    public void setLmsUrl(String str) {
        this.lmsUrl = str;
    }

    public void setMgisApiUrl(String str) {
        this.mgisApiUrl = str;
    }

    public void setMgisUrl(String str) {
        this.mgisUrl = str;
    }

    public void setOutpostUrl(String str) {
        this.outpostUrl = str;
    }

    public void setTrafficUrl(String str) {
        this.trafficUrl = str;
    }
}
