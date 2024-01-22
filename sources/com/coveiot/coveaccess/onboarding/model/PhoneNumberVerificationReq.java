package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
/* loaded from: classes8.dex */
public final class PhoneNumberVerificationReq extends CoveApiRequestBaseModel {
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("recaptchaAction")
    private String recaptchaAction;
    @SerializedName("recaptchaToken")
    private String recaptchaToken;

    public PhoneNumberVerificationReq(String str) {
        this.phoneNumber = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRecaptchaAction() {
        return this.recaptchaAction;
    }

    public String getRecaptchaToken() {
        return this.recaptchaToken;
    }

    public void setRecaptchaAction(String str) {
        this.recaptchaAction = str;
    }

    public void setRecaptchaToken(String str) {
        this.recaptchaToken = str;
    }

    public PhoneNumberVerificationReq(HashMap<String, String> hashMap, String str) {
        super(hashMap);
        this.phoneNumber = str;
    }
}
