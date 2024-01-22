package com.coveiot.coveaccess.fitness.model;

import com.coveiot.coveaccess.fitness.common.FitnessGoal;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class UpdateFitnessGoalResponse extends CoveApiResponseBaseModel {
    @SerializedName("fitnessGoal")
    @Expose
    public FitnessGoal data;

    public UpdateFitnessGoalResponse(int i, FitnessGoal fitnessGoal) {
        super(i);
        this.data = null;
        this.data = fitnessGoal;
    }
}
