package com.coveiot.utils.utility.phonevaildation.model;
/* loaded from: classes9.dex */
public enum PhoneNoValidationState {
    VALID_PHONE_NO("Valid_Phone_No"),
    DIGITS_NOT_10("Please Enter only 10 digits Mobile Number"),
    NOT_DIGITS("Please Enter only numbers"),
    INVALID_ISD_CODE("Invalid Country Code"),
    INVALID_PHONE_NO("Please enter a valid phone number");
    
    public String errorReason;

    PhoneNoValidationState(String str) {
        this.errorReason = str;
    }

    public static String getErrorReason(PhoneNoValidationState phoneNoValidationState) {
        for (PhoneNoValidationState phoneNoValidationState2 : values()) {
            if (phoneNoValidationState == phoneNoValidationState2) {
                return phoneNoValidationState.errorReason;
            }
        }
        return INVALID_PHONE_NO.errorReason;
    }
}
