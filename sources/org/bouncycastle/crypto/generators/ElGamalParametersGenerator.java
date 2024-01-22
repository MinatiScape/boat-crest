package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.ElGamalParameters;
/* loaded from: classes12.dex */
public class ElGamalParametersGenerator {

    /* renamed from: a  reason: collision with root package name */
    public int f14729a;
    public int b;
    public SecureRandom c;

    public ElGamalParameters generateParameters() {
        BigInteger[] a2 = b.a(this.f14729a, this.b, this.c);
        BigInteger bigInteger = a2[0];
        return new ElGamalParameters(bigInteger, b.b(bigInteger, a2[1], this.c));
    }

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.f14729a = i;
        this.b = i2;
        this.c = secureRandom;
    }
}
