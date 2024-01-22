package com.google.android.recaptcha;

import androidx.annotation.NonNull;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class RecaptchaException extends Exception {
    @NotNull
    private final RecaptchaErrorCode errorCode;
    @NotNull
    private final String errorMessage;

    public RecaptchaException(@NonNull RecaptchaErrorCode recaptchaErrorCode, @NonNull String str) {
        super(str);
        this.errorCode = recaptchaErrorCode;
        this.errorMessage = str;
    }

    @NotNull
    public final RecaptchaErrorCode getErrorCode() {
        return this.errorCode;
    }

    @NotNull
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public /* synthetic */ RecaptchaException(@NonNull RecaptchaErrorCode recaptchaErrorCode, @NonNull String str, int i, @NonNull DefaultConstructorMarker defaultConstructorMarker) {
        this(recaptchaErrorCode, (i & 2) != 0 ? recaptchaErrorCode.getErrorMessage() : str);
    }
}
