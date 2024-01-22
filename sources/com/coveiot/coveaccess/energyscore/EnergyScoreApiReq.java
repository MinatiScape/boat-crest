package com.coveiot.coveaccess.energyscore;

import com.coveiot.coveaccess.energyscore.model.EnergyScoreData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EnergyScoreApiReq {
    @SerializedName("data")
    @Expose
    public EnergyScoreData data;

    public EnergyScoreData getData() {
        return this.data;
    }

    public void setData(EnergyScoreData energyScoreData) {
        this.data = energyScoreData;
    }
}
