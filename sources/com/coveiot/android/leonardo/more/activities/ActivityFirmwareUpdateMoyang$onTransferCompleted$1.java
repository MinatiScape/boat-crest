package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateMoyang$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateMoyang h;

    public ActivityFirmwareUpdateMoyang$onTransferCompleted$1(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang) {
        this.h = activityFirmwareUpdateMoyang;
    }

    public static final void b(ActivityFirmwareUpdateMoyang this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.v;
            final ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.vc
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateMoyang$onTransferCompleted$1.b(ActivityFirmwareUpdateMoyang.this);
                }
            }, 3000L);
        }
    }
}
