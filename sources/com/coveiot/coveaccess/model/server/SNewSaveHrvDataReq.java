package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.hrvdata.HRVDataNew;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SNewSaveHrvDataReq {
    @SerializedName("fitnessData")
    private List<HRVDataNew> fitnessData;

    public List<HRVDataNew> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<HRVDataNew> list) {
        this.fitnessData = list;
    }
}
