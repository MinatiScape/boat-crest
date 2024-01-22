package com.coveiot.android.matrixsdk.api;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class MatrixSetQRCodeReq extends MatrixBaseReq {
    public final int e;
    @NotNull
    public final String f;

    public MatrixSetQRCodeReq(int i, @NotNull String qrCodeData) {
        Intrinsics.checkNotNullParameter(qrCodeData, "qrCodeData");
        this.e = i;
        this.f = qrCodeData;
    }

    public final int getAppType() {
        return this.e;
    }

    @NotNull
    public final String getQrCodeData() {
        return this.f;
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
