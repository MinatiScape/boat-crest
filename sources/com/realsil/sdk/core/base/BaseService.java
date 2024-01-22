package com.realsil.sdk.core.base;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.IBinder;
import android.view.Display;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public abstract class BaseService extends Service {

    /* renamed from: a  reason: collision with root package name */
    public DisplayManager f13552a;
    @Keep
    public Context mContext;
    @Keep
    public int notificationId = 1230;
    public final DisplayManager.DisplayListener b = new DisplayManager.DisplayListener(this) { // from class: com.realsil.sdk.core.base.BaseService.1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    };

    public Notification buildNotification(Context context) {
        Notification.Builder builder;
        int i = Build.VERSION.SDK_INT;
        if (i >= 16) {
            if (i >= 26) {
                builder = new Notification.Builder(getApplicationContext(), getChannelId());
            } else {
                builder = new Notification.Builder(getApplicationContext());
            }
            builder.setContentText("GuardService").setWhen(System.currentTimeMillis());
            return builder.build();
        }
        return new Notification();
    }

    public String getChannelId() {
        return "rtk_channel_id";
    }

    public String getChannelName() {
        return "rtk_channel_name";
    }

    public Notification getNotification(String str, String str2) {
        NotificationCompat.Builder when = new NotificationCompat.Builder(this, getChannelId()).setContentText(str2).setContentTitle(str).setOngoing(true).setPriority(1).setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= 26) {
            when.setChannelId(getChannelId());
        }
        return when.build();
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public boolean isScreenOn() {
        Display[] displays;
        DisplayManager displayManager = this.f13552a;
        if (displayManager == null || (displays = displayManager.getDisplays()) == null) {
            return false;
        }
        for (Display display : displays) {
            if (Build.VERSION.SDK_INT >= 20 && display.getState() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isServiceRunningInForeground(Context context) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE)) {
            if (getClass().getName().equals(runningServiceInfo.service.getClassName()) && runningServiceInfo.foreground) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ZLogger.v("in onBind()");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        DisplayManager displayManager = (DisplayManager) getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        this.f13552a = displayManager;
        if (displayManager != null) {
            displayManager.registerDisplayListener(this.b, null);
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(getChannelId(), getChannelName(), 0);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.setLockscreenVisibility(0);
            NotificationManager notificationManager2 = (NotificationManager) getSystemService("notification");
            if (notificationManager2 != null) {
                notificationManager2.createNotificationChannel(notificationChannel);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        DisplayManager displayManager = this.f13552a;
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.b);
        }
        stopForeground(true);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        ZLogger.v("in onRebind()");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        ZLogger.d("Last client unbound from service");
        return true;
    }

    public void setNotificationId(int i) {
        this.notificationId = i;
    }

    public void showNotification(String str, String str2, ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        showNotification(str, str2, PendingIntent.getActivity(this, 0, intent, 0), -1, -1);
    }

    public void showNotification(String str, String str2, PendingIntent pendingIntent, int i, int i2) {
        Notification notification;
        Notification.Builder builder;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 16) {
            if (i3 >= 26) {
                builder = new Notification.Builder(getApplicationContext(), getChannelId());
            } else {
                builder = new Notification.Builder(getApplicationContext());
            }
            if (pendingIntent != null) {
                builder.setContentIntent(pendingIntent);
            }
            if (i != -1) {
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), i));
            }
            if (i2 != -1) {
                builder.setSmallIcon(i2);
            }
            builder.setContentTitle(str).setContentText(str2).setWhen(System.currentTimeMillis());
            notification = builder.build();
        } else {
            notification = new Notification();
        }
        if (notification != null) {
            notification.flags = 34;
            startForeground(getNotificationId(), notification);
        }
    }
}
