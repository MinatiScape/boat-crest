package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleAlarm;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetVibrationAlarmReq extends SmaBaseReq {
    @Nullable
    public BleAlarm d;

    @Nullable
    public final BleAlarm getBleAlarm() {
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

    public final void setBleAlarm(@Nullable BleAlarm bleAlarm) {
        this.d = bleAlarm;
    }
}
