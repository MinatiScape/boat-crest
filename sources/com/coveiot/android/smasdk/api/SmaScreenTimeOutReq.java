package com.coveiot.android.smasdk.api;
/* loaded from: classes6.dex */
public final class SmaScreenTimeOutReq extends SmaBaseReq {
    public int d = 10;

    public final int getScreenTimeOut() {
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

    public final void setScreenTimeOut(int i) {
        this.d = i;
    }
}
