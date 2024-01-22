package com.coveiot.coveaccess.userappsetting;

import com.coveiot.coveaccess.model.server.Snooze;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveAlarmSettingReq {
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

    /* renamed from: a  reason: collision with root package name */
    private Boolean f6833a;
    @SerializedName("snooze")
    private Snooze b;
    public List<AlarmListBean> c;

    /* loaded from: classes8.dex */
    public static class AlarmListBean {

        /* renamed from: a  reason: collision with root package name */
        public String f6834a;
        public String b;
        public boolean c;
        public String d;
        public boolean e;
        public String f;
        public String g;

        public String getAlarmId() {
            return this.f6834a;
        }

        public String getLabel() {
            return this.b;
        }

        public String getTime() {
            return this.d;
        }

        public String getType() {
            return this.g;
        }

        public String getWeek() {
            return this.f;
        }

        public boolean isActive() {
            return this.c;
        }

        public boolean isRepeat() {
            return this.e;
        }

        public void setActive(boolean z) {
            this.c = z;
        }

        public void setAlarmId(String str) {
            this.f6834a = str;
        }

        public void setLabel(String str) {
            this.b = str;
        }

        public void setRepeat(boolean z) {
            this.e = z;
        }

        public void setTime(String str) {
            this.d = str;
        }

        public void setType(String str) {
            this.g = str;
        }

        public void setWeek(String str) {
            this.f = str;
        }
    }

    public Boolean getActive() {
        return this.f6833a;
    }

    public List<AlarmListBean> getAlarmListBeans() {
        return this.c;
    }

    public Snooze getSnooze() {
        return this.b;
    }

    public void setActive(Boolean bool) {
        this.f6833a = bool;
    }

    public void setAlarmListBeans(List<AlarmListBean> list) {
        this.c = list;
    }

    public void setSnooze(Snooze snooze) {
        this.b = snooze;
    }
}
