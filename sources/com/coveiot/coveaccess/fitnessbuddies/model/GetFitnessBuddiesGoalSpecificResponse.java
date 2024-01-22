package com.coveiot.coveaccess.fitnessbuddies.model;

import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoalsBean;
import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessBuddiesGoalSpecificResponse extends CoveApiResponseBaseModel {
    @SerializedName("buddiesGoals")
    public List<BuddiesGoalsBean> buddiesGoals;

    public GetFitnessBuddiesGoalSpecificResponse(int i, List<BuddiesGoalsBean> list) {
        super(i);
        this.buddiesGoals = list;
    }

    public List<BuddiesGoalsBean> getBuddiesGoals() {
        return this.buddiesGoals;
    }

    public void setBuddiesGoals(List<BuddiesGoalsBean> list) {
        this.buddiesGoals = list;
    }
}
