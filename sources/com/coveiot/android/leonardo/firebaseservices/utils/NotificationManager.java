package com.coveiot.android.leonardo.firebaseservices.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.activitymodes.activities.ActivityHistory;
import com.coveiot.android.activitymodes.activities.ActivityModeShareScreen;
import com.coveiot.android.activitymodes.models.ActivityShareData;
import com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnesschallenges.FitnessChallengeDetails;
import com.coveiot.android.healthbuddies.activities.ManageHealthBuddiesActivity;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinsWebViewer;
import com.coveiot.android.leonardo.boatcoin.ftu.activities.ActivityBoatCoinsFTU;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.HealthNavigationElement;
import com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG;
import com.coveiot.android.leonardo.firebaseservices.enums.FcmMessageTypes;
import com.coveiot.android.leonardo.firebaseservices.enums.InboxMessageTypes;
import com.coveiot.android.leonardo.firebaseservices.model.AppUpdateCoveFCMessage;
import com.coveiot.android.leonardo.firebaseservices.model.CustomNotifiacationModel;
import com.coveiot.android.leonardo.firebaseservices.model.FCMFitnessReportSubscribeMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FCMFitnessVideoMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmSportsSelectMatchMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FcmSportsSettingsMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FirmwareUpdateCoveFCMessage;
import com.coveiot.android.leonardo.firebaseservices.model.FitnessCMCheerCloverMessage;
import com.coveiot.android.leonardo.firebaseservices.service.CustomNotificationService;
import com.coveiot.android.leonardo.onboarding.splash.activities.ActivitySplash;
import com.coveiot.android.leonardo.phonelocator.PhoneFinderNotificationReciever;
import com.coveiot.android.leonardo.remotecamera.CameraActivity;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.NotificationTypes;
import com.coveiot.android.leonardo.utils.TroubleshootNotificationClickReceiver;
import com.coveiot.android.navigation.activities.ActivityDirections;
import com.coveiot.android.sportsnotification.SportsDisclaimerActivity;
import com.coveiot.android.sportsnotification.SportsNotificationActivity;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.watchfaceui.activities.ActivityWatchFace;
import com.coveiot.android.watchfaceui.webSupport.ActivityInAppWebViewerWatchface;
import com.coveiot.android.weeklyreport.activities.ActivityWeeklyReport;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.sdk.ble.api.response.NotifyNavigationEventRes;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Locale;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes2.dex */
public class NotificationManager {
    public static NotificationManager c = new NotificationManager();

    /* renamed from: a  reason: collision with root package name */
    public int f4830a = 10;
    public String b = AppConstants.CHANNEL_ID.getValue();

    /* loaded from: classes2.dex */
    public class a extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public a(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class b extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public b(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class c extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public c(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class d extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public d(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class e extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public e(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    /* loaded from: classes2.dex */
    public class f extends CustomTarget<Bitmap> {
        public final /* synthetic */ Context k;
        public final /* synthetic */ String l;
        public final /* synthetic */ String m;
        public final /* synthetic */ PendingIntent n;

        public f(Context context, String str, String str2, PendingIntent pendingIntent) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = pendingIntent;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
            NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            if (bitmap != null) {
                NotificationManager.this.g(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n, bitmap);
            } else {
                NotificationManager.this.e(this.k, R.drawable.ic_stat_notification_icon_cove, this.l, this.m, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, this.n);
            }
        }
    }

    public static synchronized NotificationManager getInstance() {
        NotificationManager notificationManager;
        synchronized (NotificationManager.class) {
            notificationManager = c;
        }
        return notificationManager;
    }

    public final void c(Context context, CustomNotifiacationModel customNotifiacationModel) {
        Intent intent = new Intent(context, CustomNotificationService.class);
        Bundle bundle = new Bundle();
        bundle.putString(context.getResources().getString(R.string.data), new Gson().toJson(customNotifiacationModel));
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public void cancleNotification(Context context, int i) {
        try {
            ((android.app.NotificationManager) context.getSystemService("notification")).cancel(i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void d(Context context, int i, String str, String str2, InboxMessageTypes inboxMessageTypes) {
        Intent intent = new Intent(context, ActivitySplash.class);
        intent.setFlags(PKIFailureInfo.duplicateCertReq);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.b).setSmallIcon(i).setContentTitle(str).setContentText(str2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setColor(ContextCompat.getColor(context, R.color.colorPrimary)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(context, 0, intent, 201326592));
        contentIntent.setSmallIcon(i);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, R.color.color_ffffff));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        contentIntent.setAutoCancel(true);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.b, context.getResources().getString(R.string.notification_name), 3));
        }
        int i2 = this.f4830a;
        this.f4830a = i2 + 1;
        notificationManager.notify(i2, contentIntent.build());
    }

    public final void e(Context context, int i, String str, String str2, InboxMessageTypes inboxMessageTypes, PendingIntent pendingIntent) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.b).setSmallIcon(i).setContentTitle(str).setContentText(str2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setColor(ContextCompat.getColor(context, R.color.color_ffffff)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(pendingIntent);
        contentIntent.setSmallIcon(i);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, R.color.color_ffffff));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        contentIntent.setAutoCancel(true);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.b, context.getResources().getString(R.string.notification_name), 3));
        }
        Notification build = contentIntent.build();
        int i2 = this.f4830a;
        this.f4830a = i2 + 1;
        notificationManager.notify(i2, build);
    }

    public final void f(Context context, int i, String str, String str2, InboxMessageTypes inboxMessageTypes, PendingIntent pendingIntent, int i2) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.b).setSmallIcon(i).setContentTitle(str).setContentText(str2).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(str2)).setColor(ContextCompat.getColor(context, R.color.color_ffffff)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(pendingIntent);
        contentIntent.setSmallIcon(i);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, R.color.color_ffffff));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        contentIntent.setAutoCancel(true);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.b, context.getResources().getString(R.string.notification_name), 3));
        }
        notificationManager.notify(i2, contentIntent.build());
    }

    public final void g(Context context, int i, String str, String str2, InboxMessageTypes inboxMessageTypes, PendingIntent pendingIntent, Bitmap bitmap) {
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, this.b).setSmallIcon(i).setContentTitle(str).setContentText(str2).setAutoCancel(true).setLargeIcon(bitmap).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(str2).bigLargeIcon(bitmap)).setColor(ContextCompat.getColor(context, R.color.color_ffffff)).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(pendingIntent);
        contentIntent.setSmallIcon(i);
        contentIntent.setContentTitle(str);
        contentIntent.setColor(ContextCompat.getColor(context, R.color.color_ffffff));
        contentIntent.setContentText(str2);
        contentIntent.setSound(RingtoneManager.getDefaultUri(2));
        contentIntent.setStyle(new NotificationCompat.BigPictureStyle().setSummaryText(str2).bigPicture(bitmap).bigLargeIcon(bitmap));
        contentIntent.setAutoCancel(true);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.b, context.getResources().getString(R.string.notification_name), 3));
        }
        Notification build = contentIntent.build();
        build.priority = 2;
        int i2 = this.f4830a;
        this.f4830a = i2 + 1;
        notificationManager.notify(i2, build);
    }

    public void notify(Context context, String str, String str2, String str3) {
        d(context, R.drawable.ic_stat_notification_icon_cove, str, str2, null);
    }

    public void notifyActivityInsight(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, new Intent(context, ActivityHistory.class), 201326592), i);
        }
    }

    public void notifyAppUpdate(Context context, AppUpdateCoveFCMessage appUpdateCoveFCMessage) {
        Intent intent;
        String packageName = context.getPackageName();
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
        } catch (ActivityNotFoundException unused) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
        }
        intent.setFlags(PKIFailureInfo.duplicateCertReq);
        e(context, R.drawable.ic_stat_notification_icon_cove, appUpdateCoveFCMessage.getTitle(), appUpdateCoveFCMessage.getMessage(), InboxMessageTypes.APP_UPDATE, PendingIntent.getActivity(context, 0, intent, 201326592));
    }

    public void notifyBoatCoinsEarnedMessage(Context context, String str, String str2, String str3, String str4) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityBoatCoinsWebViewer.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), SessionManager.getInstance(context).getCoinsHomeUrl());
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.FCM_NOTIFICATION_TYPE.getValue(), FcmMessageTypes.COINS_EARNED_MESSAGE.name());
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.setFlags(67108864);
            if (!SessionManager.getInstance(context).isBoatCoinsFTUShown()) {
                intent = new Intent(context, ActivityBoatCoinsFTU.class);
            }
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str4)) {
                Glide.with(context).asBitmap().m21load(str4).into((RequestBuilder<Bitmap>) new a(context, str2, str3, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str2, str3, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void notifyBoatCoinsFeatureMessage(Context context, String str, String str2, String str3, String str4, String str5) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityBoatCoinsWebViewer.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), str2);
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.putExtra(ThemeConstants.FCM_NOTIFICATION_TYPE.getValue(), FcmMessageTypes.COINS_FEATURE_MESSAGE.name());
            intent.setFlags(67108864);
            if (!SessionManager.getInstance(context).isBoatCoinsFTUShown()) {
                intent = new Intent(context, ActivityBoatCoinsFTU.class);
            }
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str5)) {
                Glide.with(context).asBitmap().m21load(str5).into((RequestBuilder<Bitmap>) new e(context, str3, str4, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str3, str4, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void notifyContactTracingSyncTimeout(Context context, int i) {
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.contacting_sync_failed), context.getResources().getString(R.string.contacting_sync_failed_msg), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, new Intent(context, ActivityDashboardNew.class), 201326592), i);
    }

    public void notifyCustomNotification(Context context, String str, String str2, CustomNotifiacationModel customNotifiacationModel) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent();
            intent.setAction(context.getResources().getString(R.string.app_home_activity));
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            c(context, customNotifiacationModel);
        }
    }

    public void notifyEnergyScore(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.putExtra(AppConstants.FCM_NOTIFICATION_TYPE.name(), HealthNavigationElement.ENERGY_METER.name());
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
        }
    }

    public void notifyFirmwareUpdate(Context context, FirmwareUpdateCoveFCMessage firmwareUpdateCoveFCMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            intent.putExtra(AppConstants.FCM_NOTIFICATION_TYPE.name(), AppConstants.FIRMWARE_UPDATE.name());
            e(context, R.drawable.ic_stat_notification_icon_cove, firmwareUpdateCoveFCMessage.getTitle(), firmwareUpdateCoveFCMessage.getMessage(), InboxMessageTypes.FIRMWARE_UPDATE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyFitnessChallengeFeatureMessage(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, FitnessChallengeDetails.class);
            intent.putExtra(AppConstants.CHALLENGE_ID.getValue(), str5);
            intent.putExtra(AppConstants.CHALLENGE_TYPE.getValue(), str6);
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.setFlags(67108864);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str4)) {
                Glide.with(context).asBitmap().m21load(str4).into((RequestBuilder<Bitmap>) new f(context, str2, str3, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str2, str3, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void notifyFitnessCheerClover(Context context, FitnessCMCheerCloverMessage fitnessCMCheerCloverMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            intent.putExtra(AppConstants.NOTIFICATION_TYPE.name(), NotificationTypes.BUDDIES.name());
            intent.putExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, 1);
            e(context, R.drawable.ic_stat_notification_icon_cove, fitnessCMCheerCloverMessage.getSenderUserName(), fitnessCMCheerCloverMessage.getMessage(), InboxMessageTypes.FITNESSBUDDY_CHEER_MESSAGE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyFitnessCloversAcceptReject(Context context, String str, String str2) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            intent.putExtra(AppConstants.NOTIFICATION_TYPE.name(), NotificationTypes.BUDDIES.name());
            intent.putExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, 2);
            e(context, R.drawable.ic_stat_notification_icon_cove, str, str2, InboxMessageTypes.FITNESS_ACCEPT_REJECT_MESSAGE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyFitnessReportSubscription(Context context, FCMFitnessReportSubscribeMessage fCMFitnessReportSubscribeMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(context).getWeeklyReportData();
            Intent intent = new Intent(context, ActivityWeeklyReport.class);
            if (weeklyReportData != null && weeklyReportData.isSubscribed()) {
                WeeklyReportConstant weeklyReportConstant = WeeklyReportConstant.SUBSCRIBE_SUCCESS;
                intent.putExtra(weeklyReportConstant.getValue(), weeklyReportConstant.getValue());
            } else {
                WeeklyReportConstant weeklyReportConstant2 = WeeklyReportConstant.EDIT_EMAIL;
                intent.putExtra(weeklyReportConstant2.getValue(), weeklyReportConstant2.getValue());
            }
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            e(context, R.drawable.ic_stat_notification_icon_cove, fCMFitnessReportSubscribeMessage.getTitle(), fCMFitnessReportSubscribeMessage.getMessage(), InboxMessageTypes.FITNESS_REPORT_SUBSCRIPTION, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyFitnessVideoUpdate(Context context, FCMFitnessVideoMessage fCMFitnessVideoMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, WorkoutVideosActivity.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            e(context, R.drawable.ic_stat_notification_icon_cove, fCMFitnessVideoMessage.getTitle(), fCMFitnessVideoMessage.getMessage(), InboxMessageTypes.WORKOUT_VIDEOS_UPDATE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyGenericMessage(Context context, String str, String str2, long j) {
        d(context, R.drawable.ic_stat_notification_icon_cove, str, str2, InboxMessageTypes.GENERIC_MESSAGE);
    }

    public void notifyGoalCompletion(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.putExtra(AppConstants.FCM_NOTIFICATION_TYPE.name(), HealthNavigationElement.STEPS.name());
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.goal_completion_title), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
        }
    }

    public void notifyGuardianDeleteAcceptReject(Context context, String str, String str2) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ManageHealthBuddiesActivity.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            intent.putExtra(AppConstants.NOTIFICATION_TYPE.name(), NotificationTypes.BUDDIES.name());
            intent.putExtra(FitnessConstants.EXTRA_FITNESS_TAB_POSITION, 2);
            e(context, R.drawable.ic_stat_notification_icon_cove, str, str2, InboxMessageTypes.FITNESS_ACCEPT_REJECT_MESSAGE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyGuardianDeletionMessage(Integer num, Context context, String str, String str2, long j) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            intent.putExtra(AppConstants.NOTIFICATION_TYPE.name(), NotificationTypes.GUARDIAN.name());
            intent.putExtra(Constants.EXTRA_GUARDIAN_TAB_POSITION.getValue(), num);
            e(context, R.drawable.ic_stat_notification_icon_cove, str, str2, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, PendingIntent.getActivity(context, 0, intent, 201326592));
        }
    }

    public void notifyLowBatterAlertAlert(Context context, int i) {
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.low_battery_alert), context.getResources().getString(R.string.low_battery), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, new Intent(context, ActivityDashboardNew.class), 201326592), i);
    }

    public void notifyMultipleBestActivities(Context context, int i, ArrayList<String> arrayList, String str) {
        Intent intent = new Intent(context, ActivityHistory.class);
        intent.putExtra(ActivityShareData.BEST_ACTIVITIES_ID, arrayList);
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
    }

    public void notifyOnTroubleshootAppNotificationTest(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.troubleshoot), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getBroadcast(context, 0, new Intent(context, TroubleshootNotificationClickReceiver.class), 201326592), i);
        }
    }

    public void notifyPhoneFinder(Context context) {
        Intent intent = new Intent(context, PhoneFinderNotificationReciever.class);
        intent.putExtra(AppConstants.PHONE_FINDER_NOTIFICATION_ID.name(), this.f4830a);
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.phone_found), context.getResources().getString(R.string.hey_buddy_I_am_here), InboxMessageTypes.SMILE_BACK, PendingIntent.getBroadcast(context, this.f4830a, intent, 201326592), Integer.MAX_VALUE);
    }

    public void notifyRemoteCameraOpenRequest(Context context, int i) {
        Intent intent = new Intent(context, CameraActivity.class);
        intent.addFlags(268435456);
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.remote_camera_open_alert), context.getResources().getString(R.string.remote_camera_open), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
    }

    public void notifySingleBestActivity(Context context, int i, ActivityShareData activityShareData, String str) {
        Intent intent = new Intent(context, ActivityModeShareScreen.class);
        intent.putExtra("share_data", activityShareData);
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
    }

    public void notifySleepInsight(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.putExtra(AppConstants.FCM_NOTIFICATION_TYPE.name(), HealthNavigationElement.SLEEP.name());
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
        }
    }

    public void notifySleepScore(Context context, int i, String str) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.putExtra(AppConstants.FCM_NOTIFICATION_TYPE.name(), HealthNavigationElement.SLEEP.name());
            f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.performance_alert), str, InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
        }
    }

    public void notifySpo2MeasureReminder(Context context, int i) {
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.measure_spo2_title), context.getResources().getString(R.string.measure_spo2_msg), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, new Intent(context, ActivitySp02FromRawPPG.class), 201326592), i);
    }

    public void notifySportSelection(Context context, FcmSportsSelectMatchMessage fcmSportsSelectMatchMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, SportsNotificationActivity.class);
            intent.putExtra("screen_type", fcmSportsSelectMatchMessage.getSport().toLowerCase(Locale.ROOT));
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            if (Build.VERSION.SDK_INT >= 31) {
                e(context, R.drawable.ic_stat_notification_icon_cove, fcmSportsSelectMatchMessage.getTitle(), fcmSportsSelectMatchMessage.getMessage(), InboxMessageTypes.WORKOUT_VIDEOS_UPDATE, PendingIntent.getActivity(context, 0, intent, 201326592));
                return;
            }
            e(context, R.drawable.ic_stat_notification_icon_cove, fcmSportsSelectMatchMessage.getTitle(), fcmSportsSelectMatchMessage.getMessage(), InboxMessageTypes.WORKOUT_VIDEOS_UPDATE, PendingIntent.getActivity(context, 0, intent, 134217728));
        }
    }

    public void notifySportSettings(Context context, FcmSportsSettingsMessage fcmSportsSettingsMessage) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, SportsNotificationActivity.class);
            if (SportsPreference.Companion.isDisclaimerAccepted(context)) {
                intent.putExtra("screen_type", "settings");
                intent.setFlags(PKIFailureInfo.duplicateCertReq);
            } else {
                intent = new Intent(context, SportsDisclaimerActivity.class);
                intent.putExtra(SportsDisclaimerActivity.IS_FROM, SportsDisclaimerActivity.FRAGMENTHOMEDASHBOARD);
            }
            if (Build.VERSION.SDK_INT >= 31) {
                e(context, R.drawable.ic_stat_notification_icon_cove, fcmSportsSettingsMessage.getTitle(), fcmSportsSettingsMessage.getMessage(), InboxMessageTypes.WORKOUT_VIDEOS_UPDATE, PendingIntent.getActivity(context, 0, intent, 201326592));
                return;
            }
            e(context, R.drawable.ic_stat_notification_icon_cove, fcmSportsSettingsMessage.getTitle(), fcmSportsSettingsMessage.getMessage(), InboxMessageTypes.WORKOUT_VIDEOS_UPDATE, PendingIntent.getActivity(context, 0, intent, 134217728));
        }
    }

    public void notifyStartNavigationRequest(Context context, int i, NotifyNavigationEventRes notifyNavigationEventRes) {
        Intent intent = new Intent(context, ActivityDirections.class);
        intent.putExtra("isFromBand", true);
        intent.putExtra("placeIdOnBand", notifyNavigationEventRes.getPlaceId());
        intent.putExtra("modeOnBand", notifyNavigationEventRes.getMode());
        intent.setFlags(268468224);
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.navigation_alert), context.getResources().getString(R.string.tap_here_to_open_navigation), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
    }

    public void notifyUseWFStudioMessage(Context context, String str, String str2, String str3, String str4) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            String watchFaceDiyUrl = SessionManager.getInstance(context).getWatchFaceDiyUrl();
            Intent intent = new Intent(context, ActivityInAppWebViewerWatchface.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), watchFaceDiyUrl);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_DASHBOARD.getValue(), false);
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.setFlags(67108864);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str4)) {
                Glide.with(context).asBitmap().m21load(str4).into((RequestBuilder<Bitmap>) new d(context, str2, str3, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str2, str3, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void notifyWFNewListUpdatedMessage(Context context, String str, String str2, String str3, String str4) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityWatchFace.class);
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.setFlags(67108864);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str4)) {
                Glide.with(context).asBitmap().m21load(str4).into((RequestBuilder<Bitmap>) new c(context, str2, str3, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str2, str3, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void notifyWFUpdateOnWatchMessage(Context context, String str, String str2, String str3, String str4) {
        if (SessionManager.getInstance(context).isDevicePaired()) {
            Intent intent = new Intent(context, ActivityWatchFace.class);
            intent.putExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue(), str);
            intent.putExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), true);
            intent.setFlags(67108864);
            intent.setFlags(PKIFailureInfo.duplicateCertReq);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            if (!AppUtils.isEmpty(str4)) {
                Glide.with(context).asBitmap().m21load(str4).into((RequestBuilder<Bitmap>) new b(context, str2, str3, activity));
            } else {
                e(context, R.drawable.ic_stat_notification_icon_cove, str2, str3, InboxMessageTypes.REL_GUARDIAN_UNAVAILABLE_MESSAGE, activity);
            }
        }
    }

    public void showNotificationAccessSettingScreenAlert(Context context, int i) {
        Intent intent = new Intent();
        intent.setAction("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        f(context, R.drawable.ic_stat_notification_icon_cove, context.getResources().getString(R.string.music_control_alert), context.getResources().getString(R.string.tap_to_enabled_notification_access), InboxMessageTypes.SMILE_BACK, PendingIntent.getActivity(context, 0, intent, 201326592), i);
    }
}
