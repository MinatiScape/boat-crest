package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLogBean {
    @SerializedName("logs")
    private List<LogsBean> logs;

    public List<LogsBean> getLogs() {
        return this.logs;
    }

    public void setLogs(List<LogsBean> list) {
        this.logs = list;
    }

    public String toString() {
        return "TimeLogBean{logs=" + this.logs + '}';
    }
}
