package com.coveiot.coveaccess.fitnessbuddies.model.buddydetails;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class BuddyGoalsDetails implements Serializable {
    @SerializedName("activityBaseUnit")
    @Expose
    public String activityBaseUnit;
    @SerializedName("activityType")
    @Expose
    public String activityType;
    @SerializedName("goalId")
    @Expose
    public String goalId;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    public Integer target;
    @SerializedName("targetedDays")
    @Expose
    public Integer targetedDays;

    public String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public Integer getTarget() {
        return this.target;
    }

    public Integer getTargetedDays() {
        return this.targetedDays;
    }

    public void setActivityBaseUnit(String str) {
        this.activityBaseUnit = str;
    }

    public void setActivityType(String str) {
        this.activityType = str;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setTarget(Integer num) {
        this.target = num;
    }

    public void setTargetedDays(Integer num) {
        this.targetedDays = num;
    }
}
