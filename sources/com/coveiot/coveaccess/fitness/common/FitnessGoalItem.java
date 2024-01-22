package com.coveiot.coveaccess.fitness.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.android.tappy.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class FitnessGoalItem {
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
    public Integer targetAchieved;
    @SerializedName("targetedDays")
    @Expose
    public Integer targetedDays;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    public Integer userId;
}
