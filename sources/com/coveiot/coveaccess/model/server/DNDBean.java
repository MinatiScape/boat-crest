package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class DNDBean {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("schedules")
    private List<Schedule> schedules = null;

    /* loaded from: classes8.dex */
    public static class Schedule {
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("startTime")
        private String startTime;

        public String getEndTime() {
            return this.endTime;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }
    }

    public Boolean getActive() {
        return this.active;
    }

    public List<Schedule> getSchedules() {
        return this.schedules;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setSchedules(List<Schedule> list) {
        this.schedules = list;
    }
}
