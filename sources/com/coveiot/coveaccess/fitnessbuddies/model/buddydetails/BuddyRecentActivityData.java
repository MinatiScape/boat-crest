package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class BuddyRecentActivityData {
    @SerializedName("maxHr")
    @Expose
    public Integer maxHr;
    @SerializedName("minHr")
    @Expose
    public Integer minHr;
    @SerializedName("totalCalories")
    @Expose
    public Double totalCalories;

    public Integer getMaxHr() {
        return this.maxHr;
    }

    public Integer getMinHr() {
        return this.minHr;
    }

    public Double getTotalCalories() {
        return this.totalCalories;
    }

    public void setMaxHr(Integer num) {
        this.maxHr = num;
    }

    public void setMinHr(Integer num) {
        this.minHr = num;
    }

    public void setTotalCalories(Double d) {
        this.totalCalories = d;
    }
}
