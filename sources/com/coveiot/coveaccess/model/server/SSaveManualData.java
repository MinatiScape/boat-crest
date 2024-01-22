package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveManualData {
    @SerializedName("fitnessSessionData")
    private List<FitnessSessionDataBean> fitnessSessionData;

    public List<FitnessSessionDataBean> getFitnessSessionData() {
        return this.fitnessSessionData;
    }

    public void setFitnessSessionData(List<FitnessSessionDataBean> list) {
        this.fitnessSessionData = list;
    }
}
