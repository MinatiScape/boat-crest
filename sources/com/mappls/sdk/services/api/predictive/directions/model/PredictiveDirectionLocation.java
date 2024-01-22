package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionLocation {
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    private String city;
    @SerializedName("lat")
    @Expose
    private Double latitude;
    @SerializedName("lon")
    @Expose
    private Double longitude;
    @SerializedName("original_index")
    @Expose
    private Integer originalIndex;
    @SerializedName("type")
    @Expose
    private String type;

    public String getCity() {
        return this.city;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public Integer getOriginalIndex() {
        return this.originalIndex;
    }

    public String getType() {
        return this.type;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setOriginalIndex(Integer num) {
        this.originalIndex = num;
    }

    public void setType(String str) {
        this.type = str;
    }
}
