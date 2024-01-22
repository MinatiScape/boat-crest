package com.clevertap.android.sdk.pushnotification.fcm;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.interfaces.INotificationParser;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
/* loaded from: classes2.dex */
public class b implements INotificationParser<RemoteMessage> {
    @Override // com.clevertap.android.sdk.interfaces.INotificationParser
    /* renamed from: a */
    public Bundle toBundle(@NonNull RemoteMessage remoteMessage) {
        try {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            Logger.d(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Found Valid Notification Message ");
            return bundle;
        } catch (Throwable th) {
            th.printStackTrace();
            Logger.d(PushConstants.LOG_TAG, PushConstants.FCM_LOG_TAG + "Invalid Notification Message ", th);
            return null;
        }
    }
}
