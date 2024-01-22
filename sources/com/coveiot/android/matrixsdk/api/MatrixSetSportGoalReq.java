package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixSetSportGoalReq extends MatrixBaseReq {
    public final int e;
    public final int f;
    public final int g;

    public MatrixSetSportGoalReq(int i, int i2, int i3) {
        this.e = i;
        this.f = i2;
        this.g = i3;
    }

    public final int getCalorie() {
        return this.g;
    }

    public final int getDistance() {
        return this.f;
    }

    public final int getStep() {
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
