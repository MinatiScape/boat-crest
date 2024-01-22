package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
/* loaded from: classes13.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public byte[][] f15344a;

    public g(e eVar, byte[][] bArr) {
        Objects.requireNonNull(eVar, "params == null");
        Objects.requireNonNull(bArr, "signature == null");
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("signature byte array == null");
        }
        if (bArr.length != eVar.c()) {
            throw new IllegalArgumentException("wrong signature size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != eVar.b()) {
                throw new IllegalArgumentException("wrong signature format");
            }
        }
        this.f15344a = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] a() {
        return XMSSUtil.cloneArray(this.f15344a);
    }
}
