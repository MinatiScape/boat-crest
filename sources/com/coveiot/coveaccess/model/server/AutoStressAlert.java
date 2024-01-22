package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AutoStressAlert {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("interval")
    private String interval;
    @SerializedName("repeat")
    private Boolean repeat;
    @SerializedName("startTime")
    private String startTime;
    @SerializedName("weekDays")
    private String weekDays;

    public Boolean getActive() {
        return this.active;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getInterval() {
        return this.interval;
    }

    public Boolean getRepeat() {
        return this.repeat;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getWeekDays() {
        return this.weekDays;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setInterval(String str) {
        this.interval = str;
    }

    public void setRepeat(Boolean bool) {
        this.repeat = bool;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public void setWeekDays(String str) {
        this.weekDays = str;
    }
}
