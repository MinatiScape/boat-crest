package com.coveiot.coveaccess.weeklyreport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class VerifyOtpReq {
    @SerializedName("verificationId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6914a;
    @SerializedName("otp")
    @Expose
    private Integer b;

    public Integer getOtp() {
        return this.b;
    }

    public String getVerificationId() {
        return this.f6914a;
    }

    public void setOtp(Integer num) {
        this.b = num;
    }

    public void setVerificationId(String str) {
        this.f6914a = str;
    }
}
