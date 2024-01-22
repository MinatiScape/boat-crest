package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveHeartRateDataReq {
    @SerializedName("fitnessData")
    private List<HeartRateDataBean> fitnessData;

    public List<HeartRateDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<HeartRateDataBean> list) {
        this.fitnessData = list;
    }
}
