package com.coveiot.coveaccess.model.server;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class GetOTPServicesRes implements Serializable {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)
    @Expose
    private List<ServiceType> items;

    /* loaded from: classes8.dex */
    public static class ServiceType implements Serializable {
        @SerializedName("attemptsAllowed")
        @Expose
        private Integer attemptsAllowed;
        @SerializedName("attemptsRemaining")
        @Expose
        private Integer attemptsRemaining;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;

        public Integer getAttemptsAllowed() {
            return this.attemptsAllowed;
        }

        public Integer getAttemptsRemaining() {
            return this.attemptsRemaining;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        public void setAttemptsAllowed(Integer num) {
            this.attemptsAllowed = num;
        }

        public void setAttemptsRemaining(Integer num) {
            this.attemptsRemaining = num;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public List<ServiceType> getItems() {
        return this.items;
    }

    public void setItems(List<ServiceType> list) {
        this.items = list;
    }
}
