package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class OTPAuthReq implements Serializable {
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("otpLength")
    @Expose
    private Integer otpLength;
    @SerializedName("recaptchaAction")
    @Expose
    private String recaptchaAction;
    @SerializedName("recaptchaToken")
    @Expose
    private String recaptchaToken;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public Integer getOtpLength() {
        return this.otpLength;
    }

    public String getRecaptchaAction() {
        return this.recaptchaAction;
    }

    public String getRecaptchaToken() {
        return this.recaptchaToken;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    public void setMobileNumber(String str) {
        this.mobileNumber = str;
    }

    public void setOtpLength(Integer num) {
        this.otpLength = num;
    }

    public void setRecaptchaAction(String str) {
        this.recaptchaAction = str;
    }

    public void setRecaptchaToken(String str) {
        this.recaptchaToken = str;
    }

    public void setServiceType(String str) {
        this.serviceType = str;
    }
}
