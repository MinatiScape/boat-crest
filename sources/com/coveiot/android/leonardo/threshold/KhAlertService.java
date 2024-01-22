package com.coveiot.android.leonardo.threshold;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.bleabstract.api.BleApiManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class KhAlertService extends Service {
    @NotNull
    public final Handler h = new Handler(Looper.getMainLooper());

    public final void a() {
        Notification build;
        NotificationInfo data = BleApiUtils.getData();
        if (data.getAppColor() == 0) {
            BleApiManager.getInstance(this);
            data = BleApiUtils.getData();
            if (data.getAppColor() == 0) {
                BleApiUtils.getMetadata(this);
                data = BleApiUtils.getData();
            }
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            StringBuilder sb = new StringBuilder();
            String appName = data.getAppName();
            Intrinsics.checkNotNull(appName);
            sb.append(appName);
            sb.append(" is running");
            NotificationChannel notificationChannel = new NotificationChannel("101", sb.toString(), 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(this, "101");
            StringBuilder sb2 = new StringBuilder();
            String appName2 = data.getAppName();
            Intrinsics.checkNotNull(appName2);
            sb2.append(appName2);
            sb2.append(" is running");
            build = builder.setContentTitle(sb2.toString()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, \"101\")\n   …\n                .build()");
        } else if (i >= 21) {
            Notification.Builder builder2 = new Notification.Builder(this);
            StringBuilder sb3 = new StringBuilder();
            String appName3 = data.getAppName();
            Intrinsics.checkNotNull(appName3);
            sb3.append(appName3);
            sb3.append(" is running");
            build = builder2.setContentTitle(sb3.toString()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            Notification.Builder builder3 = new Notification.Builder(this);
            StringBuilder sb4 = new StringBuilder();
            String appName4 = data.getAppName();
            Intrinsics.checkNotNull(appName4);
            sb4.append(appName4);
            sb4.append(" is running");
            build = builder3.setContentTitle(sb4.toString()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(101, build);
    }

    @NotNull
    public final Handler getMainHandler() {
        return this.h;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        a();
        return super.onStartCommand(intent, i, i2);
    }
}
