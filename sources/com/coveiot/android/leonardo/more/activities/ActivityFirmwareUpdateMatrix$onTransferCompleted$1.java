package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateMatrix$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateMatrix h;

    public ActivityFirmwareUpdateMatrix$onTransferCompleted$1(ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix) {
        this.h = activityFirmwareUpdateMatrix;
    }

    public static final void b(ActivityFirmwareUpdateMatrix this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus != ConnectionStatus.CONNECTED || this.h.isConnectedEventReceivedAfterReboot()) {
            return;
        }
        this.h.setConnectedEventReceivedAfterReboot(true);
        handler = this.h.u;
        final ActivityFirmwareUpdateMatrix activityFirmwareUpdateMatrix = this.h;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.lc
            @Override // java.lang.Runnable
            public final void run() {
                ActivityFirmwareUpdateMatrix$onTransferCompleted$1.b(ActivityFirmwareUpdateMatrix.this);
            }
        }, 3000L);
    }
}
