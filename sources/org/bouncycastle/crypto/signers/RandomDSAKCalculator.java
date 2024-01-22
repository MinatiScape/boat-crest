package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class RandomDSAKCalculator implements DSAKCalculator {
    public static final BigInteger c = BigInteger.valueOf(0);

    /* renamed from: a  reason: collision with root package name */
    public BigInteger f14843a;
    public SecureRandom b;

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        this.f14843a = bigInteger;
        this.b = secureRandom;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public boolean isDeterministic() {
        return false;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public BigInteger nextK() {
        int bitLength = this.f14843a.bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.b);
            if (!bigInteger.equals(c) && bigInteger.compareTo(this.f14843a) < 0) {
                return bigInteger;
            }
        }
    }
}
