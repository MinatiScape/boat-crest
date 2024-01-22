package com.mappls.sdk.services.api.nearby.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.autosuggest.model.AtlasExplaination;
import java.util.ArrayList;
@Keep
/* loaded from: classes6.dex */
public class NearbyAtlasResponse {
    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("suggestedLocations")
    @Expose
    private ArrayList<NearbyAtlasResult> suggestedLocations = null;
    @SerializedName("explanation")
    @Expose
    private AtlasExplaination explaintion = null;

    public AtlasExplaination getExplaintion() {
        return this.explaintion;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public ArrayList<NearbyAtlasResult> getSuggestedLocations() {
        return this.suggestedLocations;
    }

    public void setExplaintion(AtlasExplaination atlasExplaination) {
        this.explaintion = atlasExplaination;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setSuggestedLocations(ArrayList<NearbyAtlasResult> arrayList) {
        this.suggestedLocations = arrayList;
    }
}
