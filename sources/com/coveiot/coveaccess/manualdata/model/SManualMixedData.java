package com.coveiot.coveaccess.manualdata.model;

import com.coveiot.coveaccess.model.server.FitnessSessionDataBean;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SManualMixedData {
    @SerializedName("fitnessSessionData")

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessSessionDataBean> f6652a;

    public List<FitnessSessionDataBean> getFitnessSessionData() {
        return this.f6652a;
    }

    public void setFitnessSessionData(List<FitnessSessionDataBean> list) {
        this.f6652a = list;
    }
}
