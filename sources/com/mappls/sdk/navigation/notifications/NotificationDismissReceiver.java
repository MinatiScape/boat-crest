package com.mappls.sdk.navigation.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NotificationHelper;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.notifications.a;
/* loaded from: classes11.dex */
public class NotificationDismissReceiver extends BroadcastReceiver {
    public static PendingIntent a(NavigationApplication navigationApplication, a.EnumC0640a enumC0640a) {
        Intent intent = new Intent(navigationApplication, NotificationDismissReceiver.class);
        intent.putExtra("com.mmi.navigation.notifications.NotificationType", enumC0640a.name());
        return PendingIntent.getBroadcast(navigationApplication.getApplicationContext(), 0, intent, Build.VERSION.SDK_INT >= 31 ? 33554432 : 134217728);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = ((NavigationApplication) context.getApplicationContext()).getNotificationHelper();
        String string = intent.getExtras().getString("com.mmi.navigation.notifications.NotificationType");
        if (com.mappls.sdk.navigation.util.a.a(string)) {
            return;
        }
        try {
            notificationHelper.onNotificationDismissed((a.EnumC0640a) Enum.valueOf(a.EnumC0640a.class, string));
        } catch (Exception e) {
            NavigationLogger.d(e);
        }
    }
}
