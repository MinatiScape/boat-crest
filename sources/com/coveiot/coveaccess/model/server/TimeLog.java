package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class TimeLog {
    @SerializedName("logs")
    private List<Logs> logs;

    public List<Logs> getLogs() {
        return this.logs;
    }

    public void setLogs(List<Logs> list) {
        this.logs = list;
    }
}
