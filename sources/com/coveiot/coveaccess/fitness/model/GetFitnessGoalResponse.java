package com.coveiot.coveaccess.fitness.model;

import com.coveiot.coveaccess.fitness.common.FitnessGoalItem;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessGoalResponse extends CoveApiResponseBaseModel {
    @SerializedName("fitnessGoal")
    @Expose
    public List<FitnessGoalItem> data;

    public GetFitnessGoalResponse(int i, List<FitnessGoalItem> list) {
        super(i);
        this.data = list;
    }
}
