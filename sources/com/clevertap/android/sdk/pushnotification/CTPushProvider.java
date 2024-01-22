package com.clevertap.android.sdk.pushnotification;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.pushnotification.PushConstants;
/* loaded from: classes2.dex */
public interface CTPushProvider {
    int getPlatform();

    @NonNull
    PushConstants.PushType getPushType();

    boolean isAvailable();

    boolean isSupported();

    int minSDKSupportVersionCode();

    void requestToken();
}
