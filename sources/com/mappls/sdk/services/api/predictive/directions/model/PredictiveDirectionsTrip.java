package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionsTrip {
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("legs")
    @Expose
    private List<PredictiveDirectionsLeg> legs;
    @SerializedName("locations")
    @Expose
    private List<PredictiveDirectionLocation> locations;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private Integer status;
    @SerializedName("status_message")
    @Expose
    private String statusMessage;
    @SerializedName(ErrorBundle.SUMMARY_ENTRY)
    @Expose
    private PredictiveDirectionSummary summary;
    @SerializedName("units")
    @Expose
    private String units;

    public String getLanguage() {
        return this.language;
    }

    public List<PredictiveDirectionsLeg> getLegs() {
        return this.legs;
    }

    public List<PredictiveDirectionLocation> getLocations() {
        return this.locations;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public PredictiveDirectionSummary getSummary() {
        return this.summary;
    }

    public String getUnits() {
        return this.units;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setLegs(List<PredictiveDirectionsLeg> list) {
        this.legs = list;
    }

    public void setLocations(List<PredictiveDirectionLocation> list) {
        this.locations = list;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }

    public void setSummary(PredictiveDirectionSummary predictiveDirectionSummary) {
        this.summary = predictiveDirectionSummary;
    }

    public void setUnits(String str) {
        this.units = str;
    }
}
