package com.coveiot.android.leonardo.onboarding.profile.listeners;

import com.coveiot.utils.model.FailureType;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface ContractorBasicProfileInfo {
    void getEmail();

    void getName();

    void loadNextScreen();

    void onValidationFailed(@NotNull FailureType failureType, @NotNull String str);
}
