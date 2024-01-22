package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleNoDisturbSettings;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetDNDReq extends SmaBaseReq {
    @Nullable
    public BleNoDisturbSettings d;

    @Nullable
    public final BleNoDisturbSettings getBleDndReq() {
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

    public final void setBleDndReq(@Nullable BleNoDisturbSettings bleNoDisturbSettings) {
        this.d = bleNoDisturbSettings;
    }
}
