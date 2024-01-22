package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;
/* loaded from: classes11.dex */
public final class FinderPattern {

    /* renamed from: a  reason: collision with root package name */
    public final int f11834a;
    public final int[] b;
    public final ResultPoint[] c;

    public FinderPattern(int i, int[] iArr, int i2, int i3, int i4) {
        this.f11834a = i;
        this.b = iArr;
        float f = i4;
        this.c = new ResultPoint[]{new ResultPoint(i2, f), new ResultPoint(i3, f)};
    }

    public boolean equals(Object obj) {
        return (obj instanceof FinderPattern) && this.f11834a == ((FinderPattern) obj).f11834a;
    }

    public ResultPoint[] getResultPoints() {
        return this.c;
    }

    public int[] getStartEnd() {
        return this.b;
    }

    public int getValue() {
        return this.f11834a;
    }

    public int hashCode() {
        return this.f11834a;
    }
}
