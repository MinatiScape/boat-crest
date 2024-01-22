package com.mappls.sdk.services.api.alongroute.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class POIAlongRouteResponse {
    @SerializedName("suggestedPOIs")
    @Expose
    private List<SuggestedPOI> suggestedPOIs = null;

    public List<SuggestedPOI> getSuggestedPOIs() {
        return this.suggestedPOIs;
    }

    public void setSuggestedPOIs(List<SuggestedPOI> list) {
        this.suggestedPOIs = list;
    }
}
