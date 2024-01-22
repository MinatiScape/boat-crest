package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHValidationParameters;
/* loaded from: classes12.dex */
public class DHParametersGenerator {
    public static final BigInteger d = BigInteger.valueOf(2);

    /* renamed from: a  reason: collision with root package name */
    public int f14724a;
    public int b;
    public SecureRandom c;

    public DHParameters generateParameters() {
        BigInteger[] a2 = b.a(this.f14724a, this.b, this.c);
        BigInteger bigInteger = a2[0];
        BigInteger bigInteger2 = a2[1];
        return new DHParameters(bigInteger, b.b(bigInteger, bigInteger2, this.c), bigInteger2, d, (DHValidationParameters) null);
    }

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.f14724a = i;
        this.b = i2;
        this.c = secureRandom;
    }
}
