package com.clevertap.android.sdk.pushnotification.fcm;

import android.os.Bundle;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.messaging.RemoteMessage;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FcmNotificationBundleManipulation implements INotificationBundleManipulation<RemoteMessage> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Bundle f2666a;

    public FcmNotificationBundleManipulation(@NotNull Bundle messageBundle) {
        Intrinsics.checkNotNullParameter(messageBundle, "messageBundle");
        this.f2666a = messageBundle;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.INotificationBundleManipulation
    @NotNull
    public Bundle build() {
        return this.f2666a;
    }

    @Override // com.clevertap.android.sdk.pushnotification.fcm.INotificationBundleManipulation
    @NotNull
    public INotificationBundleManipulation<RemoteMessage> addPriority(@NotNull RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (message.getOriginalPriority() != message.getPriority()) {
            int priority = message.getPriority();
            this.f2666a.putString(Constants.WZRK_PN_PRT, priority != 0 ? priority != 1 ? priority != 2 ? "" : Constants.PRIORITY_NORMAL : Constants.PRIORITY_HIGH : Constants.PRIORITY_UNKNOWN);
        }
        return this;
    }
}
