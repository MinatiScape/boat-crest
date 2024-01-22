package org.bouncycastle.pkcs.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
/* loaded from: classes13.dex */
public class BcPKCS12MacCalculatorBuilder implements PKCS12MacCalculatorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ExtendedDigest f15263a;
    public AlgorithmIdentifier b;
    public SecureRandom c;
    public int d;
    public int e;

    public BcPKCS12MacCalculatorBuilder() {
        this(new SHA1Digest(), new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE));
    }

    public BcPKCS12MacCalculatorBuilder(ExtendedDigest extendedDigest, AlgorithmIdentifier algorithmIdentifier) {
        this.e = 1024;
        this.f15263a = extendedDigest;
        this.b = algorithmIdentifier;
        this.d = extendedDigest.getDigestSize();
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
    public MacCalculator build(char[] cArr) {
        if (this.c == null) {
            this.c = new SecureRandom();
        }
        byte[] bArr = new byte[this.d];
        this.c.nextBytes(bArr);
        return a.b(this.b.getAlgorithm(), this.f15263a, new PKCS12PBEParams(bArr, this.e), cArr);
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.b;
    }

    public BcPKCS12MacCalculatorBuilder setIterationCount(int i) {
        this.e = i;
        return this;
    }
}
