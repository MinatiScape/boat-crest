package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixDNDReq extends MatrixBaseReq {
    @NotNull
    public final NotDisturbConfig e;

    public MatrixDNDReq(@NotNull NotDisturbConfig dndConfig) {
        Intrinsics.checkNotNullParameter(dndConfig, "dndConfig");
        this.e = dndConfig;
    }

    @NotNull
    public final NotDisturbConfig getDndConfig() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return false;
    }
}
