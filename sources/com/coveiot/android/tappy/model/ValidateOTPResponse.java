package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ValidateOTPResponse implements Serializable {
    @Nullable
    private String errorMessage;
    @Nullable
    private List<ValidateOTPResult> validateOtpResults;

    @Nullable
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    @Nullable
    public final List<ValidateOTPResult> getValidateOtpResults() {
        return this.validateOtpResults;
    }

    public final void setErrorMessage(@Nullable String str) {
        this.errorMessage = str;
    }

    public final void setValidateOtpResults(@Nullable List<ValidateOTPResult> list) {
        this.validateOtpResults = list;
    }
}
