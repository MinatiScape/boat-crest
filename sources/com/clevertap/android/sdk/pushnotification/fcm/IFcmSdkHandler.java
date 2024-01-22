package com.clevertap.android.sdk.pushnotification.fcm;

import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.pushnotification.PushConstants;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public interface IFcmSdkHandler {
    PushConstants.PushType getPushType();

    boolean isAvailable();

    boolean isSupported();

    void requestToken();
}
