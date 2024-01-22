package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivityLapsData {
    @SerializedName("lapNumber")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6381a;
    @SerializedName("totalDistance")
    @Expose
    private Integer b;
    @SerializedName("splitTime")
    @Expose
    private Integer c;
    @SerializedName("activeTime")
    @Expose
    private Integer d;
    @SerializedName("totalCalories")
    @Expose
    private Float e;
    @SerializedName("hrZoneDurations")
    @Expose
    private List<Integer> f = null;
    @SerializedName("avgSpeed")
    @Expose
    private Float g;
    @SerializedName("maxSpeed")
    @Expose
    private Float h;
    @SerializedName("avgPower")
    @Expose
    private Float i;
    @SerializedName("avgCadence")
    @Expose
    private Float j;
    @SerializedName("maxCadence")
    @Expose
    private Float k;
    @SerializedName("avgHr")
    @Expose
    private Integer l;
    @SerializedName("maxHr")
    @Expose
    private Integer m;
    @SerializedName("maxPower")
    @Expose
    public Integer maxPower;
    @SerializedName("totalSteps")
    @Expose
    private Integer n;

    public Integer getActiveTime() {
        return this.d;
    }

    public Float getAvgCadence() {
        return this.j;
    }

    public Integer getAvgHr() {
        return this.l;
    }

    public Float getAvgPower() {
        return this.i;
    }

    public Float getAvgSpeed() {
        return this.g;
    }

    public List<Integer> getHrZoneDurations() {
        return this.f;
    }

    public Integer getLapNumber() {
        return this.f6381a;
    }

    public Float getMaxCadence() {
        return this.k;
    }

    public Integer getMaxHr() {
        return this.m;
    }

    public Integer getMaxPower() {
        return this.maxPower;
    }

    public Float getMaxSpeed() {
        return this.h;
    }

    public Integer getSplitTime() {
        return this.c;
    }

    public Float getTotalCalories() {
        return this.e;
    }

    public Integer getTotalDistance() {
        return this.b;
    }

    public Integer getTotalSteps() {
        return this.n;
    }

    public void setActiveTime(Integer num) {
        this.d = num;
    }

    public void setAvgCadence(Float f) {
        this.j = f;
    }

    public void setAvgHr(Integer num) {
        this.l = num;
    }

    public void setAvgPower(Float f) {
        this.i = f;
    }

    public void setAvgSpeed(Float f) {
        this.g = f;
    }

    public void setHrZoneDurations(List<Integer> list) {
        this.f = list;
    }

    public void setLapNumber(Integer num) {
        this.f6381a = num;
    }

    public void setMaxCadence(Float f) {
        this.k = f;
    }

    public void setMaxHr(Integer num) {
        this.m = num;
    }

    public void setMaxPower(Integer num) {
        this.maxPower = num;
    }

    public void setMaxSpeed(Float f) {
        this.h = f;
    }

    public void setSplitTime(Integer num) {
        this.c = num;
    }

    public void setTotalCalories(Float f) {
        this.e = f;
    }

    public void setTotalDistance(Integer num) {
        this.b = num;
    }

    public void setTotalSteps(Integer num) {
        this.n = num;
    }
}
