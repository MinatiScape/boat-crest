package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveSleepData {
    @SerializedName("fitnessData")
    private List<SleepDataBean> fitnessData;

    public List<SleepDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<SleepDataBean> list) {
        this.fitnessData = list;
    }
}
