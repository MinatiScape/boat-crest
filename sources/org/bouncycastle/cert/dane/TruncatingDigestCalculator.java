package org.bouncycastle.cert.dane;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes12.dex */
public class TruncatingDigestCalculator implements DigestCalculator {

    /* renamed from: a  reason: collision with root package name */
    public final DigestCalculator f14482a;
    public final int b;

    public TruncatingDigestCalculator(DigestCalculator digestCalculator) {
        this(digestCalculator, 28);
    }

    public TruncatingDigestCalculator(DigestCalculator digestCalculator, int i) {
        this.f14482a = digestCalculator;
        this.b = i;
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.f14482a.getAlgorithmIdentifier();
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public byte[] getDigest() {
        int i = this.b;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f14482a.getDigest(), 0, bArr, 0, i);
        return bArr;
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public OutputStream getOutputStream() {
        return this.f14482a.getOutputStream();
    }
}
