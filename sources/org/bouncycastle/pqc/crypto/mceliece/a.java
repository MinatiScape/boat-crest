package org.bouncycastle.pqc.crypto.mceliece;

import java.math.BigInteger;
import org.bouncycastle.pqc.math.linearalgebra.BigIntUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;
/* loaded from: classes13.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final BigInteger f15305a = BigInteger.valueOf(0);
    public static final BigInteger b = BigInteger.valueOf(1);

    public static byte[] a(int i, int i2, GF2Vector gF2Vector) {
        if (gF2Vector.getLength() == i && gF2Vector.getHammingWeight() == i2) {
            int[] vecArray = gF2Vector.getVecArray();
            BigInteger binomial = IntegerFunctions.binomial(i, i2);
            BigInteger bigInteger = f15305a;
            int i3 = i;
            for (int i4 = 0; i4 < i; i4++) {
                binomial = binomial.multiply(BigInteger.valueOf(i3 - i2)).divide(BigInteger.valueOf(i3));
                i3--;
                if ((vecArray[i4 >> 5] & (1 << (i4 & 31))) != 0) {
                    bigInteger = bigInteger.add(binomial);
                    i2--;
                    binomial = i3 == i2 ? b : binomial.multiply(BigInteger.valueOf(i2 + 1)).divide(BigInteger.valueOf(i3 - i2));
                }
            }
            return BigIntUtils.toMinimalByteArray(bigInteger);
        }
        throw new IllegalArgumentException("vector has wrong length or hamming weight");
    }

    public static GF2Vector b(int i, int i2, byte[] bArr) {
        if (i >= i2) {
            BigInteger binomial = IntegerFunctions.binomial(i, i2);
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.compareTo(binomial) < 0) {
                GF2Vector gF2Vector = new GF2Vector(i);
                int i3 = i;
                for (int i4 = 0; i4 < i; i4++) {
                    binomial = binomial.multiply(BigInteger.valueOf(i3 - i2)).divide(BigInteger.valueOf(i3));
                    i3--;
                    if (binomial.compareTo(bigInteger) <= 0) {
                        gF2Vector.setBit(i4);
                        bigInteger = bigInteger.subtract(binomial);
                        i2--;
                        binomial = i3 == i2 ? b : binomial.multiply(BigInteger.valueOf(i2 + 1)).divide(BigInteger.valueOf(i3 - i2));
                    }
                }
                return gF2Vector;
            }
            throw new IllegalArgumentException("Encoded number too large.");
        }
        throw new IllegalArgumentException("n < t");
    }
}
