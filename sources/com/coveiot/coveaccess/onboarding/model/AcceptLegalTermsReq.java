package com.coveiot.coveaccess.onboarding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class AcceptLegalTermsReq {
    @SerializedName("acceptedDate")
    @Expose
    public String acceptedDate;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("userDeviceId")
    @Expose
    public Integer userDeviceId;
    @SerializedName("version")
    @Expose
    public String version;

    public AcceptLegalTermsReq() {
    }

    public String getAcceptedDate() {
        return this.acceptedDate;
    }

    public String getMedium() {
        return this.medium;
    }

    public String getType() {
        return this.type;
    }

    public Integer getUserDeviceId() {
        return this.userDeviceId;
    }

    public String getVersion() {
        return this.version;
    }

    public void setAcceptedDate(String str) {
        this.acceptedDate = str;
    }

    public void setMedium(String str) {
        this.medium = str;
    }

    public void setUserDeviceId(Integer num) {
        this.userDeviceId = num;
    }

    public AcceptLegalTermsReq(String str, String str2) {
        this.type = str;
        this.version = str2;
    }

    public AcceptLegalTermsReq(String str, String str2, String str3, Integer num, String str4) {
        this.type = str;
        this.version = str2;
        this.medium = str3;
        this.userDeviceId = num;
        this.acceptedDate = str4;
    }
}
