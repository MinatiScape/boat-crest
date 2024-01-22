package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
/* loaded from: classes13.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final byte[][] f15343a;

    public f(e eVar, byte[][] bArr) {
        Objects.requireNonNull(eVar, "params == null");
        Objects.requireNonNull(bArr, "publicKey == null");
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("publicKey byte array == null");
        }
        if (bArr.length != eVar.c()) {
            throw new IllegalArgumentException("wrong publicKey size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != eVar.b()) {
                throw new IllegalArgumentException("wrong publicKey format");
            }
        }
        this.f15343a = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] a() {
        return XMSSUtil.cloneArray(this.f15343a);
    }
}
