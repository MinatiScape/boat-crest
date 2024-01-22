package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
/* loaded from: classes12.dex */
public class NonMemoableDigest implements ExtendedDigest {

    /* renamed from: a  reason: collision with root package name */
    public ExtendedDigest f14642a;

    public NonMemoableDigest(ExtendedDigest extendedDigest) {
        if (extendedDigest == null) {
            throw new IllegalArgumentException("baseDigest must not be null");
        }
        this.f14642a = extendedDigest;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        return this.f14642a.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return this.f14642a.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return this.f14642a.getByteLength();
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.f14642a.getDigestSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.f14642a.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.f14642a.update(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.f14642a.update(bArr, i, i2);
    }
}
