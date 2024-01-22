package com.coveiot.android.smasdk.api;

import com.szabh.smable3.entity.BleWorldClock;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SmaSetWorldClockReq extends SmaBaseReq {
    @Nullable
    public List<BleWorldClock> d;

    @Nullable
    public final List<BleWorldClock> getBleWorldClocks() {
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

    public final void setBleWorldClocks(@Nullable List<BleWorldClock> list) {
        this.d = list;
    }
}
