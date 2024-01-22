package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivitiesSampleDetails {
    @Expose
    private Integer activeTime;
    @SerializedName("activityDuration")
    @Expose
    private Integer activityDuration;
    @SerializedName("activitySite")
    @Expose
    private String activitySite;
    @SerializedName("avgHr")
    @Expose
    private Integer avgHr;
    @SerializedName("avgPace")
    @Expose
    private Integer avgPace;
    @SerializedName("avgSpeed")
    @Expose
    private Float avgSpeed;
    @SerializedName("maxHr")
    @Expose
    private Integer maxHr;
    @SerializedName("maxSpeed")
    @Expose
    private Float maxSpeed;
    @SerializedName("minHr")
    @Expose
    private Integer minHr;
    @SerializedName("sampleCount")
    @Expose
    private Integer sampleCount;
    @SerializedName("sampleRate")
    @Expose
    private Integer sampleRate;
    @SerializedName("totalCalories")
    @Expose
    private Float totalCalories;
    @SerializedName("totalDistance")
    @Expose
    private Integer totalDistance;
    @SerializedName("totalSteps")
    @Expose
    private Integer totalSteps;
    @SerializedName("hrZoneDurations")
    @Expose
    private List<Integer> hrZoneDurations = null;
    @SerializedName("targets")
    @Expose
    private List<Target> targets = null;
    @SerializedName("logs")
    @Expose
    private List<TraqActivityLogs> traqActivityLogs = null;
    @SerializedName("laps")
    @Expose
    private List<ActivityLapsData> activityLapsData = null;

    public Integer getActiveTime() {
        return this.activeTime;
    }

    public Integer getActivityDuration() {
        return this.activityDuration;
    }

    public List<ActivityLapsData> getActivityLapsData() {
        return this.activityLapsData;
    }

    @SerializedName("activeTime")
    public String getActivitySite() {
        return this.activitySite;
    }

    public Integer getAvgHr() {
        return this.avgHr;
    }

    public Integer getAvgPace() {
        return this.avgPace;
    }

    public Float getAvgSpeed() {
        return this.avgSpeed;
    }

    public List<Integer> getHrZoneDurations() {
        return this.hrZoneDurations;
    }

    public Integer getMaxHr() {
        return this.maxHr;
    }

    public Float getMaxSpeed() {
        return this.maxSpeed;
    }

    public Integer getMinHr() {
        return this.minHr;
    }

    public Integer getSampleCount() {
        return this.sampleCount;
    }

    public Integer getSampleRate() {
        return this.sampleRate;
    }

    public List<Target> getTargets() {
        return this.targets;
    }

    public Float getTotalCalories() {
        return this.totalCalories;
    }

    public Integer getTotalDistance() {
        return this.totalDistance;
    }

    public Integer getTotalSteps() {
        return this.totalSteps;
    }

    public List<TraqActivityLogs> getTraqActivityLogs() {
        return this.traqActivityLogs;
    }

    public void setActiveTime(Integer num) {
        this.activeTime = num;
    }

    public void setActivityDuration(Integer num) {
        this.activityDuration = num;
    }

    public void setActivityLapsData(List<ActivityLapsData> list) {
        this.activityLapsData = list;
    }

    public void setActivitySite(String str) {
        this.activitySite = str;
    }

    public void setAvgHr(Integer num) {
        this.avgHr = num;
    }

    public void setAvgPace(Integer num) {
        this.avgPace = num;
    }

    public void setAvgSpeed(Float f) {
        this.avgSpeed = f;
    }

    public void setHrZoneDurations(List<Integer> list) {
        this.hrZoneDurations = list;
    }

    public void setMaxHr(Integer num) {
        this.maxHr = num;
    }

    public void setMaxSpeed(Float f) {
        this.maxSpeed = f;
    }

    public void setMinHr(Integer num) {
        this.minHr = num;
    }

    public void setSampleCount(Integer num) {
        this.sampleCount = num;
    }

    public void setSampleRate(Integer num) {
        this.sampleRate = num;
    }

    public void setTargets(List<Target> list) {
        this.targets = list;
    }

    public void setTotalCalories(Float f) {
        this.totalCalories = f;
    }

    public void setTotalDistance(Integer num) {
        this.totalDistance = num;
    }

    public void setTotalSteps(Integer num) {
        this.totalSteps = num;
    }

    public void setTraqActivityLogs(List<TraqActivityLogs> list) {
        this.traqActivityLogs = list;
    }
}
