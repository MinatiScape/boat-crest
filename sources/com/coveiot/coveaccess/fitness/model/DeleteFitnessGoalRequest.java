package com.coveiot.coveaccess.fitness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class DeleteFitnessGoalRequest {
    @SerializedName("goalId")
    @Expose
    public int goalId;

    public DeleteFitnessGoalRequest(int i) {
        this.goalId = i;
    }

    public int getGoalId() {
        return this.goalId;
    }
}
