package com.coveiot.coveaccess.sedentaryalerts.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveSedentaryAlertsDataReq {
    @SerializedName("fitnessData")

    /* renamed from: a  reason: collision with root package name */
    private List<SedentaryAlertsDataBean> f6702a;

    public List<SedentaryAlertsDataBean> getFitnessData() {
        return this.f6702a;
    }

    public void setFitnessData(List<SedentaryAlertsDataBean> list) {
        this.f6702a = list;
    }
}
