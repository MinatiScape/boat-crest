package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;
/* loaded from: classes11.dex */
public final class QRCodeDecoderMetaData {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11870a;

    public QRCodeDecoderMetaData(boolean z) {
        this.f11870a = z;
    }

    public void applyMirroredCorrection(ResultPoint[] resultPointArr) {
        if (!this.f11870a || resultPointArr == null || resultPointArr.length < 3) {
            return;
        }
        ResultPoint resultPoint = resultPointArr[0];
        resultPointArr[0] = resultPointArr[2];
        resultPointArr[2] = resultPoint;
    }

    public boolean isMirrored() {
        return this.f11870a;
    }
}
