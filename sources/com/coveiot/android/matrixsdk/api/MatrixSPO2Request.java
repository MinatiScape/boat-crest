package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixSPO2Request extends MatrixBaseReq {
    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return false;
    }
}
