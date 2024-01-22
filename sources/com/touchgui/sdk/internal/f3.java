package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class f3 {

    /* renamed from: a  reason: collision with root package name */
    public final int f13761a;
    public int b;
    public int c;
    public int d;
    public final int e;
    public byte[] f;

    public f3(int i, int i2) {
        this.f13761a = i;
        this.e = i2;
        a(i2);
    }

    public final void a(int i) {
        this.d = i & 31;
        this.b = (2096128 & i) >> 10;
        this.c = (i & (-2097152)) >> 21;
    }

    public final String toString() {
        return "ImageRes{id=" + this.f13761a + ", width=" + this.b + ", height=" + this.c + ", format=" + this.d + ", head=" + this.e + '}';
    }
}
