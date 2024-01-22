package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetMusicVolumeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.leonardo.model.VolumeControlEvent;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class VolumeControlListener extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    public int f5439a;
    @NotNull
    public final AudioManager b;
    @Nullable
    public Context c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumeControlListener(@NotNull Context context, @Nullable Handler handler) {
        super(handler);
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("audio");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.b = (AudioManager) systemService;
        this.c = context;
    }

    public final void a(int i, int i2) {
        if (BleApiManager.getInstance(this.c).getBleApi() == null || BleApiManager.getInstance(this.c).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        SetMusicVolumeRequest musicVolumeRequest = new SetMusicVolumeRequest.Builder().setVolume(i).setMaxVolume(i2).build();
        BleApi bleApi = BleApiManager.getInstance(this.c).getBleApi();
        Intrinsics.checkNotNullExpressionValue(musicVolumeRequest, "musicVolumeRequest");
        bleApi.setUserSettings(musicVolumeRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.utils.VolumeControlListener$sendVolumeControlRequest$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.i("VolumeControlListener", "onSettingsError");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.i("VolumeControlListener", "onSettingsResponse");
            }
        });
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return false;
    }

    @Nullable
    public final Context getMContext() {
        return this.c;
    }

    public final int getPreviousVolume() {
        return this.f5439a;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        int streamVolume = this.b.getStreamVolume(3);
        int streamMaxVolume = this.b.getStreamMaxVolume(3);
        if (BleApiManager.getInstance(this.c).getBleApi() == null || !BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isBandVolumeControlSupported() || this.f5439a - streamVolume == 0) {
            return;
        }
        this.f5439a = streamVolume;
        CoveEventBusManager.getInstance().getEventBus().post(new VolumeControlEvent(streamMaxVolume, streamVolume));
        a(streamVolume, streamMaxVolume);
        SettingsSyncHelper.Companion companion = SettingsSyncHelper.Companion;
        Context context = this.c;
        Intrinsics.checkNotNull(context);
        companion.getInstance(context).setCurrentVolumeToWatch();
    }

    public final void setMContext(@Nullable Context context) {
        this.c = context;
    }

    public final void setPreviousVolume(int i) {
        this.f5439a = i;
    }
}
