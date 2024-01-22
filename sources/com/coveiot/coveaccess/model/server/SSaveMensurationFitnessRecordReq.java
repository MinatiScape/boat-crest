package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.fitness.model.MensurationSymptomsRecordBeans;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveMensurationFitnessRecordReq {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    private List<MensurationSymptomsRecordBeans> items;

    public List<MensurationSymptomsRecordBeans> getItems() {
        return this.items;
    }

    public void setItems(List<MensurationSymptomsRecordBeans> list) {
        this.items = list;
    }
}
