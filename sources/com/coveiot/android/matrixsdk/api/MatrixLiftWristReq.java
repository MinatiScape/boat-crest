package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixLiftWristReq extends MatrixBaseReq {
    @NotNull
    public final TurnWristLightingConfig e;

    public MatrixLiftWristReq(@NotNull TurnWristLightingConfig wristLightConfig) {
        Intrinsics.checkNotNullParameter(wristLightConfig, "wristLightConfig");
        this.e = wristLightConfig;
    }

    @NotNull
    public final TurnWristLightingConfig getWristLightConfig() {
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
