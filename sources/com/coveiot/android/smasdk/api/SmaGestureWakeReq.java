package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleGestureWake;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaGestureWakeReq extends SmaBaseReq {
    @Nullable
    public BleGestureWake d;

    @Nullable
    public final BleGestureWake getGestureWake() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setGestureWake(@Nullable BleGestureWake bleGestureWake) {
        this.d = bleGestureWake;
    }
}
