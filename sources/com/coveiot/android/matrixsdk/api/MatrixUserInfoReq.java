package com.coveiot.android.matrixsdk.api;
/* loaded from: classes5.dex */
public final class MatrixUserInfoReq extends MatrixBaseReq {
    public final boolean e;
    public final int f;
    public final float g;
    public final float h;

    public MatrixUserInfoReq(boolean z, int i, float f, float f2) {
        this.e = z;
        this.f = i;
        this.g = f;
        this.h = f2;
    }

    public final int getAge() {
        return this.f;
    }

    public final float getHeight() {
        return this.g;
    }

    public final boolean getSex() {
        return this.e;
    }

    public final float getWeight() {
        return this.h;
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
