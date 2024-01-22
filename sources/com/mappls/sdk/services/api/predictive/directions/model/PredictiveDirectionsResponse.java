package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionsResponse {
    @SerializedName("alternates")
    @Expose
    private List<PredictiveDirectionsResponse> alternates;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trip")
    @Expose
    private PredictiveDirectionsTrip trip;

    public List<PredictiveDirectionsResponse> getAlternates() {
        return this.alternates;
    }

    public String getId() {
        return this.id;
    }

    public PredictiveDirectionsTrip getTrip() {
        return this.trip;
    }

    public void setAlternates(List<PredictiveDirectionsResponse> list) {
        this.alternates = list;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTrip(PredictiveDirectionsTrip predictiveDirectionsTrip) {
        this.trip = predictiveDirectionsTrip;
    }
}
