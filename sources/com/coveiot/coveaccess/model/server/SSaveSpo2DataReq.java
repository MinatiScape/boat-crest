package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveSpo2DataReq {
    @SerializedName("fitnessData")
    private List<Spo2DataBean> fitnessData;

    public List<Spo2DataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<Spo2DataBean> list) {
        this.fitnessData = list;
    }
}
