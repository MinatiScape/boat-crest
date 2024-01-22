package org.bouncycastle.math.ec.tools;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes13.dex */
public class DiscoverEndomorphisms {
    public static boolean a(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.gcd(bigInteger2).equals(ECConstants.ONE);
    }

    public static BigInteger[] b(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return m(bigInteger.subtract(bigInteger2).divide(bigInteger3), bigInteger.add(bigInteger2).divide(bigInteger3));
    }

    public static BigInteger[] c(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        return j(bigIntegerArr, bigIntegerArr2) ? bigIntegerArr : bigIntegerArr2;
    }

    public static void d(String str) {
        X9ECParameters byName = ECNamedCurveTable.getByName(str);
        if (byName == null) {
            PrintStream printStream = System.err;
            printStream.println("Unknown curve: " + str);
            return;
        }
        ECCurve curve = byName.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            BigInteger characteristic = curve.getField().getCharacteristic();
            if (curve.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                PrintStream printStream2 = System.out;
                printStream2.println("Curve '" + str + "' has a 'GLV Type B' endomorphism with these parameters:");
                n(byName);
            }
        }
    }

    public static void discoverEndomorphisms(X9ECParameters x9ECParameters) {
        Objects.requireNonNull(x9ECParameters, "x9");
        ECCurve curve = x9ECParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            BigInteger characteristic = curve.getField().getCharacteristic();
            if (curve.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve has a 'GLV Type B' endomorphism with these parameters:");
                n(x9ECParameters);
            }
        }
    }

    public static BigInteger[] e(BigInteger[] bigIntegerArr) {
        boolean z = bigIntegerArr[0].compareTo(bigIntegerArr[1]) < 0;
        if (z) {
            s(bigIntegerArr);
        }
        BigInteger bigInteger = bigIntegerArr[0];
        BigInteger bigInteger2 = bigIntegerArr[1];
        BigInteger bigInteger3 = ECConstants.ONE;
        BigInteger bigInteger4 = ECConstants.ZERO;
        BigInteger bigInteger5 = bigInteger3;
        BigInteger bigInteger6 = bigInteger4;
        BigInteger bigInteger7 = bigInteger2;
        BigInteger bigInteger8 = bigInteger;
        while (bigInteger7.compareTo(ECConstants.ONE) > 0) {
            BigInteger[] divideAndRemainder = bigInteger8.divideAndRemainder(bigInteger7);
            BigInteger bigInteger9 = divideAndRemainder[0];
            BigInteger bigInteger10 = divideAndRemainder[1];
            BigInteger subtract = bigInteger3.subtract(bigInteger9.multiply(bigInteger4));
            BigInteger subtract2 = bigInteger6.subtract(bigInteger9.multiply(bigInteger5));
            BigInteger bigInteger11 = bigInteger7;
            bigInteger7 = bigInteger10;
            bigInteger8 = bigInteger11;
            BigInteger bigInteger12 = bigInteger4;
            bigInteger4 = subtract;
            bigInteger3 = bigInteger12;
            bigInteger6 = bigInteger5;
            bigInteger5 = subtract2;
        }
        if (bigInteger7.signum() <= 0) {
            return null;
        }
        BigInteger[] bigIntegerArr2 = {bigInteger4, bigInteger5};
        if (z) {
            s(bigIntegerArr2);
        }
        return bigIntegerArr2;
    }

    public static BigInteger[] f(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = ECConstants.ZERO;
        BigInteger bigInteger4 = ECConstants.ONE;
        BigInteger bigInteger5 = bigInteger;
        while (true) {
            BigInteger[] divideAndRemainder = bigInteger5.divideAndRemainder(bigInteger2);
            BigInteger bigInteger6 = divideAndRemainder[0];
            BigInteger bigInteger7 = divideAndRemainder[1];
            BigInteger subtract = bigInteger3.subtract(bigInteger6.multiply(bigInteger4));
            if (i(bigInteger2, bigInteger)) {
                return new BigInteger[]{bigInteger5, bigInteger3, bigInteger2, bigInteger4, bigInteger7, subtract};
            }
            bigInteger5 = bigInteger2;
            bigInteger3 = bigInteger4;
            bigInteger2 = bigInteger7;
            bigInteger4 = subtract;
        }
    }

    public static ECFieldElement[] g(ECCurve eCCurve) {
        BigInteger modPow;
        BigInteger characteristic = eCCurve.getField().getCharacteristic();
        BigInteger divide = characteristic.divide(ECConstants.THREE);
        SecureRandom secureRandom = new SecureRandom();
        do {
            BigInteger bigInteger = ECConstants.TWO;
            modPow = BigIntegers.createRandomInRange(bigInteger, characteristic.subtract(bigInteger), secureRandom).modPow(divide, characteristic);
        } while (modPow.equals(ECConstants.ONE));
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(modPow);
        return new ECFieldElement[]{fromBigInteger, fromBigInteger.square()};
    }

    public static BigInteger[] h(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger max = bigIntegerArr[0].max(bigIntegerArr2[0]);
        BigInteger min = bigIntegerArr[1].min(bigIntegerArr2[1]);
        if (max.compareTo(min) > 0) {
            return null;
        }
        return new BigInteger[]{max, min};
    }

    public static boolean i(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        int bitLength = abs2.bitLength();
        int bitLength2 = abs.bitLength() * 2;
        return bitLength2 + (-1) <= bitLength && (bitLength2 < bitLength || abs.multiply(abs).compareTo(abs2) < 0);
    }

    public static boolean j(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2) {
        BigInteger abs = bigIntegerArr[0].abs();
        BigInteger abs2 = bigIntegerArr[1].abs();
        BigInteger abs3 = bigIntegerArr2[0].abs();
        BigInteger abs4 = bigIntegerArr2[1].abs();
        boolean z = abs.compareTo(abs3) < 0;
        return z == (abs2.compareTo(abs4) < 0) ? z : abs.multiply(abs).add(abs2.multiply(abs2)).compareTo(abs3.multiply(abs3).add(abs4.multiply(abs4))) < 0;
    }

    public static boolean k(BigInteger[] bigIntegerArr, BigInteger bigInteger) {
        return i(bigIntegerArr[0].abs().max(bigIntegerArr[1].abs()), bigInteger);
    }

    public static BigInteger l(BigInteger bigInteger) {
        BigInteger shiftRight = bigInteger.shiftRight(bigInteger.bitLength() / 2);
        while (true) {
            BigInteger shiftRight2 = shiftRight.add(bigInteger.divide(shiftRight)).shiftRight(1);
            if (shiftRight2.equals(shiftRight)) {
                return shiftRight2;
            }
            shiftRight = shiftRight2;
        }
    }

    public static BigInteger[] m(BigInteger bigInteger, BigInteger bigInteger2) {
        return bigInteger.compareTo(bigInteger2) <= 0 ? new BigInteger[]{bigInteger, bigInteger2} : new BigInteger[]{bigInteger2, bigInteger};
    }

    public static void main(String[] strArr) {
        if (strArr.length < 1) {
            System.err.println("Expected a list of curve names as arguments");
            return;
        }
        for (String str : strArr) {
            d(str);
        }
    }

    public static void n(X9ECParameters x9ECParameters) {
        BigInteger n = x9ECParameters.getN();
        BigInteger bigInteger = ECConstants.ONE;
        BigInteger[] r = r(n, bigInteger, bigInteger);
        ECFieldElement[] g = g(x9ECParameters.getCurve());
        o(x9ECParameters, r[0], g);
        System.out.println("OR");
        o(x9ECParameters, r[1], g);
    }

    public static void o(X9ECParameters x9ECParameters, BigInteger bigInteger, ECFieldElement[] eCFieldElementArr) {
        ECPoint normalize = x9ECParameters.getG().normalize();
        ECPoint normalize2 = normalize.multiply(bigInteger).normalize();
        if (!normalize.getYCoord().equals(normalize2.getYCoord())) {
            throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
        }
        ECFieldElement eCFieldElement = eCFieldElementArr[0];
        if (!normalize.getXCoord().multiply(eCFieldElement).equals(normalize2.getXCoord())) {
            eCFieldElement = eCFieldElementArr[1];
            if (!normalize.getXCoord().multiply(eCFieldElement).equals(normalize2.getXCoord())) {
                throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
            }
        }
        BigInteger n = x9ECParameters.getN();
        BigInteger[] f = f(n, bigInteger);
        BigInteger[] bigIntegerArr = {f[2], f[3].negate()};
        BigInteger[] c = c(new BigInteger[]{f[0], f[1].negate()}, new BigInteger[]{f[4], f[5].negate()});
        if (!k(c, n) && a(bigIntegerArr[0], bigIntegerArr[1])) {
            BigInteger bigInteger2 = bigIntegerArr[0];
            BigInteger bigInteger3 = bigIntegerArr[1];
            BigInteger divide = bigInteger2.add(bigInteger3.multiply(bigInteger)).divide(n);
            BigInteger[] e = e(new BigInteger[]{divide.abs(), bigInteger3.abs()});
            if (e != null) {
                BigInteger bigInteger4 = e[0];
                BigInteger bigInteger5 = e[1];
                if (divide.signum() < 0) {
                    bigInteger4 = bigInteger4.negate();
                }
                if (bigInteger3.signum() > 0) {
                    bigInteger5 = bigInteger5.negate();
                }
                BigInteger subtract = divide.multiply(bigInteger4).subtract(bigInteger3.multiply(bigInteger5));
                BigInteger bigInteger6 = ECConstants.ONE;
                if (!subtract.equals(bigInteger6)) {
                    throw new IllegalStateException();
                }
                BigInteger subtract2 = bigInteger5.multiply(n).subtract(bigInteger4.multiply(bigInteger));
                BigInteger negate = bigInteger4.negate();
                BigInteger negate2 = subtract2.negate();
                BigInteger add = l(n.subtract(bigInteger6)).add(bigInteger6);
                BigInteger[] h = h(b(negate, add, bigInteger3), b(negate2, add, bigInteger2));
                if (h != null) {
                    for (BigInteger bigInteger7 = h[0]; bigInteger7.compareTo(h[1]) <= 0; bigInteger7 = bigInteger7.add(ECConstants.ONE)) {
                        BigInteger[] bigIntegerArr2 = {subtract2.add(bigInteger7.multiply(bigInteger2)), bigInteger4.add(bigInteger7.multiply(bigInteger3))};
                        if (j(bigIntegerArr2, c)) {
                            c = bigIntegerArr2;
                        }
                    }
                }
            }
        }
        BigInteger subtract3 = bigIntegerArr[0].multiply(c[1]).subtract(bigIntegerArr[1].multiply(c[0]));
        int bitLength = (n.bitLength() + 16) - (n.bitLength() & 7);
        BigInteger q = q(c[1].shiftLeft(bitLength), subtract3);
        BigInteger negate3 = q(bigIntegerArr[1].shiftLeft(bitLength), subtract3).negate();
        p("Beta", eCFieldElement.toBigInteger().toString(16));
        p("Lambda", bigInteger.toString(16));
        p("v1", "{ " + bigIntegerArr[0].toString(16) + ", " + bigIntegerArr[1].toString(16) + " }");
        p("v2", "{ " + c[0].toString(16) + ", " + c[1].toString(16) + " }");
        p("d", subtract3.toString(16));
        p("(OPT) g1", q.toString(16));
        p("(OPT) g2", negate3.toString(16));
        p("(OPT) bits", Integer.toString(bitLength));
    }

    public static void p(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer("  ");
        stringBuffer.append(str);
        while (stringBuffer.length() < 20) {
            stringBuffer.append(' ');
        }
        stringBuffer.append("= ");
        stringBuffer.append(obj.toString());
        System.out.println(stringBuffer.toString());
    }

    public static BigInteger q(BigInteger bigInteger, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() != bigInteger2.signum();
        BigInteger abs = bigInteger.abs();
        BigInteger abs2 = bigInteger2.abs();
        BigInteger divide = abs.add(abs2.shiftRight(1)).divide(abs2);
        return z ? divide.negate() : divide;
    }

    public static BigInteger[] r(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        BigInteger bigInteger4 = new ECFieldElement.Fp(bigInteger, bigInteger2.multiply(bigInteger2).subtract(bigInteger3.shiftLeft(2)).mod(bigInteger)).sqrt().toBigInteger();
        BigInteger subtract = bigInteger.subtract(bigInteger4);
        if (bigInteger4.testBit(0)) {
            subtract = subtract.add(bigInteger);
        } else {
            bigInteger4 = bigInteger4.add(bigInteger);
        }
        return new BigInteger[]{bigInteger4.shiftRight(1), subtract.shiftRight(1)};
    }

    public static void s(BigInteger[] bigIntegerArr) {
        BigInteger bigInteger = bigIntegerArr[0];
        bigIntegerArr[0] = bigIntegerArr[1];
        bigIntegerArr[1] = bigInteger;
    }
}
