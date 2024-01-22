package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class TransitItinerary {
    @SerializedName("duration")
    private Double duration;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("transfers")
    private Integer transfers;
    @SerializedName("fare")
    private TransitFare transitFare;
    @SerializedName("legs")
    private List<TransitRouteLeg> transitRouteLegs;
    @SerializedName("transitTime")
    private Integer transitTime;
    @SerializedName("waitingTime")
    private Integer waitingTime;
    @SerializedName("walkDistance")
    private Double walkDistance;
    @SerializedName("walkTime")
    private Integer walkTime;

    public Double getDuration() {
        return this.duration;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Integer getTransfers() {
        return this.transfers;
    }

    public TransitFare getTransitFare() {
        return this.transitFare;
    }

    public List<TransitRouteLeg> getTransitLegSteps() {
        return this.transitRouteLegs;
    }

    public Integer getTransitTime() {
        return this.transitTime;
    }

    public Integer getWaitingTime() {
        return this.waitingTime;
    }

    public Double getWalkDistance() {
        return this.walkDistance;
    }

    public Integer getWalkTime() {
        return this.walkTime;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public void setTransfers(Integer num) {
        this.transfers = num;
    }

    public void setTransitFare(TransitFare transitFare) {
        this.transitFare = transitFare;
    }

    public void setTransitLegSteps(List<TransitRouteLeg> list) {
        this.transitRouteLegs = list;
    }

    public void setTransitTime(Integer num) {
        this.transitTime = num;
    }

    public void setWaitingTime(Integer num) {
        this.waitingTime = num;
    }

    public void setWalkDistance(Double d) {
        this.walkDistance = d;
    }

    public void setWalkTime(Integer num) {
        this.walkTime = num;
    }
}
