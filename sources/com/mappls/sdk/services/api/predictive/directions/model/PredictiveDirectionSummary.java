package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionSummary {
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("has_time_restrictions")
    @Expose
    private Boolean hasTimeRestrictions;
    @SerializedName("length")
    @Expose
    private Double length;
    @SerializedName("max_lat")
    @Expose
    private Double maximumLatitude;
    @SerializedName("max_lon")
    @Expose
    private Double maximumLongitude;
    @SerializedName("min_lat")
    @Expose
    private Double minimumLatitude;
    @SerializedName("min_lon")
    @Expose
    private Double minimumLongitude;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose
    private Double time;

    public Double getCost() {
        return this.cost;
    }

    public Boolean getHasTimeRestrictions() {
        return this.hasTimeRestrictions;
    }

    public Double getLength() {
        return this.length;
    }

    public Double getMaximumLatitude() {
        return this.maximumLatitude;
    }

    public Double getMaximumLongitude() {
        return this.maximumLongitude;
    }

    public Double getMinimumLatitude() {
        return this.minimumLatitude;
    }

    public Double getMinimumLongitude() {
        return this.minimumLongitude;
    }

    public Double getTime() {
        return this.time;
    }

    public void setCost(Double d) {
        this.cost = d;
    }

    public void setHasTimeRestrictions(Boolean bool) {
        this.hasTimeRestrictions = bool;
    }

    public void setLength(Double d) {
        this.length = d;
    }

    public void setMaximumLatitude(Double d) {
        this.maximumLatitude = d;
    }

    public void setMaximumLongitude(Double d) {
        this.maximumLongitude = d;
    }

    public void setMinimumLatitude(Double d) {
        this.minimumLatitude = d;
    }

    public void setMinimumLongitude(Double d) {
        this.minimumLongitude = d;
    }

    public void setTime(Double d) {
        this.time = d;
    }
}
