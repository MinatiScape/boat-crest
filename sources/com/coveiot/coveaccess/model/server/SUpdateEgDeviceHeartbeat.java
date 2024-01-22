package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SUpdateEgDeviceHeartbeat {
    @SerializedName("coveDevices")
    private List<CoveDevicesBean> coveDevices;

    /* loaded from: classes8.dex */
    public static class CoveDevicesBean {
        @SerializedName("battery")
        private BatteryBean battery;
        @SerializedName("connectivity")
        private ConnectivityBean connectivity;
        @SerializedName("sync")
        private SyncBean sync;

        /* loaded from: classes8.dex */
        public static class BatteryBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName("value")
            private Integer value;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Integer getValue() {
                return this.value;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setValue(Integer num) {
                this.value = num;
            }
        }

        /* loaded from: classes8.dex */
        public static class ConnectivityBean {
            @SerializedName("code")
            private String code;
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private Boolean status;

            public String getCode() {
                return this.code;
            }

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(Boolean bool) {
                this.status = bool;
            }
        }

        /* loaded from: classes8.dex */
        public static class SyncBean {
            @SerializedName("code")
            private String code;
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private Boolean status;

            public String getCode() {
                return this.code;
            }

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(Boolean bool) {
                this.status = bool;
            }
        }

        public BatteryBean getBattery() {
            return this.battery;
        }

        public ConnectivityBean getConnectivity() {
            return this.connectivity;
        }

        public SyncBean getSync() {
            return this.sync;
        }

        public void setBattery(BatteryBean batteryBean) {
            this.battery = batteryBean;
        }

        public void setConnectivity(ConnectivityBean connectivityBean) {
            this.connectivity = connectivityBean;
        }

        public void setSync(SyncBean syncBean) {
            this.sync = syncBean;
        }
    }

    public List<CoveDevicesBean> getCoveDevices() {
        return this.coveDevices;
    }

    public void setCoveDevices(List<CoveDevicesBean> list) {
        this.coveDevices = list;
    }
}
