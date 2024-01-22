package com.coveiot.coveaccess.ecgsession.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class PostECGSessionData {
    @SerializedName("fitnessSessionData")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessSessionDatum> f6485a = null;

    public List<FitnessSessionDatum> getFitnessSessionData() {
        return this.f6485a;
    }

    public void setFitnessSessionData(List<FitnessSessionDatum> list) {
        this.f6485a = list;
    }
}
