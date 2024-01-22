package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
/* loaded from: classes8.dex */
public final class UserByPhoneNumberReq extends CoveApiRequestBaseModel {
    @SerializedName("otp")
    private String otp;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    public UserByPhoneNumberReq(String str, String str2) {
        this.phoneNumber = str;
        this.otp = str2;
    }

    public String getOtp() {
        return this.otp;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public UserByPhoneNumberReq(HashMap<String, String> hashMap, String str) {
        super(hashMap);
        this.phoneNumber = str;
        this.otp = this.otp;
    }
}
