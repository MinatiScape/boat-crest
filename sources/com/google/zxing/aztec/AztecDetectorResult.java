package com.google.zxing.aztec;

import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
/* loaded from: classes11.dex */
public final class AztecDetectorResult extends DetectorResult {
    public final boolean c;
    public final int d;
    public final int e;

    public AztecDetectorResult(BitMatrix bitMatrix, ResultPoint[] resultPointArr, boolean z, int i, int i2) {
        super(bitMatrix, resultPointArr);
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public int getNbDatablocks() {
        return this.d;
    }

    public int getNbLayers() {
        return this.e;
    }

    public boolean isCompact() {
        return this.c;
    }
}
