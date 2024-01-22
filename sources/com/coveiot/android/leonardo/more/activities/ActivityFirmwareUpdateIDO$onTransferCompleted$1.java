package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateIDO$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateIDO h;

    public ActivityFirmwareUpdateIDO$onTransferCompleted$1(ActivityFirmwareUpdateIDO activityFirmwareUpdateIDO) {
        this.h = activityFirmwareUpdateIDO;
    }

    public static final void b(ActivityFirmwareUpdateIDO this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.v;
            final ActivityFirmwareUpdateIDO activityFirmwareUpdateIDO = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ta
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateIDO$onTransferCompleted$1.b(ActivityFirmwareUpdateIDO.this);
                }
            }, 3000L);
        }
    }
}
