package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessBuddiesGoalsResponse extends CoveApiResponseBaseModel {
    @SerializedName("buddiesGoals")
    @Expose
    public List<BuddiesGoal> buddiesGoals;

    public GetFitnessBuddiesGoalsResponse(int i, List<BuddiesGoal> list) {
        super(i);
        this.buddiesGoals = null;
        this.buddiesGoals = list;
    }
}
