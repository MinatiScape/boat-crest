package com.mappls.sdk.services.api.traffic.model;

import androidx.annotation.Keep;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
@Keep
/* loaded from: classes8.dex */
public class TrafficRoadDetailResult {
    @SerializedName("avg_spd")
    @Expose
    private Integer averageSpeed;
    @SerializedName(GeoCodingCriteria.POD_CITY)
    @Expose
    private String city;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("formOfWay")
    @Expose
    private String formOfWay;
    @SerializedName("geometry")
    @Expose
    private String geometry;
    @SerializedName("divider")
    @Expose
    private Boolean isDividerExist;
    @SerializedName("multi_cw")
    @Expose
    private Boolean isMultiCarriageWay;
    @SerializedName("oneway")
    @Expose
    private Boolean isOneWay;
    @SerializedName("shoulder")
    @Expose
    private Boolean isShoulderLaneExist;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;
    @SerializedName("numOfLanes")
    @Expose
    private Integer numberOfLanes;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("roadClass")
    @Expose
    private String roadClass;
    @SerializedName("routeNo")
    @Expose
    private String routeNumber;
    @SerializedName("spd_lmt")
    @Expose
    private Integer speedLimit;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("trafficStatus")
    @Expose
    private String trafficStatus;
    @SerializedName("trafficType")
    @Expose
    private Integer trafficType;

    public Integer getAverageSpeed() {
        return this.averageSpeed;
    }

    public String getCity() {
        return this.city;
    }

    public Double getDistance() {
        return this.distance;
    }

    public String getDistrict() {
        return this.district;
    }

    public Boolean getDividerExist() {
        return this.isDividerExist;
    }

    public String getFormOfWay() {
        return this.formOfWay;
    }

    public String getGeometry() {
        return this.geometry;
    }

    public Boolean getMultiCarriageWay() {
        return this.isMultiCarriageWay;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNumberOfLanes() {
        return this.numberOfLanes;
    }

    public Boolean getOneWay() {
        return this.isOneWay;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getRoadClass() {
        return this.roadClass;
    }

    public String getRouteNumber() {
        return this.routeNumber;
    }

    public Boolean getShoulderLaneExist() {
        return this.isShoulderLaneExist;
    }

    public Integer getSpeedLimit() {
        return this.speedLimit;
    }

    public String getState() {
        return this.state;
    }

    public String getTrafficStatus() {
        return this.trafficStatus;
    }

    public Integer getTrafficType() {
        return this.trafficType;
    }

    public void setAverageSpeed(Integer num) {
        this.averageSpeed = num;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public void setDividerExist(Boolean bool) {
        this.isDividerExist = bool;
    }

    public void setFormOfWay(String str) {
        this.formOfWay = str;
    }

    public void setGeometry(String str) {
        this.geometry = str;
    }

    public void setMultiCarriageWay(Boolean bool) {
        this.isMultiCarriageWay = bool;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNumberOfLanes(Integer num) {
        this.numberOfLanes = num;
    }

    public void setOneWay(Boolean bool) {
        this.isOneWay = bool;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public void setRoadClass(String str) {
        this.roadClass = str;
    }

    public void setRouteNumber(String str) {
        this.routeNumber = str;
    }

    public void setShoulderLaneExist(Boolean bool) {
        this.isShoulderLaneExist = bool;
    }

    public void setSpeedLimit(Integer num) {
        this.speedLimit = num;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setTrafficStatus(String str) {
        this.trafficStatus = str;
    }

    public void setTrafficType(Integer num) {
        this.trafficType = num;
    }
}
