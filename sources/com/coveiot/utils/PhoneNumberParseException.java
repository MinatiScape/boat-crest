package com.coveiot.utils;

import com.coveiot.utils.utility.phonevaildation.model.PhoneNoValidationState;
/* loaded from: classes9.dex */
public class PhoneNumberParseException extends Exception {
    public PhoneNoValidationState validationError;

    public PhoneNumberParseException(PhoneNoValidationState phoneNoValidationState) {
        super(PhoneNoValidationState.getErrorReason(phoneNoValidationState));
        this.validationError = phoneNoValidationState;
    }

    public String getErroReason() {
        return PhoneNoValidationState.getErrorReason(this.validationError);
    }
}
