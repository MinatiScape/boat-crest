package com.coveiot.coveaccess.fitness.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.coveaccess.fitness.ActivityBaseUnit;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class CreateFitnessGoalRequest {
    @SerializedName("activityBaseUnit")
    @Expose
    public String activityBaseUnit;
    @SerializedName("activityType")
    @Expose
    public String activityType;
    @SerializedName("priorityOrder")
    @Expose
    public Integer priorityOrder;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName("tagId")
    @Expose
    public String tagId;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    public Integer target;
    @SerializedName("targetedDays")
    @Expose
    public Integer targetedDays;

    public CreateFitnessGoalRequest(ActivityType activityType, ActivityBaseUnit activityBaseUnit, Integer num, Integer num2, String str, String str2, Integer num3) {
        this.activityType = activityType.getActivityType();
        this.activityBaseUnit = activityBaseUnit.getActivityBaseUnit();
        this.targetedDays = num;
        this.target = num2;
        this.startDate = str;
        this.tagId = str2;
        this.priorityOrder = num3;
    }

    public CreateFitnessGoalRequest(ActivityType activityType, ActivityBaseUnit activityBaseUnit, Integer num, Integer num2, String str) {
        this.activityType = activityType.getActivityType();
        this.activityBaseUnit = activityBaseUnit.getActivityBaseUnit();
        this.targetedDays = num;
        this.target = num2;
        this.startDate = str;
    }
}
