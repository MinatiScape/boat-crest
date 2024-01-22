package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleSedentarinessSettings;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetSedentaryReminderReq extends SmaBaseReq {
    @Nullable
    public BleSedentarinessSettings d;

    @Nullable
    public final BleSedentarinessSettings getSedentarinessSettings() {
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

    public final void setSedentarinessSettings(@Nullable BleSedentarinessSettings bleSedentarinessSettings) {
        this.d = bleSedentarinessSettings;
    }
}
