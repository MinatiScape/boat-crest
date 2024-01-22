package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SSaveHeartBeatData {
    @SerializedName("app")
    private AppBean app;
    @SerializedName("coveDevices")
    private List<CoveDevicesBean> coveDevices;

    /* loaded from: classes8.dex */
    public static class AppBean {
        @SerializedName("bluetooth")
        private GpsBean bluetooth;
        @SerializedName("gps")
        private GpsBean gps;
        @SerializedName("powerOptimization")
        private PowerOptimizationBean powerOptimization;

        /* loaded from: classes8.dex */
        public static class GpsBean {
            @SerializedName("permissionLevel")
            private String permissionLevel;
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private boolean status;

            public String getPermissionLevel() {
                return this.permissionLevel;
            }

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public boolean isStatus() {
                return this.status;
            }

            public void setPermissionLevel(String str) {
                this.permissionLevel = str;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(boolean z) {
                this.status = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class PowerOptimizationBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private boolean status;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public boolean isStatus() {
                return this.status;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(boolean z) {
                this.status = z;
            }
        }

        public GpsBean getBluetooth() {
            return this.bluetooth;
        }

        public GpsBean getGps() {
            return this.gps;
        }

        public PowerOptimizationBean getPowerOptimization() {
            return this.powerOptimization;
        }

        public void setBluetooth(GpsBean gpsBean) {
            this.bluetooth = gpsBean;
        }

        public void setGps(GpsBean gpsBean) {
            this.gps = gpsBean;
        }

        public void setPowerOptimization(PowerOptimizationBean powerOptimizationBean) {
            this.powerOptimization = powerOptimizationBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class CoveDevicesBean {
        @SerializedName("battery")
        private BatteryBean battery;
        @SerializedName("connectivity")
        private ConnectivityBean connectivity;
        @SerializedName("sync")
        private ConnectivityBean sync;
        @SerializedName("userDeviceId")
        private int userDeviceId;

        /* loaded from: classes8.dex */
        public static class BatteryBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName("value")
            private int value;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public int getValue() {
                return this.value;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setValue(int i) {
                this.value = i;
            }
        }

        /* loaded from: classes8.dex */
        public static class ConnectivityBean {
            @SerializedName("code")
            private String code;
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private boolean status;

            public String getCode() {
                return this.code;
            }

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public boolean isStatus() {
                return this.status;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(boolean z) {
                this.status = z;
            }
        }

        public BatteryBean getBattery() {
            return this.battery;
        }

        public ConnectivityBean getConnectivity() {
            return this.connectivity;
        }

        public ConnectivityBean getSync() {
            return this.sync;
        }

        public int getUserDeviceId() {
            return this.userDeviceId;
        }

        public void setBattery(BatteryBean batteryBean) {
            this.battery = batteryBean;
        }

        public void setConnectivity(ConnectivityBean connectivityBean) {
            this.connectivity = connectivityBean;
        }

        public void setSync(ConnectivityBean connectivityBean) {
            this.sync = connectivityBean;
        }

        public void setUserDeviceId(int i) {
            this.userDeviceId = i;
        }
    }

    public AppBean getApp() {
        return this.app;
    }

    public List<CoveDevicesBean> getCoveDevices() {
        return this.coveDevices;
    }

    public void setApp(AppBean appBean) {
        this.app = appBean;
    }

    public void setCoveDevices(List<CoveDevicesBean> list) {
        this.coveDevices = list;
    }
}
