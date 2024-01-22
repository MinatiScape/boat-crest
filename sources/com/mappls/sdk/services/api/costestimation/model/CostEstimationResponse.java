package com.mappls.sdk.services.api.costestimation.model;

import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public class CostEstimationResponse {
    @SerializedName("country")
    private String country;
    @SerializedName(FirebaseAnalytics.Param.CURRENCY)
    private String currency;
    @SerializedName("departureTime")
    private Long departureTime;
    @SerializedName("distance")
    private Double distance;
    @SerializedName("duration")
    private Double duration;
    @SerializedName("fuelEfficiency")
    private String fuelEfficiency;
    @SerializedName("fuelPrice")
    private Double fuelPrice;
    @SerializedName("hasTolls")
    private Boolean hasTolls;
    @SerializedName("locations")
    private List<MapplsLocation> locations;
    @SerializedName("tolls")
    private List<CostEstimationTollDetail> tolls;
    @SerializedName("totalFuelCost")
    private Integer totalFuelCost;
    @SerializedName("totalTollCost")
    private Integer totalTollCost;
    @SerializedName("totalTolls")
    private Integer totalTolls;
    @SerializedName("totalTripCostEstimate")
    private Double totalTripCostEstimate;
    @SerializedName("url")
    private String url;
    @SerializedName("vehicleFuelType")
    private String vehicleFuelType;
    @SerializedName("vehicleType")
    private String vehicleType;

    public String getCountry() {
        return this.country;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Long getDepartureTime() {
        return this.departureTime;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getDuration() {
        return this.duration;
    }

    public String getFuelEfficiency() {
        return this.fuelEfficiency;
    }

    public Double getFuelPrice() {
        return this.fuelPrice;
    }

    public Boolean getHasTolls() {
        return this.hasTolls;
    }

    public List<MapplsLocation> getLocations() {
        return this.locations;
    }

    public List<CostEstimationTollDetail> getTolls() {
        return this.tolls;
    }

    public Integer getTotalFuelCost() {
        return this.totalFuelCost;
    }

    public Integer getTotalTollCost() {
        return this.totalTollCost;
    }

    public Integer getTotalTolls() {
        return this.totalTolls;
    }

    public Double getTotalTripCostEstimate() {
        return this.totalTripCostEstimate;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVehicleFuelType() {
        return this.vehicleFuelType;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDepartureTime(Long l) {
        this.departureTime = l;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public void setFuelEfficiency(String str) {
        this.fuelEfficiency = str;
    }

    public void setFuelPrice(Double d) {
        this.fuelPrice = d;
    }

    public void setHasTolls(Boolean bool) {
        this.hasTolls = bool;
    }

    public void setLocations(List<MapplsLocation> list) {
        this.locations = list;
    }

    public void setTolls(List<CostEstimationTollDetail> list) {
        this.tolls = list;
    }

    public void setTotalFuelCost(Integer num) {
        this.totalFuelCost = num;
    }

    public void setTotalTollCost(Integer num) {
        this.totalTollCost = num;
    }

    public void setTotalTolls(Integer num) {
        this.totalTolls = num;
    }

    public void setTotalTripCostEstimate(Double d) {
        this.totalTripCostEstimate = d;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVehicleFuelType(String str) {
        this.vehicleFuelType = str;
    }

    public void setVehicleType(String str) {
        this.vehicleType = str;
    }
}
