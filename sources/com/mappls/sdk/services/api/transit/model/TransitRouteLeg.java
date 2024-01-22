package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class TransitRouteLeg {
    @SerializedName("arrivalDelay")
    private Integer arrivalDelay;
    @SerializedName("departureDelay")
    private Integer departureDelay;
    @SerializedName("distance")
    private Double distance;
    @SerializedName("duration")
    private Double duration;
    @SerializedName("endTime")
    private Long endTime;
    @SerializedName("from")
    private TransitVertex from;
    @SerializedName("interlineWithPreviousLeg")
    private Boolean interlineWithPreviousLeg;
    @SerializedName("legGeometry")
    private TransitLegGeometry legGeometry;
    @SerializedName("mode")
    private String mode;
    @SerializedName("realTime")
    private Boolean realTime;
    @SerializedName("route")
    private String route;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("steps")
    private List<TransitLegStep> steps;
    @SerializedName(TypedValues.TransitionType.S_TO)
    private TransitVertex to;
    @SerializedName("transitLeg")
    private Boolean transitLeg;

    public Integer getArrivalDelay() {
        return this.arrivalDelay;
    }

    public Integer getDepartureDelay() {
        return this.departureDelay;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getDuration() {
        return this.duration;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public TransitVertex getFrom() {
        return this.from;
    }

    public Boolean getInterlineWithPreviousLeg() {
        return this.interlineWithPreviousLeg;
    }

    public TransitLegGeometry getLegGeometry() {
        return this.legGeometry;
    }

    public String getMode() {
        return this.mode;
    }

    public Boolean getRealTime() {
        return this.realTime;
    }

    public String getRoute() {
        return this.route;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public List<TransitLegStep> getSteps() {
        return this.steps;
    }

    public TransitVertex getTo() {
        return this.to;
    }

    public Boolean getTransitLeg() {
        return this.transitLeg;
    }

    public void setArrivalDelay(Integer num) {
        this.arrivalDelay = num;
    }

    public void setDepartureDelay(Integer num) {
        this.departureDelay = num;
    }

    public void setDistance(Double d) {
        this.distance = d;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public void setFrom(TransitVertex transitVertex) {
        this.from = transitVertex;
    }

    public void setInterlineWithPreviousLeg(Boolean bool) {
        this.interlineWithPreviousLeg = bool;
    }

    public void setLegGeometry(TransitLegGeometry transitLegGeometry) {
        this.legGeometry = transitLegGeometry;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public void setRealTime(Boolean bool) {
        this.realTime = bool;
    }

    public void setRoute(String str) {
        this.route = str;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public void setSteps(List<TransitLegStep> list) {
        this.steps = list;
    }

    public void setTo(TransitVertex transitVertex) {
        this.to = transitVertex;
    }

    public void setTransitLeg(Boolean bool) {
        this.transitLeg = bool;
    }
}
