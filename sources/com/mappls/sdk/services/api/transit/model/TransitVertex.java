package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
public class TransitVertex {
    @SerializedName("arrival")
    private Long arrival;
    @SerializedName("departure")
    private Long departure;
    @SerializedName("lat")
    private Double latitude;
    @SerializedName("lon")
    private Double longitude;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName("stopId")
    private Long stopId;
    @SerializedName("stopIndex")
    private Integer stopIndex;
    @SerializedName("stopSequence")
    private Integer stopSequence;
    @SerializedName("vertexType")
    private String vertexType;

    public Long getArrival() {
        return this.arrival;
    }

    public Long getDeparture() {
        return this.departure;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getName() {
        return this.name;
    }

    public Long getStopId() {
        return this.stopId;
    }

    public Integer getStopIndex() {
        return this.stopIndex;
    }

    public Integer getStopSequence() {
        return this.stopSequence;
    }

    public String getVertexType() {
        return this.vertexType;
    }

    public void setArrival(Long l) {
        this.arrival = l;
    }

    public void setDeparture(Long l) {
        this.departure = l;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setStopId(Long l) {
        this.stopId = l;
    }

    public void setStopIndex(Integer num) {
        this.stopIndex = num;
    }

    public void setStopSequence(Integer num) {
        this.stopSequence = num;
    }

    public void setVertexType(String str) {
        this.vertexType = str;
    }
}
