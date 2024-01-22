package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class MensturationBean {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private Boolean active;
    @SerializedName("cycleLength")
    private Integer cycleLength;
    @SerializedName("cycleStartDate")
    private String cycleStartDate;
    @SerializedName("periodLength")
    private Integer periodLength;
    @SerializedName("pmsLength")
    private Integer pmsLength;
    @SerializedName("reminders")
    private List<Reminder> reminders = null;

    /* loaded from: classes8.dex */
    public static class Reminder {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;
        @SerializedName("remindAt")
        private String remindAt;
        @SerializedName("remindBefore")
        private Integer remindBefore;
        @SerializedName("type")
        private String type;

        public Boolean getActive() {
            return this.active;
        }

        public String getRemindAt() {
            return this.remindAt;
        }

        public Integer getRemindBefore() {
            return this.remindBefore;
        }

        public String getType() {
            return this.type;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }

        public void setRemindAt(String str) {
            this.remindAt = str;
        }

        public void setRemindBefore(Integer num) {
            this.remindBefore = num;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public Boolean getActive() {
        return this.active;
    }

    public Integer getCycleLength() {
        return this.cycleLength;
    }

    public String getCycleStartDate() {
        return this.cycleStartDate;
    }

    public Integer getPeriodLength() {
        return this.periodLength;
    }

    public Integer getPmsLength() {
        return this.pmsLength;
    }

    public List<Reminder> getReminders() {
        return this.reminders;
    }

    public void setActive(Boolean bool) {
        this.active = bool;
    }

    public void setCycleLength(Integer num) {
        this.cycleLength = num;
    }

    public void setCycleStartDate(String str) {
        this.cycleStartDate = str;
    }

    public void setPeriodLength(Integer num) {
        this.periodLength = num;
    }

    public void setPmsLength(Integer num) {
        this.pmsLength = num;
    }

    public void setReminders(List<Reminder> list) {
        this.reminders = list;
    }
}
