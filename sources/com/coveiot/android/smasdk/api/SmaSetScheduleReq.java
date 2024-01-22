package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleSchedule;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaSetScheduleReq extends SmaBaseReq {
    @NotNull
    public BleSchedule d;

    public SmaSetScheduleReq(@NotNull BleSchedule bleSchedule) {
        Intrinsics.checkNotNullParameter(bleSchedule, "bleSchedule");
        this.d = bleSchedule;
    }

    @NotNull
    public final BleSchedule getBleSchedule() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }

    public final void setBleSchedule(@NotNull BleSchedule bleSchedule) {
        Intrinsics.checkNotNullParameter(bleSchedule, "<set-?>");
        this.d = bleSchedule;
    }
}
