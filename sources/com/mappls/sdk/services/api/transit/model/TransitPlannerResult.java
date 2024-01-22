package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitPlannerResult {
    @SerializedName("plan")
    private TransitPlan transitPlan;

    public TransitPlan getTransitPlan() {
        return this.transitPlan;
    }

    public void setTransitPlan(TransitPlan transitPlan) {
        this.transitPlan = transitPlan;
    }
}
