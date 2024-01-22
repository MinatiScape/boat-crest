package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;
/* loaded from: classes11.dex */
public final class FinderPattern extends ResultPoint {
    public final float c;
    public final int d;

    public FinderPattern(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    public boolean b(float f, float f2, float f3) {
        if (Math.abs(f2 - getY()) > f || Math.abs(f3 - getX()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.c);
        return abs <= 1.0f || abs <= this.c;
    }

    public FinderPattern c(float f, float f2, float f3) {
        int i = this.d;
        int i2 = i + 1;
        float x = (i * getX()) + f2;
        float f4 = i2;
        return new FinderPattern(x / f4, ((this.d * getY()) + f) / f4, ((this.d * this.c) + f3) / f4, i2);
    }

    public int d() {
        return this.d;
    }

    public float getEstimatedModuleSize() {
        return this.c;
    }

    public FinderPattern(float f, float f2, float f3, int i) {
        super(f, f2);
        this.c = f3;
        this.d = i;
    }
}
