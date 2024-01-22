package com.coveiot.coveaccess.userappsetting;

import com.coveiot.android.tappy.utils.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class SoftwarUpdateReq implements Serializable {
    @SerializedName("appFullName")
    private String appId;
    @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_VERSION)
    private String appVersion;
    @SerializedName("cloveDevice")
    @Deprecated
    private List<CloveDeviceBean> cloveDeviceBeans;
    @SerializedName("coveDevices")
    private List<CloveDeviceBean> coveDevices;
    @SerializedName("device")
    private DeviceBean device;

    /* loaded from: classes8.dex */
    public static class CloveDeviceBean implements Serializable {
        @SerializedName("btMacAddress")
        private String btMacAddress;
        @SerializedName("customerId")
        private String customerId;
        @SerializedName("firmwareVersion")
        private String firmwareVersion;
        @SerializedName("hardwareVersion")
        private String hardwareVersion;
        @SerializedName("modelNumber")
        private String modelNumber;
        @SerializedName(Constants.SERIAL_NUMBER)
        private String serialNumber;

        public String getBtMacAddress() {
            return this.btMacAddress;
        }

        public String getCustomerId() {
            return this.customerId;
        }

        public String getFirmwareVersion() {
            return this.firmwareVersion;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
        }

        public String getModelNumber() {
            return this.modelNumber;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public void setBtMacAddress(String str) {
            this.btMacAddress = str;
        }

        public void setCustomerId(String str) {
            this.customerId = str;
        }

        public void setFirmwareVersion(String str) {
            this.firmwareVersion = str;
        }

        public void setHardwareVersion(String str) {
            this.hardwareVersion = str;
        }

        public void setModelNumber(String str) {
            this.modelNumber = str;
        }

        public void setSerialNumber(String str) {
            this.serialNumber = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class DeviceBean implements Serializable {
        @SerializedName(FirebaseAnalytics.Param.LOCATION)
        private LocationBean location;

        /* loaded from: classes8.dex */
        public static class LocationBean implements Serializable {
            @SerializedName("latitude")
            private double latitude;
            @SerializedName("longitude")
            private double longitude;

            public Double getLatitude() {
                return Double.valueOf(this.latitude);
            }

            public Double getLongitude() {
                return Double.valueOf(this.longitude);
            }

            public void setLatitude(Double d) {
                this.latitude = d.doubleValue();
            }

            public void setLongitude(Double d) {
                this.longitude = d.doubleValue();
            }
        }

        public LocationBean getLocation() {
            return this.location;
        }

        public void setLocation(LocationBean locationBean) {
            this.location = locationBean;
        }
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public List<CloveDeviceBean> getCloveDeviceBeans() {
        return this.cloveDeviceBeans;
    }

    public List<CloveDeviceBean> getCoveDevices() {
        return this.coveDevices;
    }

    public DeviceBean getDevice() {
        return this.device;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setCloveDeviceBeans(List<CloveDeviceBean> list) {
        this.cloveDeviceBeans = list;
    }

    public void setCoveDevices(List<CloveDeviceBean> list) {
        this.coveDevices = list;
    }

    public void setDevice(DeviceBean deviceBean) {
        this.device = deviceBean;
    }
}
