package com.coveiot.android.matrixsdk.api;

import com.htsmart.wristband2.bean.WristbandAlarm;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixVibrationAlarmReq extends MatrixBaseReq {
    @NotNull
    public final List<WristbandAlarm> e;

    /* JADX WARN: Multi-variable type inference failed */
    public MatrixVibrationAlarmReq(@NotNull List<? extends WristbandAlarm> clockList) {
        Intrinsics.checkNotNullParameter(clockList, "clockList");
        this.e = clockList;
    }

    @NotNull
    public final List<WristbandAlarm> getClockList() {
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
