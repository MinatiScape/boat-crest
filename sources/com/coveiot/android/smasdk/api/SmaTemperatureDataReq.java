package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaTemperatureDataReq extends SmaBaseReq {
    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.smasdk.api.SmaBaseReq
    public boolean isPriority() {
        return false;
    }
}
