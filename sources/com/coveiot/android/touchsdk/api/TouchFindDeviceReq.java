package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchFindDeviceReq extends TouchELXBaseReq {
    public boolean e;
    public int f;

    public TouchFindDeviceReq(boolean z, int i) {
        this.e = z;
        this.f = i;
    }

    public final boolean getEnable() {
        return this.e;
    }

    public final int getTimeout() {
        return this.f;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }

    public final void setEnable(boolean z) {
        this.e = z;
    }

    public final void setTimeout(int i) {
        this.f = i;
    }
}
