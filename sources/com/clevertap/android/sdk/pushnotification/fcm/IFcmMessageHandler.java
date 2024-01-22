package com.clevertap.android.sdk.pushnotification.fcm;

import android.content.Context;
import androidx.annotation.RestrictTo;
import com.google.firebase.messaging.RemoteMessage;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public interface IFcmMessageHandler {
    boolean createNotification(Context context, RemoteMessage remoteMessage);

    boolean onNewToken(Context context, String str);
}
