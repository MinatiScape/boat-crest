package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitPlannerResponse {
    @SerializedName("responseCode")
    private Integer responseCode;
    @SerializedName("results")
    private TransitPlannerResult result;

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public TransitPlannerResult getResult() {
        return this.result;
    }

    public void setResponseCode(Integer num) {
        this.responseCode = num;
    }

    public void setResult(TransitPlannerResult transitPlannerResult) {
        this.result = transitPlannerResult;
    }
}
