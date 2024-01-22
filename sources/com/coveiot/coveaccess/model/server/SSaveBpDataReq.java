package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveBpDataReq {
    @SerializedName("fitnessData")
    private List<BpDataBean> fitnessData;

    public List<BpDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<BpDataBean> list) {
        this.fitnessData = list;
    }
}
