package com.coveiot.coveaccess.fitness.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class MensurationSymptomRecordData {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<MensurationSymptomsRecordBeans> mensesData = null;

    public List<MensurationSymptomsRecordBeans> getMensesData() {
        return this.mensesData;
    }

    public void setMensesData(List<MensurationSymptomsRecordBeans> list) {
        this.mensesData = list;
    }
}
