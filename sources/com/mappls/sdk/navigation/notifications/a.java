package com.mappls.sdk.navigation.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.R;
/* loaded from: classes11.dex */
public abstract class a {
    public static final int ERROR_NOTIFICATION_SERVICE_ID = 7;
    public static final int GPS_WAKE_UP_NOTIFICATION_SERVICE_ID = 8;
    public static final int GPX_NOTIFICATION_SERVICE_ID = 6;
    public static final int NAVIGATION_NOTIFICATION_SERVICE_ID = 5;
    public static final String PRIMARY_CHANNEL = "navigation";
    public static final int TOP_NOTIFICATION_SERVICE_ID = 100;
    public static final int WEAR_ERROR_NOTIFICATION_SERVICE_ID = 1007;
    public static final int WEAR_GPS_WAKE_UP_NOTIFICATION_SERVICE_ID = 1008;
    public static final int WEAR_GPX_NOTIFICATION_SERVICE_ID = 1006;
    public static final int WEAR_NAVIGATION_NOTIFICATION_SERVICE_ID = 1005;

    /* renamed from: a  reason: collision with root package name */
    public NotificationManager f12926a;
    public NavigationApplication app;
    public String b;
    public int color;
    public int icon;
    public boolean ongoing = true;
    public boolean top;

    /* renamed from: com.mappls.sdk.navigation.notifications.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public enum EnumC0640a {
        NAVIGATION,
        GPX,
        /* JADX INFO: Fake field, exist only in values array */
        GPS,
        /* JADX INFO: Fake field, exist only in values array */
        ERROR
    }

    public a(NavigationApplication navigationApplication, String str) {
        this.app = navigationApplication;
        this.b = str;
        init();
        b();
    }

    public final NotificationManager a() {
        if (this.f12926a == null) {
            this.f12926a = (NotificationManager) this.app.getSystemService("notification");
        }
        return this.f12926a;
    }

    public final void b() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("navigation", this.app.getString(R.string.mappls_noti_channel_navigation), 3);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.setLockscreenVisibility(0);
            notificationChannel.setSound(null, null);
            a().createNotificationChannel(notificationChannel);
        }
    }

    public abstract NotificationCompat.Builder buildNotification(boolean z);

    public final void c(NotificationManager notificationManager) {
    }

    public void closeSystemDialogs(Context context) {
        Intent intent = new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intent.setPackage(this.app.getPackageName());
        context.sendBroadcast(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r0 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
        r0.setPackage(null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r0 == null) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.core.app.NotificationCompat.Builder createBuilder(boolean r7) {
        /*
            r6 = this;
            com.mappls.sdk.navigation.MapplsNavigationHelper r0 = com.mappls.sdk.navigation.MapplsNavigationHelper.getInstance()
            java.lang.Class r0 = r0.getNavigationActivityClass()
            r1 = 0
            if (r0 == 0) goto L23
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Exception -> L1b
            com.mappls.sdk.navigation.NavigationApplication r2 = r6.app     // Catch: java.lang.Exception -> L1b
            com.mappls.sdk.navigation.MapplsNavigationHelper r3 = com.mappls.sdk.navigation.MapplsNavigationHelper.getInstance()     // Catch: java.lang.Exception -> L1b
            java.lang.Class r3 = r3.getNavigationActivityClass()     // Catch: java.lang.Exception -> L1b
            r0.<init>(r2, r3)     // Catch: java.lang.Exception -> L1b
            goto L20
        L1b:
            r0 = move-exception
            com.mappls.sdk.navigation.apis.NavigationLogger.d(r0)
            r0 = r1
        L20:
            if (r0 == 0) goto L38
            goto L35
        L23:
            com.mappls.sdk.navigation.NavigationApplication r0 = r6.app
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            com.mappls.sdk.navigation.NavigationApplication r2 = r6.app
            java.lang.String r2 = r2.getPackageName()
            android.content.Intent r0 = r0.getLaunchIntentForPackage(r2)
            if (r0 == 0) goto L38
        L35:
            r0.setPackage(r1)
        L38:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r1 < r2) goto L41
            r1 = 33554432(0x2000000, float:9.403955E-38)
            goto L43
        L41:
            r1 = 134217728(0x8000000, float:3.85186E-34)
        L43:
            com.mappls.sdk.navigation.NavigationApplication r2 = r6.app
            r3 = 0
            android.app.PendingIntent r0 = android.app.PendingIntent.getActivity(r2, r3, r0, r1)
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r4 = "com.mmi.maps.navigation.EXIT"
            r2.<init>(r4)
            com.mappls.sdk.navigation.NavigationApplication r4 = r6.app
            java.lang.String r4 = r4.getPackageName()
            r2.setPackage(r4)
            com.mappls.sdk.navigation.NavigationApplication r4 = r6.app
            android.app.PendingIntent r1 = android.app.PendingIntent.getBroadcast(r4, r3, r2, r1)
            androidx.core.app.NotificationCompat$Builder r2 = new androidx.core.app.NotificationCompat$Builder
            com.mappls.sdk.navigation.NavigationApplication r4 = r6.app
            java.lang.String r5 = "navigation"
            r2.<init>(r4, r5)
            r4 = 1
            androidx.core.app.NotificationCompat$Builder r2 = r2.setVisibility(r4)
            boolean r5 = r6.top
            if (r5 == 0) goto L74
            r5 = r4
            goto L78
        L74:
            int r5 = r6.getPriority()
        L78:
            androidx.core.app.NotificationCompat$Builder r2 = r2.setPriority(r5)
            boolean r5 = r6.ongoing
            if (r5 == 0) goto L83
            if (r7 != 0) goto L83
            r3 = r4
        L83:
            androidx.core.app.NotificationCompat$Builder r2 = r2.setOngoing(r3)
            androidx.core.app.NotificationCompat$Builder r0 = r2.setContentIntent(r0)
            long r2 = java.lang.System.currentTimeMillis()
            androidx.core.app.NotificationCompat$Builder r0 = r0.setWhen(r2)
            int r2 = com.mappls.sdk.navigation.R.drawable.ic_close_white_24dp
            java.lang.String r3 = "Exit Navigation"
            androidx.core.app.NotificationCompat$Builder r0 = r0.addAction(r2, r3, r1)
            com.mappls.sdk.navigation.NavigationApplication r1 = r6.app
            com.mappls.sdk.navigation.notifications.a$a r2 = r6.getType()
            android.app.PendingIntent r1 = com.mappls.sdk.navigation.notifications.NotificationDismissReceiver.a(r1, r2)
            androidx.core.app.NotificationCompat$Builder r0 = r0.setDeleteIntent(r1)
            boolean r1 = r6.top
            if (r1 == 0) goto Lb7
            java.lang.String r1 = r6.b
            androidx.core.app.NotificationCompat$Builder r1 = r0.setGroup(r1)
            r7 = r7 ^ r4
            r1.setGroupSummary(r7)
        Lb7:
            int r7 = r6.color
            if (r7 == 0) goto Lbe
            r0.setColor(r7)
        Lbe:
            int r7 = r6.icon
            if (r7 == 0) goto Lc5
            r0.setSmallIcon(r7)
        Lc5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.notifications.a.createBuilder(boolean):androidx.core.app.NotificationCompat$Builder");
    }

    public String getGroupName() {
        return this.b;
    }

    public abstract int getNavigationNotificationId();

    public abstract int getNavigationWearableNotificationId();

    public abstract int getPriority();

    public abstract EnumC0640a getType();

    public void init() {
    }

    public abstract boolean isActive();

    public abstract boolean isEnabled();

    public boolean isTop() {
        return this.top;
    }

    public void onNotificationDismissed() {
    }

    public boolean refreshNotification() {
        NotificationCompat.Builder buildNotification;
        NotificationManager a2;
        int navigationNotificationId;
        if (!isEnabled() || (buildNotification = buildNotification(false)) == null) {
            a().cancel(getNavigationNotificationId());
            return false;
        }
        Notification build = buildNotification.build();
        setupNotification(build);
        if (this.top) {
            a2 = a();
            navigationNotificationId = 100;
        } else {
            a2 = a();
            navigationNotificationId = getNavigationNotificationId();
        }
        a2.notify(navigationNotificationId, build);
        c(a());
        return true;
    }

    public void removeNotification() {
        a().cancel(getNavigationNotificationId());
        a().cancel(100);
        a().cancel(getNavigationWearableNotificationId());
    }

    public void setTop(boolean z) {
        this.top = z;
    }

    public void setupNotification(Notification notification) {
        notification.flags = 64;
    }

    public boolean showNotification() {
        NotificationCompat.Builder buildNotification;
        if (!isEnabled() || (buildNotification = buildNotification(false)) == null) {
            return false;
        }
        Notification build = buildNotification.build();
        setupNotification(build);
        a().notify(this.top ? 100 : getNavigationNotificationId(), build);
        c(a());
        return true;
    }
}
