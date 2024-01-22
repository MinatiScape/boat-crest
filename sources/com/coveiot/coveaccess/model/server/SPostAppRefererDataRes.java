package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPostAppRefererDataRes {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("appName")
        private String appName;
        @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_VERSION)
        private String appVersion;
        @SerializedName("createdDate")
        private String createdDate;
        @SerializedName("deviceManufacturer")
        private String deviceManufacturer;
        @SerializedName("deviceModel")
        private String deviceModel;
        @SerializedName("logId")
        private String logId;
        @SerializedName("mobileNumber")
        private Object mobileNumber;
        @SerializedName("osName")
        private String osName;
        @SerializedName("osVersion")
        private String osVersion;
        @SerializedName("referrerData")
        private String referrerData;

        public String getAppName() {
            return this.appName;
        }

        public String getAppVersion() {
            return this.appVersion;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public String getDeviceManufacturer() {
            return this.deviceManufacturer;
        }

        public String getDeviceModel() {
            return this.deviceModel;
        }

        public String getLogId() {
            return this.logId;
        }

        public Object getMobileNumber() {
            return this.mobileNumber;
        }

        public String getOsName() {
            return this.osName;
        }

        public String getOsVersion() {
            return this.osVersion;
        }

        public String getReferrerData() {
            return this.referrerData;
        }

        public void setAppName(String str) {
            this.appName = str;
        }

        public void setAppVersion(String str) {
            this.appVersion = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setDeviceManufacturer(String str) {
            this.deviceManufacturer = str;
        }

        public void setDeviceModel(String str) {
            this.deviceModel = str;
        }

        public void setLogId(String str) {
            this.logId = str;
        }

        public void setMobileNumber(Object obj) {
            this.mobileNumber = obj;
        }

        public void setOsName(String str) {
            this.osName = str;
        }

        public void setOsVersion(String str) {
            this.osVersion = str;
        }

        public void setReferrerData(String str) {
            this.referrerData = str;
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
