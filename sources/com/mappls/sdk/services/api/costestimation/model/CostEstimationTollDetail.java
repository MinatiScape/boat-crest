package com.mappls.sdk.services.api.costestimation.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class CostEstimationTollDetail {
    @SerializedName("address")
    private String address;
    @SerializedName("agency")
    private String agency;
    @SerializedName("amenities")
    private List<String> amenities;
    @SerializedName("averageWaitTimeRange")
    private Double averageWaitTimeRange;
    @SerializedName("cost")
    private Integer cost;
    @SerializedName("distance")
    private Double distance;
    @SerializedName("duration")
    private Double duration;
    @SerializedName("emergency")
    private String emergency;
    @SerializedName("lanes")
    private Integer lanes;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName(alternate = {"eloc"}, value = "mapplsPin")
    private String mapplsPin;
    @SerializedName("node")
    private Double node;
    @SerializedName("nodeIdx")
    private Integer nodeIdx;
    @SerializedName("payment")
    private List<String> payment;
    @SerializedName("road")
    private String road;
    @SerializedName("roadType")
    private String roadType;
    @SerializedName("state")
    private String state;
    @SerializedName("tollGrpId")
    private Integer tollGrpId;
    @SerializedName("tollName")
    private String tollName;
    @SerializedName("type")
    private String type;

    public String getAddress() {
        return this.address;
    }

    public String getAgency() {
        return this.agency;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public Double getAverageWaitTimeRange() {
        return this.averageWaitTimeRange;
    }

    public Integer getCost() {
        return this.cost;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getDuration() {
        return this.duration;
    }

    public String getEmergency() {
        return this.emergency;
    }

    public Integer getLanes() {
        return this.lanes;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public Double getNode() {
        return this.node;
    }

    public Integer getNodeIdx() {
        return this.nodeIdx;
    }

    public List<String> getPayment() {
        return this.payment;
    }

    public String getRoad() {
        return this.road;
    }

    public String getRoadType() {
        return this.roadType;
    }

    public String getState() {
        return this.state;
    }

    public Integer getTollGrpId() {
        return this.tollGrpId;
    }

    public String getTollName() {
        return this.tollName;
    }

    public String getType() {
        return this.type;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setAgency(String str) {
        this.agency = str;
    }

    public void setAmenities(List<String> list) {
        this.amenities = list;
    }

    public void setAverageWaitTimeRange(Double d) {
        this.averageWaitTimeRange = d;
    }

    public void setCost(Integer num) {
        this.cost = num;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public void setEmergency(String str) {
        this.emergency = str;
    }

    public void setLanes(Integer num) {
        this.lanes = num;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setNode(Double d) {
        this.node = d;
    }

    public void setNodeIdx(Integer num) {
        this.nodeIdx = num;
    }

    public void setPayment(List<String> list) {
        this.payment = list;
    }

    public void setRoad(String str) {
        this.road = str;
    }

    public void setRoadType(String str) {
        this.roadType = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setTollGrpId(Integer num) {
        this.tollGrpId = num;
    }

    public void setTollName(String str) {
        this.tollName = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
