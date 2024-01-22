package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class PostDailyDataAggregate {
    @SerializedName("fitnessDataAggregates")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessDataAggregate> f6455a = null;

    public List<FitnessDataAggregate> getFitnessDataAggregates() {
        return this.f6455a;
    }

    public void setFitnessDataAggregates(List<FitnessDataAggregate> list) {
        this.f6455a = list;
    }
}
