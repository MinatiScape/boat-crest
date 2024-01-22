package com.coveiot.coveaccess.sedentaryalerts.model;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SedentaryAlertsDataBean {
    @SerializedName("userDeviceId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6703a;
    @SerializedName("type")
    @Expose
    private String b;
    @SerializedName("date")
    @Expose
    private String c;
    @SerializedName("tzOffset")
    @Expose
    private String d;
    @SerializedName("value")
    @Expose
    private int e;
    @SerializedName("baseUnit")
    @Expose
    private String f;
    @SerializedName("timeLog")
    @Expose
    private TimeLog g;
    @SerializedName("settings")
    @Expose
    private SedentaryAlertSettings h;

    /* loaded from: classes8.dex */
    public static class SedentaryAlertSettings {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private boolean f6704a;
        @SerializedName("startTime")
        @Expose
        private String b;
        @SerializedName("endTime")
        @Expose
        private String c;
        @SerializedName("interval")
        @Expose
        private String d;
        @SerializedName("siesta")
        @Expose
        private SedentaryAlertSettingsSiesta e;

        public String getEndTime() {
            return this.c;
        }

        public String getInterval() {
            return this.d;
        }

        public SedentaryAlertSettingsSiesta getSiesta() {
            return this.e;
        }

        public String getStartTime() {
            return this.b;
        }

        public boolean isActive() {
            return this.f6704a;
        }

        public void setActive(boolean z) {
            this.f6704a = z;
        }

        public void setEndTime(String str) {
            this.c = str;
        }

        public void setInterval(String str) {
            this.d = str;
        }

        public void setSiesta(SedentaryAlertSettingsSiesta sedentaryAlertSettingsSiesta) {
            this.e = sedentaryAlertSettingsSiesta;
        }

        public void setStartTime(String str) {
            this.b = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SedentaryAlertSettingsSiesta {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6705a;

        public Boolean getActive() {
            return this.f6705a;
        }

        public void setActive(Boolean bool) {
            this.f6705a = bool;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLog {
        @SerializedName("logs")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<Log> f6706a = null;

        /* loaded from: classes8.dex */
        public static class Log {
            @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6707a;

            public String getTime() {
                return this.f6707a;
            }

            public void setTime(String str) {
                this.f6707a = str;
            }
        }

        public List<Log> getLogs() {
            return this.f6706a;
        }

        public void setLogs(List<Log> list) {
            this.f6706a = list;
        }
    }

    public String getBaseUnit() {
        return this.f;
    }

    public String getDate() {
        return this.c;
    }

    public SedentaryAlertSettings getSedentaryAlertSettings() {
        return this.h;
    }

    public TimeLog getTimeLog() {
        return this.g;
    }

    public String getType() {
        return this.b;
    }

    public String getTzOffset() {
        return this.d;
    }

    public String getUser() {
        return this.f6703a;
    }

    public int getValue() {
        return this.e;
    }

    public void setBaseUnit(String str) {
        this.f = str;
    }

    public void setDate(String str) {
        this.c = str;
    }

    public void setSedentaryAlertSettings(SedentaryAlertSettings sedentaryAlertSettings) {
        this.h = sedentaryAlertSettings;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.g = timeLog;
    }

    public void setType(String str) {
        this.b = str;
    }

    public void setTzOffset(String str) {
        this.d = str;
    }

    public void setUser(String str) {
        this.f6703a = str;
    }

    public void setValue(int i) {
        this.e = i;
    }

    public String toString() {
        return "SedentaryAlertsDataBean{user='" + this.f6703a + "', type='" + this.b + "', date='" + this.c + "', tzOffset='" + this.d + "', value=" + this.e + ", baseUnit='" + this.f + "', timeLog=" + this.g + ", sedentaryAlertSettings=" + this.h + '}';
    }
}
