package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveTemperatureDataReq {
    @SerializedName("fitnessData")
    private List<TemperatureDataBean> fitnessData;

    public List<TemperatureDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<TemperatureDataBean> list) {
        this.fitnessData = list;
    }
}
