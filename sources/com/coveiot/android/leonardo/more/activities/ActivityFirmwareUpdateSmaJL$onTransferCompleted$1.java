package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateSmaJL$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateSmaJL h;

    public ActivityFirmwareUpdateSmaJL$onTransferCompleted$1(ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL) {
        this.h = activityFirmwareUpdateSmaJL;
    }

    public static final void b(ActivityFirmwareUpdateSmaJL this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.M();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.z;
            final ActivityFirmwareUpdateSmaJL activityFirmwareUpdateSmaJL = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.nd
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateSmaJL$onTransferCompleted$1.b(ActivityFirmwareUpdateSmaJL.this);
                }
            }, 3000L);
        }
    }
}
