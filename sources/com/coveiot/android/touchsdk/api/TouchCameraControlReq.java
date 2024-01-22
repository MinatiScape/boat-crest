package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchCameraControlReq extends TouchELXBaseReq {
    public boolean e;

    public TouchCameraControlReq(boolean z) {
        this.e = z;
    }

    public final boolean isEnabled() {
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

    public final void setEnabled(boolean z) {
        this.e = z;
    }
}
