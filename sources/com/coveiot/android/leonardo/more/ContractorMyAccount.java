package com.coveiot.android.leonardo.more;

import com.coveiot.utils.model.FailureType;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface ContractorMyAccount {
    void getDob();

    void getEmail();

    void getGender();

    void getHeight();

    void getName();

    void getPhoneNumber();

    void getRunStrideLength();

    void getWalkStrideLength();

    void getWeight();

    void onValidationFailed(@NotNull FailureType failureType, @NotNull String str);
}
