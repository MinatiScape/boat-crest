package com.coveiot.coveaccess.userdevicesetting.model;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SmartAlertSettings implements Serializable {
    @SerializedName("apps")
    @Expose
    private List<App> apps;

    /* loaded from: classes8.dex */
    public static class App implements Serializable {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        @Expose
        private Boolean active;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;
        @SerializedName("packageName")
        @Expose
        private String packageName;

        public Boolean getActive() {
            return this.active;
        }

        public String getName() {
            return this.name;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }
    }

    public List<App> getApps() {
        return this.apps;
    }

    public void setApps(List<App> list) {
        this.apps = list;
    }
}
