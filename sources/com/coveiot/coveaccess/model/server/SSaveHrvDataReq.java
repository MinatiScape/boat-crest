package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveHrvDataReq {
    @SerializedName("fitnessData")
    private List<HrvDataBean> fitnessData;

    public List<HrvDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<HrvDataBean> list) {
        this.fitnessData = list;
    }
}
