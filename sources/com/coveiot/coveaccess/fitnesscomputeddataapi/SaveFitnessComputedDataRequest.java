package com.coveiot.coveaccess.fitnesscomputeddataapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveFitnessComputedDataRequest {
    @SerializedName("fitnessDataComputed")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessComputedData> f6587a = null;

    public List<FitnessComputedData> getFitnessDataComputed() {
        return this.f6587a;
    }

    public void setFitnessDataComputed(List<FitnessComputedData> list) {
        this.f6587a = list;
    }
}
