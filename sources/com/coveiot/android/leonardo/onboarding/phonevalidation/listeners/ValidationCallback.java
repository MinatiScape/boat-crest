package com.coveiot.android.leonardo.onboarding.phonevalidation.listeners;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface ValidationCallback {

    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void onPhoneNumberEntered$default(ValidationCallback validationCallback, String str, String str2, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onPhoneNumberEntered");
            }
            if ((i & 4) != 0) {
                z = true;
            }
            validationCallback.onPhoneNumberEntered(str, str2, z);
        }
    }

    void onOtpReceived(@NotNull String str);

    void onPhoneNumberEntered(@NotNull String str, @NotNull String str2, boolean z);

    void onResendOtpRequested(@NotNull String str);
}
