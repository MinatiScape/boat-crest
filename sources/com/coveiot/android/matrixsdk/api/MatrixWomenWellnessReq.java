package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixWomenWellnessReq extends MatrixBaseReq {
    @NotNull
    public final WomenHealthyConfig e;

    public MatrixWomenWellnessReq(@NotNull WomenHealthyConfig womenHealthyConfig) {
        Intrinsics.checkNotNullParameter(womenHealthyConfig, "womenHealthyConfig");
        this.e = womenHealthyConfig;
    }

    @NotNull
    public final WomenHealthyConfig getWomenHealthyConfig() {
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
