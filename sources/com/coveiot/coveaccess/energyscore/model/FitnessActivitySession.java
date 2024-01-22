package com.coveiot.coveaccess.energyscore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FitnessActivitySession {
    @SerializedName("activityDuration")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6519a;
    @SerializedName("totalCalories")
    @Expose
    private Integer b;
    @SerializedName("sessionEndDate")
    public String sessionEndDate;
    @SerializedName("sessionStartDate")
    public String sessionStartDate;
    @SerializedName("sessionType")
    public String sessionType;

    public Integer getActivityDuration() {
        return this.f6519a;
    }

    public String getSessionEndDate() {
        return this.sessionEndDate;
    }

    public String getSessionStartDate() {
        return this.sessionStartDate;
    }

    public String getSessionType() {
        return this.sessionType;
    }

    public Integer getTotalCalories() {
        return this.b;
    }

    public void setActivityDuration(Integer num) {
        this.f6519a = num;
    }

    public void setSessionEndDate(String str) {
        this.sessionEndDate = str;
    }

    public void setSessionStartDate(String str) {
        this.sessionStartDate = str;
    }

    public void setSessionType(String str) {
        this.sessionType = str;
    }

    public void setTotalCalories(Integer num) {
        this.b = num;
    }
}
