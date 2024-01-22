package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdate$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdate h;

    public ActivityFirmwareUpdate$onTransferCompleted$1(ActivityFirmwareUpdate activityFirmwareUpdate) {
        this.h = activityFirmwareUpdate;
    }

    public static final void b(ActivityFirmwareUpdate this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.N();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.z;
            final ActivityFirmwareUpdate activityFirmwareUpdate = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.ca
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdate$onTransferCompleted$1.b(ActivityFirmwareUpdate.this);
                }
            }, 3000L);
        }
    }
}
