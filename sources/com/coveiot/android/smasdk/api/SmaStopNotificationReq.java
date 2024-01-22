package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleNotification;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SmaStopNotificationReq extends SmaBaseReq {
    @NotNull
    public BleNotification d;

    public SmaStopNotificationReq(@NotNull BleNotification bleNotification) {
        Intrinsics.checkNotNullParameter(bleNotification, "bleNotification");
        this.d = bleNotification;
    }

    @NotNull
    public final BleNotification getBleNotification() {
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

    public final void setBleNotification(@NotNull BleNotification bleNotification) {
        Intrinsics.checkNotNullParameter(bleNotification, "<set-?>");
        this.d = bleNotification;
    }
}
