package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveAmbientSoundDataReq {
    @SerializedName("fitnessData")
    private List<AmbientSoundDataBean> fitnessData;

    public List<AmbientSoundDataBean> getFitnessData() {
        return this.fitnessData;
    }

    public void setFitnessData(List<AmbientSoundDataBean> list) {
        this.fitnessData = list;
    }
}
