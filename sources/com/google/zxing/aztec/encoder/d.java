package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
/* loaded from: classes11.dex */
public abstract class d {
    public static final d b = new b(null, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    public final d f11777a;

    public d(d dVar) {
        this.f11777a = dVar;
    }

    public final d a(int i, int i2) {
        return new b(this, i, i2);
    }

    public final d b(int i, int i2) {
        return new a(this, i, i2);
    }

    public abstract void c(BitArray bitArray, byte[] bArr);

    public final d d() {
        return this.f11777a;
    }
}
