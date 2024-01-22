package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaRealTek$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateSmaRealTek h;

    public ActivityFirmwareUpdateSmaRealTek$onTransferCompleted$1(ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek) {
        this.h = activityFirmwareUpdateSmaRealTek;
    }

    public static final void b(ActivityFirmwareUpdateSmaRealTek this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.L();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.w;
            final ActivityFirmwareUpdateSmaRealTek activityFirmwareUpdateSmaRealTek = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ae
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateSmaRealTek$onTransferCompleted$1.b(ActivityFirmwareUpdateSmaRealTek.this);
                }
            }, 3000L);
        }
    }
}
