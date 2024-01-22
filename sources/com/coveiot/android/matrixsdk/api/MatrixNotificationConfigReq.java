package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.NotificationConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixNotificationConfigReq extends MatrixBaseReq {
    @NotNull
    public final NotificationConfig e;

    public MatrixNotificationConfigReq(@NotNull NotificationConfig notificationConfig) {
        Intrinsics.checkNotNullParameter(notificationConfig, "notificationConfig");
        this.e = notificationConfig;
    }

    @NotNull
    public final NotificationConfig getNotificationConfig() {
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
