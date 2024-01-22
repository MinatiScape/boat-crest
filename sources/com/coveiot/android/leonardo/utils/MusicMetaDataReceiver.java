package com.coveiot.android.leonardo.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataRequest;
import com.coveiot.android.bleabstract.request.SetMusicPlaybackStateChangedRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class MusicMetaDataReceiver extends BroadcastReceiver {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f5429a = "MusicMetaDataReceiver";
    @Nullable
    public String b = "";
    @Nullable
    public String c = "";
    @Nullable
    public String d = "";
    public int e = -1;
    public int f = -1;

    @Nullable
    public final String getArtist() {
        return this.b;
    }

    public final int getCurrentPlaybackState() {
        return this.e;
    }

    @Nullable
    public final String getCurrentTitle() {
        return this.d;
    }

    public final int getPlaybackState() {
        return this.f;
    }

    @NotNull
    public final String getTAG() {
        return this.f5429a;
    }

    @Nullable
    public final String getTitle() {
        return this.c;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        SetMusicMetaDataRequest setSongTrackReq;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual(intent.getAction(), AppConstants.MUSIC_METADATA_BROADCAST_INTENT.getValue())) {
            if (BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isMusicMetaDataChangeFromAppSupported()) {
                this.c = intent.getStringExtra(AppConstants.MUSIC_METADATA_KEY_TITLE.getValue());
                this.b = intent.getStringExtra(AppConstants.MUSIC_METADATA_KEY_ARTIST.getValue());
                String str = this.c;
                if (str != null) {
                    Intrinsics.checkNotNull(str);
                    if (!(str.length() > 0) || Intrinsics.areEqual(this.c, this.d)) {
                        return;
                    }
                    this.d = this.c;
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    if (!companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                        setSongTrackReq = new SetMusicMetaDataRequest.Builder().setTitle(this.d).build();
                    } else {
                        setSongTrackReq = new SetMusicMetaDataRequest.Builder().setTitle(this.d).setArtist(this.b).build();
                    }
                    if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
                        Intrinsics.checkNotNullExpressionValue(setSongTrackReq, "setSongTrackReq");
                        bleApi.setUserSettings(setSongTrackReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.MusicMetaDataReceiver$onReceive$1
                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                LogHelper.d(MusicMetaDataReceiver.this.getTAG(), String.valueOf(error.getErrorMsg()));
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                Intrinsics.checkNotNullParameter(response, "response");
                                LogHelper.d(MusicMetaDataReceiver.this.getTAG(), "SetMusicMetaDataRequest Sent successfully.");
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            String str2 = this.c;
            if (str2 != null || Intrinsics.areEqual(str2, this.d)) {
                return;
            }
            SettingsSyncHelper.Companion.getInstance(context).sendMusicNotPlayingMsg();
        } else if (Intrinsics.areEqual(intent.getAction(), AppConstants.MUSIC_PLAYBACK_STATE_CHANGE_BROADCAST_INTENT.getValue()) && BleApiManager.getInstance(context).getBleApi() != null && BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isMusicPlaybackStateChangeFromAppSupported()) {
            int intExtra = intent.getIntExtra(AppConstants.MUSIC_APP_PLAYBACK_STATE_CHANGED.getValue(), -1);
            this.f = intExtra;
            if (intExtra == -1 || intExtra == this.e) {
                return;
            }
            this.e = intExtra;
            SetMusicPlaybackStateChangedRequest setPlaybackState = new SetMusicPlaybackStateChangedRequest.Builder().setMusicControlState(intExtra == 1 ? MusicControlState.PLAY : MusicControlState.PAUSE).build();
            if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
                Intrinsics.checkNotNullExpressionValue(setPlaybackState, "setPlaybackState");
                bleApi2.setUserSettings(setPlaybackState, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.MusicMetaDataReceiver$onReceive$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        LogHelper.d(MusicMetaDataReceiver.this.getTAG(), String.valueOf(error.getErrorMsg()));
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        LogHelper.d(MusicMetaDataReceiver.this.getTAG(), "SetMusicPlaybackStateChangedRequest Sent successfully.");
                    }
                });
            }
        }
    }

    public final void setArtist(@Nullable String str) {
        this.b = str;
    }

    public final void setCurrentPlaybackState(int i) {
        this.e = i;
    }

    public final void setCurrentTitle(@Nullable String str) {
        this.d = str;
    }

    public final void setPlaybackState(int i) {
        this.f = i;
    }

    public final void setTitle(@Nullable String str) {
        this.c = str;
    }
}
