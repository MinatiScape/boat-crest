package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.fitness.model.FitnessRecordData;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveFitnessRecordReq {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    private List<FitnessRecordData> items;

    public List<FitnessRecordData> getItems() {
        return this.items;
    }

    public void setItems(List<FitnessRecordData> list) {
        this.items = list;
    }
}
