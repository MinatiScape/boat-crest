package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateTouch$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateTouch h;

    public ActivityFirmwareUpdateTouch$onTransferCompleted$1(ActivityFirmwareUpdateTouch activityFirmwareUpdateTouch) {
        this.h = activityFirmwareUpdateTouch;
    }

    public static final void b(ActivityFirmwareUpdateTouch this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.H();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.u;
            final ActivityFirmwareUpdateTouch activityFirmwareUpdateTouch = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ie
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateTouch$onTransferCompleted$1.b(ActivityFirmwareUpdateTouch.this);
                }
            }, 3000L);
        }
    }
}
