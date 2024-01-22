package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
/* loaded from: classes11.dex */
public abstract class Binarizer {

    /* renamed from: a  reason: collision with root package name */
    public final LuminanceSource f11761a;

    public Binarizer(LuminanceSource luminanceSource) {
        this.f11761a = luminanceSource;
    }

    public abstract Binarizer createBinarizer(LuminanceSource luminanceSource);

    public abstract BitMatrix getBlackMatrix() throws NotFoundException;

    public abstract BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException;

    public final int getHeight() {
        return this.f11761a.getHeight();
    }

    public final LuminanceSource getLuminanceSource() {
        return this.f11761a;
    }

    public final int getWidth() {
        return this.f11761a.getWidth();
    }
}
