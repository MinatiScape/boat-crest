package com.mappls.sdk.services.utils;

import androidx.annotation.Keep;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes8.dex */
class UserAgent {
    @SerializedName("androidVersion")
    @Expose
    private String androidVersion;
    @SerializedName("androidVersionAPI")
    @Expose
    private Integer androidVersionAPI;
    @SerializedName("appName")
    @Expose
    private String appName;
    @SerializedName("appPackageName")
    @Expose
    private String appPackageName;
    @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_VERSION)
    @Expose
    private String appVersion;
    @SerializedName("deviceBrand")
    @Expose
    private String deviceBrand;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("mapsSDKVersion")
    @Expose
    private String mapsSDKVersion;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("versionCode")
    @Expose
    private Integer versionCode;

    public String getAndroidVersion() {
        return this.androidVersion;
    }

    public Integer getAndroidVersionAPI() {
        return this.androidVersionAPI;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceBrand() {
        return this.deviceBrand;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getMapsSDKVersion() {
        return this.mapsSDKVersion;
    }

    public String getModel() {
        return this.model;
    }

    public Integer getVersionCode() {
        return this.versionCode;
    }

    public void setAndroidVersion(String str) {
        this.androidVersion = str;
    }

    public void setAndroidVersionAPI(Integer num) {
        this.androidVersionAPI = num;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setAppPackageName(String str) {
        this.appPackageName = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setDeviceBrand(String str) {
        this.deviceBrand = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setMapsSDKVersion(String str) {
        this.mapsSDKVersion = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setVersionCode(Integer num) {
        this.versionCode = num;
    }
}
