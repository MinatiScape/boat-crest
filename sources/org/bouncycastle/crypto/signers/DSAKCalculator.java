package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
/* loaded from: classes13.dex */
public interface DSAKCalculator {
    void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr);

    void init(BigInteger bigInteger, SecureRandom secureRandom);

    boolean isDeterministic();

    BigInteger nextK();
}
