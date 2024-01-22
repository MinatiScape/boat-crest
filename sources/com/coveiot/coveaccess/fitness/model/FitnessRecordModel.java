package com.coveiot.coveaccess.fitness.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessRecordModel {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<SPO2FitnessRecordData> items = null;

    public List<SPO2FitnessRecordData> getItems() {
        return this.items;
    }

    public void setItems(List<SPO2FitnessRecordData> list) {
        this.items = list;
    }
}
