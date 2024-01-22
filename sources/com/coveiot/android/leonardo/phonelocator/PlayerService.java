package com.coveiot.android.leonardo.phonelocator;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.media.VolumeProviderCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.utils.utility.UtilConstants;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
/* loaded from: classes5.dex */
public class PlayerService extends Service {
    public MediaSessionCompat h;
    public BroadcastReceiver i;
    public VolumeButtonHelper j;

    /* loaded from: classes5.dex */
    public class ScreenReceiver extends BroadcastReceiver {
        public ScreenReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            PhoneLocator.getInstance(PlayerService.this).stopPlayingAudio();
        }
    }

    /* loaded from: classes5.dex */
    public class a extends MediaSessionCompat.Callback {
        public a(PlayerService playerService) {
        }
    }

    /* loaded from: classes5.dex */
    public class b implements VolumeButtonHelper.VolumeChangeListener {
        public b() {
        }

        @Override // com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper.VolumeChangeListener
        public void onDoublePress() {
        }

        @Override // com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper.VolumeChangeListener
        public void onLongPress() {
        }

        @Override // com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper.VolumeChangeListener
        public void onSinglePress() {
        }

        @Override // com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper.VolumeChangeListener
        public void onVolumeChange(@NonNull VolumeButtonHelper.Direction direction) {
            PhoneLocator.getInstance(PlayerService.this).stopPlayingAudio();
            PlayerService.this.stopSelf();
        }

        @Override // com.coveiot.android.leonardo.phonelocator.VolumeButtonHelper.VolumeChangeListener
        public void onVolumePress(int i) {
        }
    }

    /* loaded from: classes5.dex */
    public class c extends VolumeProviderCompat {
        public c(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        @Override // androidx.media.VolumeProviderCompat
        public void onAdjustVolume(int i) {
            if (i == -1) {
                PhoneLocator.getInstance(PlayerService.this).stopPlayingAudio();
            } else if (i == 1) {
                PhoneLocator.getInstance(PlayerService.this).stopPlayingAudio();
            }
            PlayerService.this.stopSelf();
        }
    }

    public static PendingIntent getNotificationActivityLaunchIntent(Context context) {
        Intent intent = new Intent(AppConstants.INTENT_FILTER_STRIDE_SPLASH.getValue());
        intent.setFlags(PKIFailureInfo.duplicateCertReq);
        return PendingIntent.getActivity(context, 0, intent, 67108864);
    }

    public static void showForegroundNotification(Service service, String str, int i, int i2, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) service.getSystemService("notification")).createNotificationChannel(new NotificationChannel(UtilConstants.ID_NOTIFICATION_CHANNEL, service.getString(R.string.titan_notification_fg_msg), 2));
            service.startForeground(1, new Notification.Builder(service, UtilConstants.ID_NOTIFICATION_CHANNEL).setContentTitle(str).setSmallIcon(i).setColor(ContextCompat.getColor(service, i2)).setContentIntent(pendingIntent).setContentText(service.getString(R.string.hey_buddy_I_am_here)).setAutoCancel(true).build());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        showForegroundNotification(this, getString(R.string.phone_found), R.drawable.ic_stat_notification_icon_cove, R.color.colorPrimary, getNotificationActivityLaunchIntent(this));
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        ScreenReceiver screenReceiver = new ScreenReceiver();
        this.i = screenReceiver;
        registerReceiver(screenReceiver, intentFilter);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "PlayerService");
        this.h = mediaSessionCompat;
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            mediaSessionCompat.setCallback(new a(this));
        }
        this.h.setFlags(3);
        this.h.setPlaybackState(new PlaybackStateCompat.Builder().setState(3, 0L, 0.0f).build());
        if (i >= 31) {
            VolumeButtonHelper volumeButtonHelper = new VolumeButtonHelper(getApplicationContext(), 2);
            this.j = volumeButtonHelper;
            volumeButtonHelper.registerVolumeChangeListener(new b());
        } else {
            this.h.setPlaybackToRemote(new c(1, 100, 50));
        }
        this.h.setActive(true);
    }

    @Override // android.app.Service
    @RequiresApi(api = 28)
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.i);
        this.h.release();
        if (Build.VERSION.SDK_INT >= 31) {
            stopForeground(1);
            this.j.unregisterReceiver();
            this.j.finalizeMediaPlayer();
        }
    }
}
