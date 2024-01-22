package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaOtaReq extends SmaBaseReq {
    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return true;
    }
}
