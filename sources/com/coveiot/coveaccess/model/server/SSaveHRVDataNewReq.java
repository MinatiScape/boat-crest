package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveHRVDataNewReq {
    @SerializedName("fitnessData")
    private List<StressFitnessData> stressFitnessData;

    public List<StressFitnessData> getFitnessData() {
        return this.stressFitnessData;
    }

    public void setFitnessData(List<StressFitnessData> list) {
        this.stressFitnessData = list;
    }
}
