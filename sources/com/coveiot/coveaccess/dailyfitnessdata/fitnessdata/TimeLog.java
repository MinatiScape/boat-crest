package com.coveiot.coveaccess.dailyfitnessdata.fitnessdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLog {
    @SerializedName("logs")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<FitnessLog> f6469a = null;

    public List<FitnessLog> getLogs() {
        return this.f6469a;
    }

    public void setLogs(List<FitnessLog> list) {
        this.f6469a = list;
    }
}
