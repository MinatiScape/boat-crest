package com.google.zxing.common;

import com.google.zxing.ResultPoint;
/* loaded from: classes11.dex */
public class DetectorResult {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11788a;
    public final ResultPoint[] b;

    public DetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr) {
        this.f11788a = bitMatrix;
        this.b = resultPointArr;
    }

    public final BitMatrix getBits() {
        return this.f11788a;
    }

    public final ResultPoint[] getPoints() {
        return this.b;
    }
}
