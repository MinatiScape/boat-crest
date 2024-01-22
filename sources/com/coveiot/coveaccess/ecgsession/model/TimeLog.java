package com.coveiot.coveaccess.ecgsession.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLog {
    @SerializedName("logs")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Log> f6486a = null;

    public List<Log> getLogs() {
        return this.f6486a;
    }

    public void setLogs(List<Log> list) {
        this.f6486a = list;
    }
}
