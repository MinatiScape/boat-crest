package com.coveiot.utils.utility.phonevaildation.model;
/* loaded from: classes9.dex */
public class PhoneValidationResponse {

    /* renamed from: a  reason: collision with root package name */
    public PhoneNoValidationState f7630a;
    public String b;
    public String c;

    public PhoneValidationResponse(PhoneNoValidationState phoneNoValidationState, String str) {
        this.f7630a = phoneNoValidationState;
        this.b = str;
    }

    public String getMessage() {
        return this.b;
    }

    public String getParsedMobileNumber() {
        return this.c;
    }

    public PhoneNoValidationState getPhoneNoValidationState() {
        return this.f7630a;
    }

    public void setParsedMobileNumber(String str) {
        this.c = str;
    }
}
