package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class PostDailyFitnessDataRequest {
    @SerializedName("fitnessData")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessDatum> f6465a = null;

    public List<FitnessDatum> getFitnessData() {
        return this.f6465a;
    }

    public void setFitnessData(List<FitnessDatum> list) {
        this.f6465a = list;
    }
}
