package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.config.SedentaryConfig;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixSedentaryReminderReq extends MatrixBaseReq {
    @NotNull
    public final SedentaryConfig e;

    public MatrixSedentaryReminderReq(@NotNull SedentaryConfig sedentaryConfig) {
        Intrinsics.checkNotNullParameter(sedentaryConfig, "sedentaryConfig");
        this.e = sedentaryConfig;
    }

    @NotNull
    public final SedentaryConfig getSedentaryConfig() {
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
