package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
/* loaded from: classes12.dex */
public class ShortenedDigest implements ExtendedDigest {

    /* renamed from: a  reason: collision with root package name */
    public ExtendedDigest f14644a;
    public int b;

    public ShortenedDigest(ExtendedDigest extendedDigest, int i) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        if (i > extendedDigest.getDigestSize()) {
            throw new IllegalArgumentException("baseDigest output not large enough to support length");
        }
        this.f14644a = extendedDigest;
        this.b = i;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.f14644a.getDigestSize()];
        this.f14644a.doFinal(bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, i, this.b);
        return this.b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.f14644a.getAlgorithmName() + "(" + (this.b * 8) + ")";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.f14644a.getByteLength();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.b;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.f14644a.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.f14644a.update(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.f14644a.update(bArr, i, i2);
    }
}
