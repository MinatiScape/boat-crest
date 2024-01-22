package com.mappls.sdk.services.api.predictive.distance.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDistanceResponse {
    @SerializedName("sources")
    @Expose
    private List<List<PredictiveDistanceLocation>> sources;
    @SerializedName("sources_to_targets")
    @Expose
    private List<List<PredictiveDistanceResults>> sourcesToTargets;
    @SerializedName("targets")
    @Expose
    private List<List<PredictiveDistanceLocation>> targets;
    @SerializedName("units")
    @Expose
    private String units;

    public List<List<PredictiveDistanceLocation>> getSources() {
        return this.sources;
    }

    public List<List<PredictiveDistanceResults>> getSourcesToTargets() {
        return this.sourcesToTargets;
    }

    public List<List<PredictiveDistanceLocation>> getTargets() {
        return this.targets;
    }

    public String getUnits() {
        return this.units;
    }

    public void setSources(List<List<PredictiveDistanceLocation>> list) {
        this.sources = list;
    }

    public void setSourcesToTargets(List<List<PredictiveDistanceResults>> list) {
        this.sourcesToTargets = list;
    }

    public void setTargets(List<List<PredictiveDistanceLocation>> list) {
        this.targets = list;
    }

    public void setUnits(String str) {
        this.units = str;
    }
}
