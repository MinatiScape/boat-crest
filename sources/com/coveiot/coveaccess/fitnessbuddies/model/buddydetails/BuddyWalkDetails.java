package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.turf.TurfConstants;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BuddyWalkDetails implements Serializable {
    @SerializedName("calories")
    @Expose
    public Integer calories;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName(TurfConstants.UNIT_METERS)
    @Expose
    public Integer meters;
    @SerializedName("steps")
    @Expose
    public Integer steps;

    public Integer getCalories() {
        return this.calories;
    }

    public String getDate() {
        return this.date;
    }

    public Integer getMeters() {
        return this.meters;
    }

    public Integer getSteps() {
        return this.steps;
    }

    public void setCalories(Integer num) {
        this.calories = num;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMeters(Integer num) {
        this.meters = num;
    }

    public void setSteps(Integer num) {
        this.steps = num;
    }
}
