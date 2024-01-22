package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.HealthyConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixHealthReminderReq extends MatrixBaseReq {
    @NotNull
    public final HealthyConfig e;

    public MatrixHealthReminderReq(@NotNull HealthyConfig healthyConfig) {
        Intrinsics.checkNotNullParameter(healthyConfig, "healthyConfig");
        this.e = healthyConfig;
    }

    @NotNull
    public final HealthyConfig getHealthyConfig() {
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
