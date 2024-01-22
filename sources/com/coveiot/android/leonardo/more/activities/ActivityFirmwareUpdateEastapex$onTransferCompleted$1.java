package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateEastapex$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateEastapex h;

    public ActivityFirmwareUpdateEastapex$onTransferCompleted$1(ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex) {
        this.h = activityFirmwareUpdateEastapex;
    }

    public static final void b(ActivityFirmwareUpdateEastapex this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.J();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.u;
            final ActivityFirmwareUpdateEastapex activityFirmwareUpdateEastapex = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.la
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateEastapex$onTransferCompleted$1.b(ActivityFirmwareUpdateEastapex.this);
                }
            }, 3000L);
        }
    }
}
