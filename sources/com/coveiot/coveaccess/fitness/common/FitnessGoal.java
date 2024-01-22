package com.coveiot.coveaccess.fitness.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FitnessGoal {
    @SerializedName("activityBaseUnit")
    @Expose
    public String activityBaseUnit;
    @SerializedName("activityType")
    @Expose
    public String activityType;
    @SerializedName("createdDate")
    @Expose
    public String createdDate;
    @SerializedName("goalId")
    @Expose
    public Integer goalId;
    @SerializedName("modifiedDate")
    @Expose
    public String modifiedDate;
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    public Integer target;
    @SerializedName("targetAchieved")
    @Expose
    public String targetAchieved;
    @SerializedName("targetedDays")
    @Expose
    public Integer targetedDays;
}
