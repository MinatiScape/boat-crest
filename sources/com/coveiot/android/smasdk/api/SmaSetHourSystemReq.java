package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaSetHourSystemReq extends SmaBaseReq {
    public boolean d = true;

    public final boolean is12HourFormat() {
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

    public final void set12HourFormat(boolean z) {
        this.d = z;
    }
}
