package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateKaHaRealTek$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateKaHaRealTek h;

    public ActivityFirmwareUpdateKaHaRealTek$onTransferCompleted$1(ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek) {
        this.h = activityFirmwareUpdateKaHaRealTek;
    }

    public static final void b(ActivityFirmwareUpdateKaHaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.L();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.w;
            final ActivityFirmwareUpdateKaHaRealTek activityFirmwareUpdateKaHaRealTek = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.xb
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateKaHaRealTek$onTransferCompleted$1.b(ActivityFirmwareUpdateKaHaRealTek.this);
                }
            }, 3000L);
        }
    }
}
