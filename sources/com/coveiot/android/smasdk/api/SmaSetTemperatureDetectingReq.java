package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleTemperatureDetecting;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetTemperatureDetectingReq extends SmaBaseReq {
    @Nullable
    public BleTemperatureDetecting d;

    @Nullable
    public final BleTemperatureDetecting getBleTemperatureDetectingReq() {
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

    public final void setBleTemperatureDetectingReq(@Nullable BleTemperatureDetecting bleTemperatureDetecting) {
        this.d = bleTemperatureDetecting;
    }
}
