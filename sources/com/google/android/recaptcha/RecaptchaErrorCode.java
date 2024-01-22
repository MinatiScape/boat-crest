package com.google.android.recaptcha;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public enum RecaptchaErrorCode {
    UNKNOWN_ERROR(0, "Unknown Error"),
    NETWORK_ERROR(1, "Network Error"),
    INVALID_SITEKEY(2, "Site key invalid"),
    INVALID_KEYTYPE(3, "Key type invalid"),
    INVALID_PACKAGE_NAME(4, "Package name not allowed"),
    INVALID_ACTION(5, "Invalid action name, may only include alphanumeric characters like [A-Z], [a-z], [0-9], / and _. Do not include user-specific information"),
    INVALID_TIMEOUT(6, "Invalid timeout, minimum value is 5_000L milliseconds"),
    INTERNAL_ERROR(100, "Internal Error");
    
    private final int errorCode;
    @NotNull
    private final String errorMessage;

    RecaptchaErrorCode(int i, String str) {
        this.errorCode = i;
        this.errorMessage = str;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    @NotNull
    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
