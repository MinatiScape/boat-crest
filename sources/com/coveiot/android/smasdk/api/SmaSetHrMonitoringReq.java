package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleHrMonitoringSettings;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetHrMonitoringReq extends SmaBaseReq {
    @Nullable
    public BleHrMonitoringSettings d;

    @Nullable
    public final BleHrMonitoringSettings getHrMonitoringSettins() {
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

    public final void setHrMonitoringSettins(@Nullable BleHrMonitoringSettings bleHrMonitoringSettings) {
        this.d = bleHrMonitoringSettings;
    }
}
