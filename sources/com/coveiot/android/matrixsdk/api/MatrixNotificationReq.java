package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.WristbandNotification;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixNotificationReq extends MatrixBaseReq {
    @NotNull
    public final WristbandNotification e;

    public MatrixNotificationReq(@NotNull WristbandNotification wristbandNotification) {
        Intrinsics.checkNotNullParameter(wristbandNotification, "wristbandNotification");
        this.e = wristbandNotification;
    }

    @NotNull
    public final WristbandNotification getWristbandNotification() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
