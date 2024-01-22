package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixGetAlarmListData extends MatrixBaseReq {
    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
