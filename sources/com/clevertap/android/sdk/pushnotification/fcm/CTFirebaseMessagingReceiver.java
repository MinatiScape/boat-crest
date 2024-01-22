package com.clevertap.android.sdk.pushnotification.fcm;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.clevertap.android.sdk.CTXtensions;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.interfaces.NotificationRenderedListener;
import com.clevertap.android.sdk.pushnotification.PushNotificationUtil;
import com.google.firebase.messaging.RemoteMessage;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class CTFirebaseMessagingReceiver extends BroadcastReceiver implements NotificationRenderedListener {
    public CountDownTimer h;
    public String i = "";
    public boolean j;
    public BroadcastReceiver.PendingResult k;
    public long l;

    /* loaded from: classes2.dex */
    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            CTFirebaseMessagingReceiver.this.c("receiver life time is expired");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(Context context, Bundle bundle) {
        try {
            try {
                CleverTapAPI globalInstance = CleverTapAPI.getGlobalInstance(context, PushNotificationUtil.getAccountIdFromNotificationBundle(bundle));
                if (globalInstance != null) {
                    CTXtensions.flushPushImpressionsOnPostAsyncSafely(globalInstance, "CTRM#flushQueueSync", Constants.D_SRC_PI_R, context);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.v("CTRM", "Failed executing CTRM flushQueueSync thread.", e);
            }
        } finally {
            c("flush from receiver is done!");
        }
    }

    public final void c(String str) {
        try {
            Logger.v("CTRM", "got a signal to kill receiver and timer because " + str);
            if (!this.i.trim().isEmpty()) {
                CleverTapAPI.removeNotificationRenderedListener(this.i);
            }
            long nanoTime = System.nanoTime();
            if (this.k != null && !this.j) {
                Logger.v("CTRM", "informing OS to kill receiver...");
                this.k.finish();
                this.j = true;
                CountDownTimer countDownTimer = this.h;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                Logger.v("CTRM", "informed OS to kill receiver...");
                Logger.v("CTRM", "receiver was alive for " + TimeUnit.NANOSECONDS.toSeconds(nanoTime - this.l) + " seconds");
                return;
            }
            Logger.v("CTRM", "have already informed OS to kill receiver, can not inform again else OS will get angry :-O");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.clevertap.android.sdk.interfaces.NotificationRenderedListener
    @SuppressLint({"RestrictedApi"})
    public void onNotificationRendered(boolean z) {
        Logger.v("CTRM", "push impression sent successfully by core, i should inform OS to kill receiver. my callback key is " + this.i);
        c("push impression sent successfully by core");
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"RestrictedApi"})
    public void onReceive(final Context context, Intent intent) {
        RemoteMessage remoteMessage;
        final Bundle bundle;
        this.l = System.nanoTime();
        Logger.d("CTRM", "received a message from Firebase");
        if (context == null || intent == null || (bundle = new b().toBundle((remoteMessage = new RemoteMessage(intent.getExtras())))) == null) {
            return;
        }
        if (remoteMessage.getPriority() != 2) {
            Logger.d("CTRM", "returning from CTRM because message priority is not normal");
            return;
        }
        long parseLong = Long.parseLong(bundle.getString("ctrmt", "4500"));
        this.k = goAsync();
        if (CleverTapAPI.getNotificationInfo(bundle).fromCleverTap) {
            if (Utils.isRenderFallback(remoteMessage, context)) {
                String buildPushNotificationRenderedListenerKey = PushNotificationUtil.buildPushNotificationRenderedListenerKey(PushNotificationUtil.getAccountIdFromNotificationBundle(bundle), PushNotificationUtil.getPushIdFromNotificationBundle(bundle));
                this.i = buildPushNotificationRenderedListenerKey;
                CleverTapAPI.addNotificationRenderedListener(buildPushNotificationRenderedListenerKey, this);
                a aVar = new a(parseLong, 1000L);
                this.h = aVar;
                aVar.start();
                new Thread(new Runnable() { // from class: com.clevertap.android.sdk.pushnotification.fcm.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        CTFirebaseMessagingReceiver.this.d(context, bundle);
                    }
                }).start();
                return;
            }
            Logger.v("CTRM", "Notification payload does not have a fallback key.");
            c("isRenderFallback is false");
            return;
        }
        Logger.v("CTRM", "Notification payload is not from CleverTap.");
        c("push is not from CleverTap.");
    }
}
