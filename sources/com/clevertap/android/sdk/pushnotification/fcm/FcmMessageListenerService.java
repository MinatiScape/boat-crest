package com.clevertap.android.sdk.pushnotification.fcm;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
/* loaded from: classes2.dex */
public class FcmMessageListenerService extends FirebaseMessagingService {
    public IFcmMessageHandler h = new CTFcmMessageHandler();

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        this.h.createNotification(getApplicationContext(), remoteMessage);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(@NonNull String str) {
        super.onNewToken(str);
        this.h.onNewToken(getApplicationContext(), str);
    }
}
