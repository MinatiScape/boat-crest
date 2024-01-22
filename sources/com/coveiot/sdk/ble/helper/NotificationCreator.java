package com.coveiot.sdk.ble.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.coveiot.sdk.ble.R;
import com.coveiot.sdk.ble.model.NotificationChannelInfo;
import com.coveiot.sdk.ble.utils.BleUtils;
/* loaded from: classes9.dex */
public class NotificationCreator {

    /* renamed from: a  reason: collision with root package name */
    public static Notification f7574a;
    public static int b;
    public static String c;

    public static Notification getNotification(Context context) {
        if (f7574a == null) {
            PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent("splash"), 67108864);
            NotificationChannelInfo notificationChanneInfo = BleUtils.getNotificationChanneInfo(context);
            String appName = BleUtils.isEmpty(notificationChanneInfo.getAppName()) ? "Ble Sdk" : notificationChanneInfo.getAppName();
            String description = BleUtils.isEmpty(notificationChanneInfo.getDescription()) ? "Background service to keep your SF connected to the app." : notificationChanneInfo.getDescription();
            String channelName = BleUtils.isEmpty(notificationChanneInfo.getChannelName()) ? "Ble_Sdk_channel" : notificationChanneInfo.getChannelName();
            String channelDefaultId = notificationChanneInfo.getChannelDefaultId();
            int drawable = notificationChanneInfo.getDrawable();
            b = Integer.parseInt(channelDefaultId);
            c = channelName;
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(c, appName, 2);
                notificationChannel.setDescription(description);
                notificationChannel.enableLights(false);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, c);
            builder.setChannelId(c);
            builder.setContentTitle(appName + " is running.");
            if (drawable == 0) {
                builder.setSmallIcon(R.mipmap.ic_launcher);
            }
            BitmapFactory.decodeResource(context.getResources(), drawable);
            builder.setColor(ContextCompat.getColor(context, R.color.transparent_white));
            builder.setSmallIcon(drawable);
            builder.setContentIntent(activity);
            Notification build = builder.build();
            f7574a = build;
            int i = build.flags | 32;
            build.flags = i;
            int i2 = i | 2;
            build.flags = i2;
            build.flags = i2 | 64;
        }
        return f7574a;
    }

    public static int getNotificationId() {
        return b;
    }
}
