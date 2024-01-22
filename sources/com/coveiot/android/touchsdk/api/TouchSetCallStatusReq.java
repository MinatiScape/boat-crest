package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchSetCallStatusReq extends TouchELXBaseReq {
    public int e;

    public TouchSetCallStatusReq(int i) {
        this.e = i;
    }

    public final int getStatus() {
        return this.e;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setStatus(int i) {
        this.e = i;
    }
}
