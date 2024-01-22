package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SIOTUserDeviceResponseModel implements Serializable {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("btMacAddress")
        private String btMacAddress;
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("customerId")
        private String customerId;
        @SerializedName(DeviceKey.DeviceName)
        private String deviceName;
        @SerializedName("firmwareVersion")
        private String firmwareVersion;
        @SerializedName("hardwareVersion")
        private String hardwareVersion;
        @SerializedName("lastModifiedDate")
        private String lastModifiedDate;
        @SerializedName("manufactureDate")
        private String manufactureDate;
        @SerializedName("modelNumber")
        private String modelNumber;
        @SerializedName(com.coveiot.android.tappy.utils.Constants.SERIAL_NUMBER)
        private String serialNumber;
        @SerializedName("trackerId")
        private String trackerId;
        @SerializedName("userDeviceId")
        private String userDeviceId;

        public String getBtMacAddress() {
            return this.btMacAddress;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getCustomerId() {
            return this.customerId;
        }

        public String getDeviceName() {
            return this.deviceName;
        }

        public String getFirmwareVersion() {
            return this.firmwareVersion;
        }

        public String getHardwareVersion() {
            return this.hardwareVersion;
        }

        public String getLastModifiedDate() {
            return this.lastModifiedDate;
        }

        public String getManufactureDate() {
            return this.manufactureDate;
        }

        public String getModelNumber() {
            return this.modelNumber;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public String getTrackerId() {
            return this.trackerId;
        }

        public String getUserDeviceId() {
            return this.userDeviceId;
        }

        public void setBtMacAddress(String str) {
            this.btMacAddress = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setCustomerId(String str) {
            this.customerId = str;
        }

        public void setDeviceName(String str) {
            this.deviceName = str;
        }

        public void setFirmwareVersion(String str) {
            this.firmwareVersion = str;
        }

        public void setHardwareVersion(String str) {
            this.hardwareVersion = str;
        }

        public void setLastModifiedDate(String str) {
            this.lastModifiedDate = str;
        }

        public void setManufactureDate(String str) {
            this.manufactureDate = str;
        }

        public void setModelNumber(String str) {
            this.modelNumber = str;
        }

        public void setSerialNumber(String str) {
            this.serialNumber = str;
        }

        public void setTrackerId(String str) {
            this.trackerId = str;
        }

        public void setUserDeviceId(String str) {
            this.userDeviceId = str;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
