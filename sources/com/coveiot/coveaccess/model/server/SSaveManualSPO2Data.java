package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveManualSPO2Data {
    @SerializedName("fitnessSessionData")
    private List<SPO2FitnessSessionDataBean> fitnessSessionData;

    public List<SPO2FitnessSessionDataBean> getFitnessSessionData() {
        return this.fitnessSessionData;
    }

    public void setFitnessSessionData(List<SPO2FitnessSessionDataBean> list) {
        this.fitnessSessionData = list;
    }
}
