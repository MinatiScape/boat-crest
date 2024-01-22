package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixDIYComponentReq extends MatrixBaseReq {
    public final int e;

    public MatrixDIYComponentReq(int i) {
        this.e = i;
    }

    public final int getPosition() {
        return this.e;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }
}
