package com.coveiot.coveaccess.fitness.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AllFitnessRecordData {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<AllFitnessRecordBean> fitnessRecords = null;

    public List<AllFitnessRecordBean> getFitnessRecords() {
        return this.fitnessRecords;
    }

    public void setFitnessRecords(List<AllFitnessRecordBean> list) {
        this.fitnessRecords = list;
    }
}
