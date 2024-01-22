package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaDeleteScheduleReq extends SmaBaseReq {
    public int d;

    public SmaDeleteScheduleReq(int i) {
        this.d = i;
    }

    public final int getScheduleId() {
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

    public final void setScheduleId(int i) {
        this.d = i;
    }
}
