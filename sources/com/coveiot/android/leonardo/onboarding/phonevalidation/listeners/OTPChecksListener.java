package com.coveiot.android.leonardo.onboarding.phonevalidation.listeners;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface OTPChecksListener {
    void onFailure(@NotNull String str);

    void onSuccess();
}
