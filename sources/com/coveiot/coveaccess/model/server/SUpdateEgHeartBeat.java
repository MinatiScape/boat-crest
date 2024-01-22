package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SUpdateEgHeartBeat {
    @SerializedName("app")
    private AppBean app;

    /* loaded from: classes8.dex */
    public static class AppBean {
        @SerializedName("battery")
        private BatteryBean battery;
        @SerializedName("bluetooth")
        private BluetoothBean bluetooth;
        @SerializedName("gps")
        private GpsBean gps;
        @SerializedName("powerOptimization")
        private PowerOptimizationBean powerOptimization;
        @SerializedName("process")
        private ProcessBean process;

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
        public static class BluetoothBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private Boolean status;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(Boolean bool) {
                this.status = bool;
            }
        }

        /* loaded from: classes8.dex */
        public static class GpsBean {
            @SerializedName("permissionLevel")
            private String permissionLevel;
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private Boolean status;

            public String getPermissionLevel() {
                return this.permissionLevel;
            }

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setPermissionLevel(String str) {
                this.permissionLevel = str;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(Boolean bool) {
                this.status = bool;
            }
        }

        /* loaded from: classes8.dex */
        public static class PowerOptimizationBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private Boolean status;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public Boolean getStatus() {
                return this.status;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(Boolean bool) {
                this.status = bool;
            }
        }

        /* loaded from: classes8.dex */
        public static class ProcessBean {
            @SerializedName("recordedTime")
            private String recordedTime;
            @SerializedName(NotificationCompat.CATEGORY_STATUS)
            private String status;

            public String getRecordedTime() {
                return this.recordedTime;
            }

            public String getStatus() {
                return this.status;
            }

            public void setRecordedTime(String str) {
                this.recordedTime = str;
            }

            public void setStatus(String str) {
                this.status = str;
            }
        }

        public BatteryBean getBattery() {
            return this.battery;
        }

        public BluetoothBean getBluetooth() {
            return this.bluetooth;
        }

        public GpsBean getGps() {
            return this.gps;
        }

        public PowerOptimizationBean getPowerOptimization() {
            return this.powerOptimization;
        }

        public ProcessBean getProcess() {
            return this.process;
        }

        public void setBattery(BatteryBean batteryBean) {
            this.battery = batteryBean;
        }

        public void setBluetooth(BluetoothBean bluetoothBean) {
            this.bluetooth = bluetoothBean;
        }

        public void setGps(GpsBean gpsBean) {
            this.gps = gpsBean;
        }

        public void setPowerOptimization(PowerOptimizationBean powerOptimizationBean) {
            this.powerOptimization = powerOptimizationBean;
        }

        public void setProcess(ProcessBean processBean) {
            this.process = processBean;
        }
    }

    public AppBean getApp() {
        return this.app;
    }

    public void setApp(AppBean appBean) {
        this.app = appBean;
    }
}
