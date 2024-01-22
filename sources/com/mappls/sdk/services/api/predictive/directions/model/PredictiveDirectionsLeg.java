package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionsLeg {
    @SerializedName("maneuvers")
    @Expose
    private List<PredictiveDirectionManeuver> maneuvers;
    @SerializedName("shape")
    @Expose
    private String shape;
    @SerializedName(ErrorBundle.SUMMARY_ENTRY)
    @Expose
    private PredictiveDirectionSummary summary;

    public List<PredictiveDirectionManeuver> getManeuvers() {
        return this.maneuvers;
    }

    public String getShape() {
        return this.shape;
    }

    public PredictiveDirectionSummary getSummary() {
        return this.summary;
    }

    public void setManeuvers(List<PredictiveDirectionManeuver> list) {
        this.maneuvers = list;
    }

    public void setShape(String str) {
        this.shape = str;
    }

    public void setSummary(PredictiveDirectionSummary predictiveDirectionSummary) {
        this.summary = predictiveDirectionSummary;
    }
}
