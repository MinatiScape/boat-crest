package com.coveiot.coveaccess.dailyfitnessdata.aggregatedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLog {
    @SerializedName("logs")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<DailyLog> f6457a = null;

    public List<DailyLog> getLogs() {
        return this.f6457a;
    }

    public void setLogs(List<DailyLog> list) {
        this.f6457a = list;
    }
}
