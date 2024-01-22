package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchDeviceInfoReq extends TouchELXBaseReq {
    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return true;
    }
}
