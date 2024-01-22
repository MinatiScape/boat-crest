package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSavePeriodicStressDataReq {
    @SerializedName("fitnessData")
    private List<PeriodicStressData> fitnessData;

    public List<PeriodicStressData> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<PeriodicStressData> list) {
        this.fitnessData = list;
    }
}
