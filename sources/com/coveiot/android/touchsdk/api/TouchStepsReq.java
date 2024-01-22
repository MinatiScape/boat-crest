package com.coveiot.android.touchsdk.api;
/* loaded from: classes7.dex */
public final class TouchStepsReq extends TouchELXBaseReq {
    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.touchsdk.api.TouchELXBaseReq
    public boolean isPriority() {
        return false;
    }
}
