package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public final class XMSSMTParameters {

    /* renamed from: a  reason: collision with root package name */
    public final XMSSParameters f15329a;
    public final int b;
    public final int c;

    public XMSSMTParameters(int i, int i2, Digest digest) {
        this.b = i;
        this.c = i2;
        this.f15329a = new XMSSParameters(a(i, i2), digest);
        DefaultXMSSMTOid.lookup(getDigest().getAlgorithmName(), getDigestSize(), getWinternitzParameter(), getLen(), getHeight(), i2);
    }

    public static int a(int i, int i2) throws IllegalArgumentException {
        if (i >= 2) {
            if (i % i2 == 0) {
                int i3 = i / i2;
                if (i3 != 1) {
                    return i3;
                }
                throw new IllegalArgumentException("height / layers must be greater than 1");
            }
            throw new IllegalArgumentException("layers must divide totalHeight without remainder");
        }
        throw new IllegalArgumentException("totalHeight must be > 1");
    }

    public Digest getDigest() {
        return this.f15329a.getDigest();
    }

    public int getDigestSize() {
        return this.f15329a.getDigestSize();
    }

    public int getHeight() {
        return this.b;
    }

    public int getLayers() {
        return this.c;
    }

    public int getLen() {
        return this.f15329a.c().e().c();
    }

    public c getWOTSPlus() {
        return this.f15329a.c();
    }

    public int getWinternitzParameter() {
        return this.f15329a.getWinternitzParameter();
    }

    public XMSSParameters getXMSSParameters() {
        return this.f15329a;
    }
}
