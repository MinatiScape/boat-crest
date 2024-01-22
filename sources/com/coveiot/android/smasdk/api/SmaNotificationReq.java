package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleNotification;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaNotificationReq extends SmaBaseReq {
    @Nullable
    public BleNotification d;

    @Nullable
    public final BleNotification getBleNotification() {
        return this.d;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setBleNotification(@Nullable BleNotification bleNotification) {
        this.d = bleNotification;
    }
}
