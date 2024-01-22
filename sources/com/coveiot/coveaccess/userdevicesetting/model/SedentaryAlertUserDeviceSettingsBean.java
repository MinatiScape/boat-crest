package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SedentaryAlertUserDeviceSettingsBean {
    @SerializedName("weekDays")

    /* renamed from: a  reason: collision with root package name */
    private String f6898a;
    @SerializedName("siesta")
    private Siesta b;
    @SerializedName("repeat")
    private boolean c;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean d;
    @SerializedName("startTime")
    private String e;
    @SerializedName("interval")
    private String f;
    @SerializedName("endTime")
    private String g;

    /* loaded from: classes8.dex */
    public static class Siesta {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private boolean f6899a;

        public boolean isActive() {
            return this.f6899a;
        }

        public void setActive(boolean z) {
            this.f6899a = z;
        }
    }

    public String getEndTime() {
        return this.g;
    }

    public String getInterval() {
        return this.f;
    }

    public Siesta getSiesta() {
        return this.b;
    }

    public String getStartTime() {
        return this.e;
    }

    public String getWeekDays() {
        return this.f6898a;
    }

    public boolean isActive() {
        return this.d;
    }

    public boolean isRepeat() {
        return this.c;
    }

    public void setActive(boolean z) {
        this.d = z;
    }

    public void setEndTime(String str) {
        this.g = str;
    }

    public void setInterval(String str) {
        this.f = str;
    }

    public void setRepeat(boolean z) {
        this.c = z;
    }

    public void setSiesta(Siesta siesta) {
        this.b = siesta;
    }

    public void setStartTime(String str) {
        this.e = str;
    }

    public void setWeekDays(String str) {
        this.f6898a = str;
    }

    public String toString() {
        return "SedentaryAlertUserDeviceSettingsBean{weekDays='" + this.f6898a + "', siesta=" + this.b + ", repeat=" + this.c + ", active=" + this.d + ", startTime='" + this.e + "', interval='" + this.f + "', endTime='" + this.g + "'}";
    }
}
