package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixHourFormatReq extends MatrixBaseReq {
    public final boolean e;

    public MatrixHourFormatReq(boolean z) {
        this.e = z;
    }

    public final boolean is12HourFormat() {
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
