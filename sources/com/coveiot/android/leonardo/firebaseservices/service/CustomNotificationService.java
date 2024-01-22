package com.coveiot.android.leonardo.firebaseservices.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.activities.FitnessBuddiesDashBoardActivity;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.firebaseservices.model.CustomNotifiacationModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.leaderboard.views.activities.LeaderBoardActivity;
import com.coveiot.utils.utility.GlideUtils;
import com.google.gson.Gson;
/* loaded from: classes2.dex */
public class CustomNotificationService extends Service {
    public String h = AppConstants.CHANNEL_ID.getValue();

    /* loaded from: classes2.dex */
    public class a extends SimpleTarget<Bitmap> {
        public final /* synthetic */ Intent k;
        public final /* synthetic */ CustomNotifiacationModel l;
        public final /* synthetic */ String m;

        public a(Intent intent, CustomNotifiacationModel customNotifiacationModel, String str) {
            this.k = intent;
            this.l = customNotifiacationModel;
            this.m = str;
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
            CustomNotificationService.this.stopSelf();
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
        }

        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
            CustomNotificationService.this.c(this.k, bitmap, this.l, this.m);
        }
    }

    public final void b(Intent intent) {
        String string;
        CustomNotifiacationModel customNotifiacationModel;
        Bundle extras = intent.getExtras();
        if (extras == null || (string = extras.getString(getResources().getString(R.string.data))) == null || string.isEmpty() || (customNotifiacationModel = (CustomNotifiacationModel) new Gson().fromJson(string, (Class<Object>) CustomNotifiacationModel.class)) == null) {
            return;
        }
        if (!TextUtils.isEmpty(customNotifiacationModel.getMediaUrl())) {
            GlideUtils.loadImage(this, customNotifiacationModel.getMediaUrl(), new a(intent, customNotifiacationModel, string));
        } else {
            c(intent, null, customNotifiacationModel, string);
        }
    }

    public final void c(@NonNull Intent intent, @Nullable Bitmap bitmap, CustomNotifiacationModel customNotifiacationModel, String str) {
        Intent intent2;
        if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.minus_one))) {
            intent2 = new Intent(this, ActivityDashboardNew.class);
            intent2.putExtras(intent);
            intent2.putExtra(AppConstants.IS_CUSTOMNOTIFICATION.getValue(), str);
        } else if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.zero))) {
            intent2 = new Intent(this, ActivityDashboardNew.class);
            intent2.putExtras(intent);
            intent2.putExtra(AppConstants.IS_CUSTOMNOTIFICATION.getValue(), str);
        } else if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.one))) {
            intent2 = new Intent();
            intent2.putExtras(intent);
            intent2.putExtra(AppConstants.IS_CUSTOMNOTIFICATION.getValue(), str);
        } else if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.two))) {
            intent2 = new Intent();
            intent2.putExtras(intent);
            intent2.putExtra(AppConstants.IS_CUSTOMNOTIFICATION.getValue(), str);
        } else if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.three))) {
            intent2 = new Intent(this, LeaderBoardActivity.class);
        } else if (customNotifiacationModel.getTargetScreen().equalsIgnoreCase(getResources().getString(R.string.four))) {
            PreferenceManager.Companion.saveFitnessNotificationCount(this, 0);
            intent2 = new Intent(this, FitnessBuddiesDashBoardActivity.class);
        } else {
            intent2 = new Intent(this, ActivityDashboardNew.class);
            intent2.putExtras(intent);
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent2, 201326592);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, this.h);
        builder.setSmallIcon(R.drawable.ic_stat_notification_icon_cove);
        if (customNotifiacationModel.getTitle() != null) {
            builder.setContentTitle(customNotifiacationModel.getTitle());
        }
        if (customNotifiacationModel.getMessage() != null) {
            builder.setContentText(customNotifiacationModel.getMessage());
        }
        builder.setAutoCancel(true);
        builder.setDefaults(3);
        builder.setContentIntent(activity);
        if (bitmap != null) {
            if (customNotifiacationModel.getMessage() != null) {
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(customNotifiacationModel.getMessage()));
            } else {
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
            }
        } else if (customNotifiacationModel.getMessage() != null) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(customNotifiacationModel.getMessage()));
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(this.h, getResources().getString(R.string.notification_name), 3));
        }
        notificationManager.notify(0, builder.build());
        stopSelf();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        b(intent);
        return 2;
    }
}
