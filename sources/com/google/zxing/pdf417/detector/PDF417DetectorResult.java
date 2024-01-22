package com.google.zxing.pdf417.detector;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.List;
/* loaded from: classes11.dex */
public final class PDF417DetectorResult {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11860a;
    public final List<ResultPoint[]> b;

    public PDF417DetectorResult(BitMatrix bitMatrix, List<ResultPoint[]> list) {
        this.f11860a = bitMatrix;
        this.b = list;
    }

    public BitMatrix getBits() {
        return this.f11860a;
    }

    public List<ResultPoint[]> getPoints() {
        return this.b;
    }
}
