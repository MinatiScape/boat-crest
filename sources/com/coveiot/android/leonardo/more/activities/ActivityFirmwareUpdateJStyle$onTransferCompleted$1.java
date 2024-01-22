package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import androidx.lifecycle.Observer;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateJStyle$onTransferCompleted$1 implements Observer<ConnectionStatus> {
    public final /* synthetic */ ActivityFirmwareUpdateJStyle h;

    public ActivityFirmwareUpdateJStyle$onTransferCompleted$1(ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle) {
        this.h = activityFirmwareUpdateJStyle;
    }

    public static final void b(ActivityFirmwareUpdateJStyle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        Handler handler;
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            handler = this.h.v;
            final ActivityFirmwareUpdateJStyle activityFirmwareUpdateJStyle = this.h;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.bb
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityFirmwareUpdateJStyle$onTransferCompleted$1.b(ActivityFirmwareUpdateJStyle.this);
                }
            }, 3000L);
        }
    }
}
