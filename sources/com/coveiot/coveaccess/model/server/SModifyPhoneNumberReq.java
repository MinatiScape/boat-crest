package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public class SModifyPhoneNumberReq {
    private String mobileNumber;
    private int userId;
    private String verificationCode;

    public SModifyPhoneNumberReq(int i, String str, String str2) {
        this.userId = i;
        this.mobileNumber = str;
        this.verificationCode = str2;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }
}
