package com.clevertap.android.sdk.pushnotification.fcm;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.pushnotification.CTPushProvider;
import com.clevertap.android.sdk.pushnotification.CTPushProviderListener;
import com.clevertap.android.sdk.pushnotification.PushConstants;
@SuppressLint({"unused"})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class FcmPushProvider implements CTPushProvider {
    private IFcmSdkHandler handler;

    @SuppressLint({"unused"})
    public FcmPushProvider(CTPushProviderListener cTPushProviderListener, Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.handler = new FcmSdkHandlerImpl(cTPushProviderListener, context, cleverTapInstanceConfig);
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    public int getPlatform() {
        return 1;
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    @NonNull
    public PushConstants.PushType getPushType() {
        return this.handler.getPushType();
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    public boolean isAvailable() {
        return this.handler.isAvailable();
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    public boolean isSupported() {
        return this.handler.isSupported();
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    public int minSDKSupportVersionCode() {
        return 0;
    }

    @Override // com.clevertap.android.sdk.pushnotification.CTPushProvider
    public void requestToken() {
        this.handler.requestToken();
    }

    public void setHandler(IFcmSdkHandler iFcmSdkHandler) {
        this.handler = iFcmSdkHandler;
    }
}
