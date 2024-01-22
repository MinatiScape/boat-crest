package com.coveiot.coveaccess.weeklyreport.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class GenerateOtpReq {
    @SerializedName("emailId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6913a;
    @SerializedName("otpLength")
    @Expose
    private Integer b;

    public String getEmailId() {
        return this.f6913a;
    }

    public Integer getOtpLength() {
        return this.b;
    }

    public void setEmailId(String str) {
        this.f6913a = str;
    }

    public void setOtpLength(Integer num) {
        this.b = num;
    }
}
