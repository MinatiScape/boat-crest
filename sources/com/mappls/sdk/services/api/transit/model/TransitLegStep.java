package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitLegStep {
    @SerializedName("absoluteDirection")
    private String absoluteDirection;
    @SerializedName("distance")
    private Double distance;
    @SerializedName("lat")
    private Double latitude;
    @SerializedName("lon")
    private Double longitude;
    @SerializedName("relativeDirection")
    private String relativeDirection;
    @SerializedName("stayOn")
    private Boolean stayOn;
    @SerializedName("streetName")
    private String streetName;

    public String getAbsoluteDirection() {
        return this.absoluteDirection;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getRelativeDirection() {
        return this.relativeDirection;
    }

    public Boolean getStayOn() {
        return this.stayOn;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setAbsoluteDirection(String str) {
        this.absoluteDirection = str;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setRelativeDirection(String str) {
        this.relativeDirection = str;
    }

    public void setStayOn(Boolean bool) {
        this.stayOn = bool;
    }

    public void setStreetName(String str) {
        this.streetName = str;
    }
}
