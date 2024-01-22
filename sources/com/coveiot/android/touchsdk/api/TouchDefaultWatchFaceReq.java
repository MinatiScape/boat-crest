package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchDefaultWatchFaceReq extends TouchELXBaseReq {
    public int e;

    public TouchDefaultWatchFaceReq(int i) {
        this.e = i;
    }

    public final int getWatchfacePosition() {
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

    public final void setWatchfacePosition(int i) {
        this.e = i;
    }
}
