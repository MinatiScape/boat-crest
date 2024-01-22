package com.coveiot.covepreferences.data;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class FitnessGoal implements Serializable {
    public String activityBaseUnit;
    public String activityType;
    public String createdDate;
    public Integer goalId;
    public String modifiedDate;
    public String startDate;
    public Integer target;
    public String targetAchieved;
    public Integer targetedDays;

    public String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public Integer getGoalId() {
        return this.goalId;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public Integer getTarget() {
        return this.target;
    }

    public String getTargetAchieved() {
        return this.targetAchieved;
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

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setGoalId(Integer num) {
        this.goalId = num;
    }

    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public void setTarget(Integer num) {
        this.target = num;
    }

    public void setTargetAchieved(String str) {
        this.targetAchieved = str;
    }

    public void setTargetedDays(Integer num) {
        this.targetedDays = num;
    }
}
