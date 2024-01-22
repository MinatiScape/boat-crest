package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixWristWearReq extends MatrixBaseReq {
    public final boolean e;

    public MatrixWristWearReq(boolean z) {
        this.e = z;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.android.matrixsdk.api.MatrixBaseReq
    public boolean isPriority() {
        return true;
    }

    public final boolean isRightHand() {
        return this.e;
    }
}
