package com.clevertap.android.sdk.pushnotification.fcm;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.pushnotification.CTPushProviderListener;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.utils.PackageUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
/* loaded from: classes2.dex */
public class FcmSdkHandlerImpl implements IFcmSdkHandler {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapInstanceConfig f2667a;
    public final Context b;
    public final CTPushProviderListener c;

    /* loaded from: classes2.dex */
    public class a implements OnCompleteListener<String> {
        public a() {
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(@NonNull Task<String> task) {
            if (!task.isSuccessful()) {
                CleverTapInstanceConfig cleverTapInstanceConfig = FcmSdkHandlerImpl.this.f2667a;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json failed", task.getException());
                FcmSdkHandlerImpl.this.c.onNewToken(null, FcmSdkHandlerImpl.this.getPushType());
                return;
            }
            String result = task.getResult() != null ? task.getResult() : null;
            CleverTapInstanceConfig cleverTapInstanceConfig2 = FcmSdkHandlerImpl.this.f2667a;
            cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "FCM token using googleservices.json - " + result);
            FcmSdkHandlerImpl.this.c.onNewToken(result, FcmSdkHandlerImpl.this.getPushType());
        }
    }

    public FcmSdkHandlerImpl(CTPushProviderListener cTPushProviderListener, Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.b = context;
        this.f2667a = cleverTapInstanceConfig;
        this.c = cTPushProviderListener;
        ManifestInfo.getInstance(context);
    }

    public String c() {
        return FirebaseApp.getInstance().getOptions().getGcmSenderId();
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public PushConstants.PushType getPushType() {
        return PushConstants.PushType.FCM;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public boolean isAvailable() {
        try {
            if (!PackageUtils.isGooglePlayServicesAvailable(this.b)) {
                CleverTapInstanceConfig cleverTapInstanceConfig = this.f2667a;
                cleverTapInstanceConfig.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Google Play services is currently unavailable.");
                return false;
            } else if (TextUtils.isEmpty(c())) {
                CleverTapInstanceConfig cleverTapInstanceConfig2 = this.f2667a;
                cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "The FCM sender ID is not set. Unable to register for FCM.");
                return false;
            } else {
                return true;
            }
        } catch (Throwable th) {
            CleverTapInstanceConfig cleverTapInstanceConfig3 = this.f2667a;
            cleverTapInstanceConfig3.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Unable to register with FCM.", th);
            return false;
        }
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public boolean isSupported() {
        return PackageUtils.isGooglePlayStoreAvailable(this.b);
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.IFcmSdkHandler
    public void requestToken() {
        try {
            CleverTapInstanceConfig cleverTapInstanceConfig = this.f2667a;
            cleverTapInstanceConfig.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Requesting FCM token using googleservices.json");
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new a());
        } catch (Throwable th) {
            CleverTapInstanceConfig cleverTapInstanceConfig2 = this.f2667a;
            cleverTapInstanceConfig2.log(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Error requesting FCM token", th);
            this.c.onNewToken(null, getPushType());
        }
    }
}
