package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Snooze {
    @SerializedName("interval")
    private String interval;
    @SerializedName("maxAllowed")
    private Integer maxAllowed;

    public String getInterval() {
        return this.interval;
    }

    public Integer getMaxAllowed() {
        return this.maxAllowed;
    }

    public void setInterval(String str) {
        this.interval = str;
    }

    public void setMaxAllowed(Integer num) {
        this.maxAllowed = num;
    }
}
