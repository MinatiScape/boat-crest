package org.bouncycastle.math.field;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public abstract class FiniteFields {

    /* renamed from: a  reason: collision with root package name */
    public static final FiniteField f15192a = new c(BigInteger.valueOf(2));
    public static final FiniteField b = new c(BigInteger.valueOf(3));

    public static PolynomialExtensionField getBinaryExtensionField(int[] iArr) {
        if (iArr[0] == 0) {
            for (int i = 1; i < iArr.length; i++) {
                if (iArr[i] <= iArr[i - 1]) {
                    throw new IllegalArgumentException("Polynomial exponents must be montonically increasing");
                }
            }
            return new b(f15192a, new a(iArr));
        }
        throw new IllegalArgumentException("Irreducible polynomials in GF(2) must have constant term");
    }

    public static FiniteField getPrimeField(BigInteger bigInteger) {
        int bitLength = bigInteger.bitLength();
        if (bigInteger.signum() <= 0 || bitLength < 2) {
            throw new IllegalArgumentException("'characteristic' must be >= 2");
        }
        if (bitLength < 3) {
            int intValue = bigInteger.intValue();
            if (intValue == 2) {
                return f15192a;
            }
            if (intValue == 3) {
                return b;
            }
        }
        return new c(bigInteger);
    }
}
