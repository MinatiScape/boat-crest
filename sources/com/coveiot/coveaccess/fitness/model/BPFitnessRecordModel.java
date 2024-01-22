package com.coveiot.coveaccess.fitness.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class BPFitnessRecordModel {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<BPFitnessRecordData> bpItems = null;

    public List<BPFitnessRecordData> getBpItems() {
        return this.bpItems;
    }

    public void setBpItems(List<BPFitnessRecordData> list) {
        this.bpItems = list;
    }
}
