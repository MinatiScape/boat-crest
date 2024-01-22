package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$onDownloadFinish$1 implements SettingsResultListener {
    public final /* synthetic */ ActivityFirmwareUpdateJStyle h;

    public ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$onDownloadFinish$1(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle) {
        this.h = activityFirmwareUpdateJStyle;
    }

    public static final void b(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startScan(true);
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.h;
        String string = activityFirmwareUpdateJStyle.getString(R.string.ota_mode_failed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ota_mode_failed)");
        activityFirmwareUpdateJStyle.B(string, "", false);
    }

    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
    public void onSettingsResponse(@NotNull BleBaseResponse response) {
        Handler handler;
        Intrinsics.checkNotNullParameter(response, "response");
        handler = this.h.v;
        final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.h;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.xa
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateJStyle$beginFirmwareDownload$1$1$onDownloadFinish$1.b(ActivityFirmwareUpdateJStyle.this);
            }
        }, 3000L);
    }
}
