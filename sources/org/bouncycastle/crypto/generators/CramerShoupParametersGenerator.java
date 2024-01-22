package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.CramerShoupParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class CramerShoupParametersGenerator {
    public static final BigInteger d = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public int f14720a;
    public int b;
    public SecureRandom c;

    /* loaded from: classes12.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final BigInteger f14721a = BigInteger.valueOf(2);

        public static BigInteger[] a(int i, int i2, SecureRandom secureRandom) {
            BigInteger bigInteger;
            BigInteger add;
            int i3 = i - 1;
            while (true) {
                bigInteger = new BigInteger(i3, 2, secureRandom);
                add = bigInteger.shiftLeft(1).add(CramerShoupParametersGenerator.d);
                if (!add.isProbablePrime(i2) || (i2 > 2 && !bigInteger.isProbablePrime(i2))) {
                }
            }
            return new BigInteger[]{add, bigInteger};
        }

        public static BigInteger b(BigInteger bigInteger, SecureRandom secureRandom) {
            BigInteger modPow;
            BigInteger subtract = bigInteger.subtract(f14721a);
            do {
                BigInteger bigInteger2 = f14721a;
                modPow = BigIntegers.createRandomInRange(bigInteger2, subtract, secureRandom).modPow(bigInteger2, bigInteger);
            } while (modPow.equals(CramerShoupParametersGenerator.d));
            return modPow;
        }
    }

    public CramerShoupParameters generateParameters() {
        BigInteger b;
        BigInteger bigInteger = a.a(this.f14720a, this.b, this.c)[1];
        BigInteger b2 = a.b(bigInteger, this.c);
        do {
            b = a.b(bigInteger, this.c);
        } while (b2.equals(b));
        return new CramerShoupParameters(bigInteger, b2, b, new SHA256Digest());
    }

    public CramerShoupParameters generateParameters(DHParameters dHParameters) {
        BigInteger b;
        BigInteger p = dHParameters.getP();
        BigInteger g = dHParameters.getG();
        do {
            b = a.b(p, this.c);
        } while (g.equals(b));
        return new CramerShoupParameters(p, g, b, new SHA256Digest());
    }

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.f14720a = i;
        this.b = i2;
        this.c = secureRandom;
    }
}
