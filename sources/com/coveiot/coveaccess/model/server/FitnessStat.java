package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.turf.TurfConstants;
/* loaded from: classes8.dex */
public class FitnessStat {
    @SerializedName("activityBaseUnit")
    @Expose
    private String activityBaseUnit;
    @SerializedName("activityType")
    @Expose
    private String activityType;
    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName(TurfConstants.UNIT_METERS)
    @Expose
    private String meters;
    @SerializedName("statsType")
    @Expose
    private String statsType;
    @SerializedName("value")
    @Expose
    private String value;

    public String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public String getCalories() {
        return this.calories;
    }

    public String getDate() {
        return this.date;
    }

    public String getMeters() {
        return this.meters;
    }

    public String getStatsType() {
        return this.statsType;
    }

    public String getValue() {
        return this.value;
    }

    public void setActivityBaseUnit(String str) {
        this.activityBaseUnit = str;
    }

    public void setActivityType(String str) {
        this.activityType = str;
    }

    public void setCalories(String str) {
        this.calories = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMeters(String str) {
        this.meters = str;
    }

    public void setStatsType(String str) {
        this.statsType = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
