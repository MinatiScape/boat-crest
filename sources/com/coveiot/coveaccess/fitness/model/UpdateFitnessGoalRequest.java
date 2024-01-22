package com.coveiot.coveaccess.fitness.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class UpdateFitnessGoalRequest implements Serializable {
    @SerializedName("startDate")
    @Expose
    public String startDate;
    @SerializedName(TypedValues.AttributesType.S_TARGET)
    @Expose
    public Integer target;
    @SerializedName("targetedDays")
    @Expose
    public Integer targetedDays;

    public UpdateFitnessGoalRequest(Integer num, Integer num2) {
        this.target = null;
        this.startDate = null;
        this.targetedDays = num;
        this.target = num2;
    }

    public UpdateFitnessGoalRequest(Integer num, Integer num2, String str) {
        this.target = null;
        this.startDate = null;
        this.targetedDays = num;
        this.target = num2;
        this.startDate = str;
    }
}
