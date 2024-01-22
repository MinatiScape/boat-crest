package com.coveiot.android.leonardo.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.support.v4.media.MediaMetadataCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataPlaybackVolumeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.model.DeviceReconnected;
import com.coveiot.android.leonardo.model.MusicControlEvent;
import com.coveiot.android.leonardo.model.SocialNotificationModel;
import com.coveiot.android.leonardo.model.VolumeControlEvent;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SocialNotificationListenerService extends NotificationListenerService implements MediaSessionManager.OnActiveSessionsChangedListener {
    @NotNull
    public String h;
    @Nullable
    public MediaController i;
    @Nullable
    public MediaSessionManager j;
    public boolean k;
    @Nullable
    public LinkedList<SocialNotificationModel> l;
    @Nullable
    public SettingsContentObserver m;
    public int n;
    @Nullable
    public AudioManager o;
    public int p;
    public int q;
    @NotNull
    public final MediaController.Callback r;

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MusicControlEvent.Control.values().length];
            try {
                iArr[MusicControlEvent.Control.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MusicControlEvent.Control.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MusicControlEvent.Control.NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MusicControlEvent.Control.PREVIOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SocialNotificationListenerService() {
        String simpleName = SocialNotificationListenerService.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SocialNotificationListen…ce::class.java.simpleName");
        this.h = simpleName;
        new Handler();
        this.n = 3;
        this.r = new MediaController.Callback() { // from class: com.coveiot.android.leonardo.service.SocialNotificationListenerService$mMediaCallback$1
            @Override // android.media.session.MediaController.Callback
            public void onAudioInfoChanged(@Nullable MediaController.PlaybackInfo playbackInfo) {
                super.onAudioInfoChanged(playbackInfo);
            }

            @Override // android.media.session.MediaController.Callback
            public void onExtrasChanged(@Nullable Bundle bundle) {
                super.onExtrasChanged(bundle);
            }

            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(@Nullable MediaMetadata mediaMetadata) {
                DeviceSupportedFeatures deviceSupportedFeatures;
                super.onMetadataChanged(mediaMetadata);
                if (mediaMetadata != null) {
                    LogHelper.d("onMetadataChanged Title", " --- " + mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
                    LogHelper.d("onMetadataChanged Artist", "--- " + mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
                    LogHelper.d("onMetadataChanged Album", " --- " + mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
                    Intent intent = new Intent(AppConstants.MUSIC_METADATA_BROADCAST_INTENT.getValue());
                    intent.putExtra(AppConstants.MUSIC_METADATA_KEY_TITLE.getValue(), mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
                    intent.putExtra(AppConstants.MUSIC_METADATA_KEY_ARTIST.getValue(), mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
                    intent.putExtra(AppConstants.MUSIC_METADATA_KEY_ALBUM.getValue(), mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
                    LocalBroadcastManager.getInstance(SocialNotificationListenerService.this.getApplicationContext()).sendBroadcast(intent);
                    BleApi bleApi = BleApiManager.getInstance(SocialNotificationListenerService.this).getBleApi();
                    boolean z = true;
                    if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isMusicDataSupportInSingleCommand()) {
                        z = false;
                    }
                    if (z) {
                        SocialNotificationListenerService.this.sendMusicMetaDataVolumeToWatch();
                    }
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(@Nullable PlaybackState playbackState) {
                DeviceSupportedFeatures deviceSupportedFeatures;
                if (playbackState != null) {
                    SocialNotificationListenerService.this.setMPlaybackState(playbackState.getState());
                    boolean z = true;
                    if (playbackState.getState() == 3 || playbackState.getState() == 2 || playbackState.getState() == 1 || playbackState.getState() == 0) {
                        Intent intent = new Intent(AppConstants.MUSIC_PLAYBACK_STATE_CHANGE_BROADCAST_INTENT.getValue());
                        intent.putExtra(AppConstants.MUSIC_APP_PLAYBACK_STATE_CHANGED.getValue(), playbackState.getState() == 3 ? 1 : 0);
                        LocalBroadcastManager.getInstance(SocialNotificationListenerService.this.getApplicationContext()).sendBroadcast(intent);
                        BleApi bleApi = BleApiManager.getInstance(SocialNotificationListenerService.this).getBleApi();
                        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isMusicDataSupportInSingleCommand()) {
                            z = false;
                        }
                        if (z) {
                            SocialNotificationListenerService.this.sendMusicMetaDataVolumeToWatch();
                        }
                    }
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueChanged(@Nullable List<MediaSession.QueueItem> list) {
                super.onQueueChanged(list);
            }

            @Override // android.media.session.MediaController.Callback
            public void onQueueTitleChanged(@Nullable CharSequence charSequence) {
                super.onQueueTitleChanged(charSequence);
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                super.onSessionDestroyed();
                if (SocialNotificationListenerService.this.getMPlaybackState() != 3) {
                    SettingsSyncHelper.Companion.getInstance(SocialNotificationListenerService.this).sendMusicNotPlayingMsg();
                }
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionEvent(@NotNull String event, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(event, "event");
                super.onSessionEvent(event, bundle);
            }
        };
    }

    public final void a(String str, String str2, String str3, Long l) {
        LinkedList<SocialNotificationModel> linkedList = this.l;
        Intrinsics.checkNotNull(linkedList);
        if (linkedList.size() >= 30) {
            LinkedList<SocialNotificationModel> linkedList2 = this.l;
            Intrinsics.checkNotNull(linkedList2);
            linkedList2.removeFirst();
        }
        SocialNotificationModel socialNotificationModel = new SocialNotificationModel(l, str3, str2, str);
        LinkedList<SocialNotificationModel> linkedList3 = this.l;
        Intrinsics.checkNotNull(linkedList3);
        linkedList3.add(socialNotificationModel);
    }

    public final int getMPlaybackState() {
        return this.n;
    }

    @Nullable
    public final SettingsContentObserver getSettingsContentObserver() {
        return this.m;
    }

    public final boolean isCall() {
        return this.k;
    }

    public final boolean isSameNotificationKey(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l) {
        LinkedList<SocialNotificationModel> linkedList = this.l;
        if (linkedList != null) {
            Intrinsics.checkNotNull(linkedList);
            if (linkedList.size() > 0) {
                LinkedList<SocialNotificationModel> linkedList2 = this.l;
                Intrinsics.checkNotNull(linkedList2);
                List<SocialNotificationModel> reversed = CollectionsKt___CollectionsKt.reversed(linkedList2);
                Intrinsics.checkNotNull(reversed);
                for (SocialNotificationModel socialNotificationModel : reversed) {
                    if (m.equals$default(socialNotificationModel.getContent(), str, false, 2, null) && m.equals$default(socialNotificationModel.getTitle(), str2, false, 2, null) && m.equals$default(socialNotificationModel.getPackageName(), str3, false, 2, null)) {
                        Intrinsics.checkNotNull(l);
                        long longValue = l.longValue();
                        Long notificationWhenTime = socialNotificationModel.getNotificationWhenTime();
                        Intrinsics.checkNotNull(notificationWhenTime);
                        return longValue - notificationWhenTime.longValue() <= 1000;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
    public void onActiveSessionsChanged(@Nullable List<MediaController> list) {
        if (list != null && list.size() > 0) {
            Iterator<MediaController> it = list.iterator();
            if (it.hasNext()) {
                MediaController next = it.next();
                MediaController mediaController = this.i;
                if (mediaController != null) {
                    Intrinsics.checkNotNull(mediaController);
                    mediaController.unregisterCallback(this.r);
                    LogHelper.d(this.h, "MediaController removed");
                    this.i = null;
                }
                if (this.i == null) {
                    this.i = next;
                    Intrinsics.checkNotNull(next);
                    next.registerCallback(this.r);
                    this.r.onMetadataChanged(next.getMetadata());
                    MediaController mediaController2 = this.i;
                    Intrinsics.checkNotNull(mediaController2);
                    if (mediaController2.getPlaybackState() != null) {
                        MediaController.Callback callback = this.r;
                        MediaController mediaController3 = this.i;
                        Intrinsics.checkNotNull(mediaController3);
                        callback.onPlaybackStateChanged(mediaController3.getPlaybackState());
                    }
                    String str = this.h;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MediaController set: ");
                    MediaController mediaController4 = this.i;
                    Intrinsics.checkNotNull(mediaController4);
                    sb.append(mediaController4.getPackageName());
                    LogHelper.d(str, sb.toString());
                    return;
                }
                return;
            }
            return;
        }
        String str2 = this.h;
        LogHelper.d(str2, "onActiveSessionsChanged else -- " + new byte[]{0});
        SettingsSyncHelper.Companion.getInstance(this).sendMusicNotPlayingMsg();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogHelper.d(this.h, "onCreate");
        if (this.l == null) {
            LogHelper.d(this.h, "notifiationsLinkedList==null");
            this.l = new LinkedList<>();
        }
        try {
            if (this.j == null) {
                Object systemService = getSystemService("media_session");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.session.MediaSessionManager");
                MediaSessionManager mediaSessionManager = (MediaSessionManager) systemService;
                this.j = mediaSessionManager;
                Intrinsics.checkNotNull(mediaSessionManager);
                List<MediaController> activeSessions = mediaSessionManager.getActiveSessions(new ComponentName(this, SocialNotificationListenerService.class));
                Intrinsics.checkNotNullExpressionValue(activeSessions, "mMediaSessionManager!!.g…      )\n                )");
                onActiveSessionsChanged(activeSessions);
                MediaSessionManager mediaSessionManager2 = this.j;
                if (mediaSessionManager2 != null) {
                    mediaSessionManager2.removeOnActiveSessionsChangedListener(this);
                }
                MediaSessionManager mediaSessionManager3 = this.j;
                Intrinsics.checkNotNull(mediaSessionManager3);
                mediaSessionManager3.addOnActiveSessionsChangedListener(this, new ComponentName(this, SocialNotificationListenerService.class));
            }
        } catch (SecurityException unused) {
            LogHelper.d(this.h, "No Notification Access");
        }
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(this.h, "onDestroy called");
        MediaSessionManager mediaSessionManager = this.j;
        if (mediaSessionManager != null) {
            mediaSessionManager.removeOnActiveSessionsChangedListener(this);
        }
        try {
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public final void onDeviceConnected(@NotNull DeviceReconnected onDeviceConnected) {
        Intrinsics.checkNotNullParameter(onDeviceConnected, "onDeviceConnected");
        try {
            MediaSessionManager mediaSessionManager = this.j;
            if (mediaSessionManager != null) {
                Intrinsics.checkNotNull(mediaSessionManager);
                List<MediaController> activeSessions = mediaSessionManager.getActiveSessions(new ComponentName(this, SocialNotificationListenerService.class));
                Intrinsics.checkNotNullExpressionValue(activeSessions, "mMediaSessionManager!!.g…      )\n                )");
                if (!AppUtils.isEmpty(activeSessions)) {
                    onActiveSessionsChanged(activeSessions);
                } else {
                    SettingsSyncHelper.Companion.getInstance(this).sendMusicNotPlayingMsg();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public final void onMusicControlEventReceived(@NotNull MusicControlEvent musicControlEvent) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        Intrinsics.checkNotNullParameter(musicControlEvent, "musicControlEvent");
        MediaSessionManager mediaSessionManager = this.j;
        Intrinsics.checkNotNull(mediaSessionManager);
        List<MediaController> activeSessions = mediaSessionManager.getActiveSessions(new ComponentName(this, SocialNotificationListenerService.class));
        Intrinsics.checkNotNullExpressionValue(activeSessions, "mMediaSessionManager!!.g…a\n            )\n        )");
        if (AppUtils.isEmpty(activeSessions)) {
            LogHelper.d(this.h, "music control event received " + musicControlEvent.getControl().name() + " but controllers are empty");
            return;
        }
        LogHelper.d(this.h, "music control event received " + musicControlEvent.getControl().name() + " but controllers are alive");
        MusicControlEvent.Control control = musicControlEvent.getControl();
        int i = control == null ? -1 : WhenMappings.$EnumSwitchMapping$0[control.ordinal()];
        boolean z = true;
        if (i == 1) {
            PlaybackState playbackState = activeSessions.get(0).getPlaybackState();
            Long valueOf = playbackState != null ? Long.valueOf(playbackState.getPosition()) : null;
            activeSessions.get(0).getTransportControls().play();
            MediaController.TransportControls transportControls = activeSessions.get(0).getTransportControls();
            Intrinsics.checkNotNull(valueOf);
            transportControls.seekTo(valueOf.longValue());
        } else if (i == 2) {
            activeSessions.get(0).getTransportControls().pause();
        } else if (i == 3) {
            activeSessions.get(0).getTransportControls().skipToNext();
        } else if (i == 4) {
            activeSessions.get(0).getTransportControls().skipToPrevious();
        }
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        if (bleApi == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isMusicDataSupportInSingleCommand()) {
            z = false;
        }
        if (z) {
            sendMusicMetaDataVolumeToWatch();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:615:0x0eda, code lost:
        if (kotlin.text.StringsKt__StringsKt.contains$default((java.lang.CharSequence) r2, (java.lang.CharSequence) r13, false, 2, (java.lang.Object) null) != false) goto L317;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0d03  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x0e93  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0f64  */
    /* JADX WARN: Removed duplicated region for block: B:629:0x0f6d  */
    /* JADX WARN: Removed duplicated region for block: B:632:0x0f85  */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v21, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v9, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v0, types: [T, java.lang.String] */
    @Override // android.service.notification.NotificationListenerService
    @android.annotation.SuppressLint({"StringFormatInvalid"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onNotificationPosted(@org.jetbrains.annotations.NotNull android.service.notification.StatusBarNotification r27) {
        /*
            Method dump skipped, instructions count: 4140
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.service.SocialNotificationListenerService.onNotificationPosted(android.service.notification.StatusBarNotification):void");
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        LogHelper.d(this.h, "onStartCommand");
        if (this.l == null) {
            LogHelper.d(this.h, "notifiationsLinkedList==null");
            this.l = new LinkedList<>();
        }
        try {
            if (this.j == null) {
                Object systemService = getSystemService("media_session");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.session.MediaSessionManager");
                MediaSessionManager mediaSessionManager = (MediaSessionManager) systemService;
                this.j = mediaSessionManager;
                Intrinsics.checkNotNull(mediaSessionManager);
                List<MediaController> activeSessions = mediaSessionManager.getActiveSessions(new ComponentName(this, SocialNotificationListenerService.class));
                Intrinsics.checkNotNullExpressionValue(activeSessions, "mMediaSessionManager!!.g…      )\n                )");
                onActiveSessionsChanged(activeSessions);
                MediaSessionManager mediaSessionManager2 = this.j;
                if (mediaSessionManager2 != null) {
                    mediaSessionManager2.removeOnActiveSessionsChangedListener(this);
                }
                MediaSessionManager mediaSessionManager3 = this.j;
                Intrinsics.checkNotNull(mediaSessionManager3);
                mediaSessionManager3.addOnActiveSessionsChangedListener(this, new ComponentName(this, SocialNotificationListenerService.class));
            }
        } catch (SecurityException unused) {
            LogHelper.d(this.h, "No Notification Access");
        }
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
            return 3;
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }

    @Subscribe
    public final void onVolumeControlEventReceived(@NotNull VolumeControlEvent volumeControlEvent) {
        Intrinsics.checkNotNullParameter(volumeControlEvent, "volumeControlEvent");
        sendMusicMetaDataVolumeToWatch();
    }

    public final void sendMusicMetaDataVolumeToWatch() {
        MusicControlState musicControlState;
        PlaybackState playbackState;
        PlaybackState playbackState2;
        MediaMetadata metadata;
        DeviceSupportedFeatures deviceSupportedFeatures;
        if (this.o == null) {
            Object systemService = getSystemService("audio");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            this.o = (AudioManager) systemService;
        }
        AudioManager audioManager = this.o;
        Intrinsics.checkNotNull(audioManager);
        this.p = audioManager.getStreamMaxVolume(3);
        AudioManager audioManager2 = this.o;
        Intrinsics.checkNotNull(audioManager2);
        this.q = audioManager2.getStreamVolume(3);
        BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
        String str = null;
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            BleApi bleApi2 = BleApiManager.getInstance(this).getBleApi();
            boolean z = true;
            if ((bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isMusicDataSupportInSingleCommand()) ? false : true) {
                SetMusicMetaDataPlaybackVolumeRequest.Builder maxVolume = new SetMusicMetaDataPlaybackVolumeRequest.Builder().setVolume(this.q).setMaxVolume(this.p);
                MediaController mediaController = this.i;
                if (mediaController != null && (metadata = mediaController.getMetadata()) != null) {
                    str = metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE);
                }
                SetMusicMetaDataPlaybackVolumeRequest.Builder title = maxVolume.setTitle(str);
                MediaController mediaController2 = this.i;
                if (!((mediaController2 == null || (playbackState2 = mediaController2.getPlaybackState()) == null || playbackState2.getState() != 3) ? false : true)) {
                    MediaController mediaController3 = this.i;
                    if (mediaController3 == null || (playbackState = mediaController3.getPlaybackState()) == null || playbackState.getState() != 6) {
                        z = false;
                    }
                    if (!z) {
                        musicControlState = MusicControlState.PAUSE;
                        SetMusicMetaDataPlaybackVolumeRequest build = title.setMusicControlState(musicControlState).build();
                        Context baseContext = getBaseContext();
                        Intrinsics.checkNotNull(baseContext);
                        BleApiManager.getInstance(baseContext).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.service.SocialNotificationListenerService$sendMusicMetaDataVolumeToWatch$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                String str2;
                                Intrinsics.checkNotNullParameter(response, "response");
                                str2 = SocialNotificationListenerService.this.h;
                                LogHelper.i(str2, "sendMusicMetaDataVolumeToWatch success");
                            }
                        });
                    }
                }
                musicControlState = MusicControlState.PLAY;
                SetMusicMetaDataPlaybackVolumeRequest build2 = title.setMusicControlState(musicControlState).build();
                Context baseContext2 = getBaseContext();
                Intrinsics.checkNotNull(baseContext2);
                BleApiManager.getInstance(baseContext2).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.service.SocialNotificationListenerService$sendMusicMetaDataVolumeToWatch$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        String str2;
                        Intrinsics.checkNotNullParameter(response, "response");
                        str2 = SocialNotificationListenerService.this.h;
                        LogHelper.i(str2, "sendMusicMetaDataVolumeToWatch success");
                    }
                });
            }
        }
    }

    public final void setCall(boolean z) {
        this.k = z;
    }

    public final void setMPlaybackState(int i) {
        this.n = i;
    }

    public final void setSettingsContentObserver(@Nullable SettingsContentObserver settingsContentObserver) {
        this.m = settingsContentObserver;
    }
}
