package com.coveiot.coveaccess.tappy.model;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SDeviceInfo implements Serializable {
    @SerializedName("DeviceID")
    @Expose
    private long deviceId;
    @SerializedName("FormFactor")
    @Expose
    private String formFactor;
    @SerializedName("FriendlyName")
    @Expose
    private String friendlyName;
    @SerializedName(ExifInterface.TAG_MAKE)
    @Expose
    private String make;
    @SerializedName(ExifInterface.TAG_MODEL)
    @Expose
    private String model;
    @SerializedName("OS")
    @Expose
    private String os;
    @SerializedName("OSVersion")
    @Expose
    private String osVersion;
    @SerializedName("Status")
    @Expose
    private String status;

    public long getDeviceId() {
        return this.deviceId;
    }

    public String getFormFactor() {
        return this.formFactor;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOs() {
        return this.os;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDeviceId(long j) {
        this.deviceId = j;
    }

    public void setFormFactor(String str) {
        this.formFactor = str;
    }

    public void setFriendlyName(String str) {
        this.friendlyName = str;
    }

    public void setMake(String str) {
        this.make = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
