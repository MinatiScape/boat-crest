package com.google.zxing.pdf417.encoder;
/* loaded from: classes11.dex */
public final class Dimensions {

    /* renamed from: a  reason: collision with root package name */
    public final int f11862a;
    public final int b;
    public final int c;
    public final int d;

    public Dimensions(int i, int i2, int i3, int i4) {
        this.f11862a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getMaxCols() {
        return this.b;
    }

    public int getMaxRows() {
        return this.d;
    }

    public int getMinCols() {
        return this.f11862a;
    }

    public int getMinRows() {
        return this.c;
    }
}
