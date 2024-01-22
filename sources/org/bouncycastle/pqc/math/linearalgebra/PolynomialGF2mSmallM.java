package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class PolynomialGF2mSmallM {
    public static final char RANDOM_IRREDUCIBLE_POLYNOMIAL = 'I';

    /* renamed from: a  reason: collision with root package name */
    public GF2mField f15377a;
    public int b;
    public int[] c;

    public PolynomialGF2mSmallM(GF2mField gF2mField) {
        this.f15377a = gF2mField;
        this.b = -1;
        this.c = new int[1];
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int i) {
        this.f15377a = gF2mField;
        this.b = i;
        int[] iArr = new int[i + 1];
        this.c = iArr;
        iArr[i] = 1;
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int i, char c, SecureRandom secureRandom) {
        this.f15377a = gF2mField;
        if (c == 'I') {
            this.c = d(i, secureRandom);
            c();
            return;
        }
        throw new IllegalArgumentException(" Error: type " + c + " is not defined for GF2smallmPolynomial");
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, byte[] bArr) {
        this.f15377a = gF2mField;
        int i = 8;
        int i2 = 1;
        while (gF2mField.getDegree() > i) {
            i2++;
            i += 8;
        }
        if (bArr.length % i2 != 0) {
            throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
        }
        this.c = new int[bArr.length / i2];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int[] iArr = this.c;
            if (i3 >= iArr.length) {
                if (iArr.length != 1 && iArr[iArr.length - 1] == 0) {
                    throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
                }
                c();
                return;
            }
            int i5 = 0;
            while (i5 < i) {
                int[] iArr2 = this.c;
                iArr2[i3] = ((bArr[i4] & 255) << i5) ^ iArr2[i3];
                i5 += 8;
                i4++;
            }
            if (!this.f15377a.isElementOfThisField(this.c[i3])) {
                throw new IllegalArgumentException(" Error: byte array is not encoded polynomial over given finite field GF2m");
            }
            i3++;
        }
    }

    public PolynomialGF2mSmallM(GF2mField gF2mField, int[] iArr) {
        this.f15377a = gF2mField;
        this.c = p(iArr);
        c();
    }

    public PolynomialGF2mSmallM(GF2mVector gF2mVector) {
        this(gF2mVector.getField(), gF2mVector.getIntArrayForm());
    }

    public PolynomialGF2mSmallM(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.f15377a = polynomialGF2mSmallM.f15377a;
        this.b = polynomialGF2mSmallM.b;
        this.c = IntUtils.clone(polynomialGF2mSmallM.c);
    }

    public static int b(int[] iArr) {
        int length = iArr.length - 1;
        while (length >= 0 && iArr[length] == 0) {
            length--;
        }
        return length;
    }

    public static int g(int[] iArr) {
        int b = b(iArr);
        if (b == -1) {
            return 0;
        }
        return iArr[b];
    }

    public static boolean h(int[] iArr, int[] iArr2) {
        int b = b(iArr);
        if (b != b(iArr2)) {
            return false;
        }
        for (int i = 0; i <= b; i++) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] n(int[] iArr, int i) {
        int b = b(iArr);
        if (b == -1) {
            return new int[1];
        }
        int[] iArr2 = new int[b + i + 1];
        System.arraycopy(iArr, 0, iArr2, i, b + 1);
        return iArr2;
    }

    public static int[] p(int[] iArr) {
        int b = b(iArr);
        if (b == -1) {
            return new int[1];
        }
        int i = b + 1;
        if (iArr.length == i) {
            return IntUtils.clone(iArr);
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }

    public final int[] a(int[] iArr, int[] iArr2) {
        int[] iArr3;
        if (iArr.length < iArr2.length) {
            iArr3 = new int[iArr2.length];
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
        } else {
            iArr3 = new int[iArr.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            iArr = iArr2;
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr3[length] = this.f15377a.add(iArr3[length], iArr[length]);
        }
        return iArr3;
    }

    public PolynomialGF2mSmallM add(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.f15377a, a(this.c, polynomialGF2mSmallM.c));
    }

    public PolynomialGF2mSmallM addMonomial(int i) {
        int[] iArr = new int[i + 1];
        iArr[i] = 1;
        return new PolynomialGF2mSmallM(this.f15377a, a(this.c, iArr));
    }

    public void addToThis(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.c = a(this.c, polynomialGF2mSmallM.c);
        c();
    }

    public final void c() {
        int length = this.c.length;
        do {
            this.b = length - 1;
            length = this.b;
            if (length < 0) {
                return;
            }
        } while (this.c[length] == 0);
    }

    public final int[] d(int i, SecureRandom secureRandom) {
        int[] iArr = new int[i + 1];
        iArr[i] = 1;
        iArr[0] = this.f15377a.getRandomNonZeroElement(secureRandom);
        for (int i2 = 1; i2 < i; i2++) {
            iArr[i2] = this.f15377a.getRandomElement(secureRandom);
        }
        while (!i(iArr)) {
            int a2 = RandUtils.a(secureRandom, i);
            if (a2 == 0) {
                iArr[0] = this.f15377a.getRandomNonZeroElement(secureRandom);
            } else {
                iArr[a2] = this.f15377a.getRandomElement(secureRandom);
            }
        }
        return iArr;
    }

    public PolynomialGF2mSmallM[] div(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int[][] e = e(this.c, polynomialGF2mSmallM.c);
        return new PolynomialGF2mSmallM[]{new PolynomialGF2mSmallM(this.f15377a, e[0]), new PolynomialGF2mSmallM(this.f15377a, e[1])};
    }

    public final int[][] e(int[] iArr, int[] iArr2) {
        int b = b(iArr2);
        int b2 = b(iArr) + 1;
        if (b != -1) {
            int[][] iArr3 = {new int[1], new int[b2]};
            int inverse = this.f15377a.inverse(g(iArr2));
            iArr3[0][0] = 0;
            System.arraycopy(iArr, 0, iArr3[1], 0, iArr3[1].length);
            while (b <= b(iArr3[1])) {
                int[] iArr4 = {this.f15377a.mult(g(iArr3[1]), inverse)};
                int[] m = m(iArr2, iArr4[0]);
                int b3 = b(iArr3[1]) - b;
                int[] n = n(m, b3);
                iArr3[0] = a(n(iArr4, b3), iArr3[0]);
                iArr3[1] = a(n, iArr3[1]);
            }
            return iArr3;
        }
        throw new ArithmeticException("Division by zero.");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof PolynomialGF2mSmallM)) {
            PolynomialGF2mSmallM polynomialGF2mSmallM = (PolynomialGF2mSmallM) obj;
            if (this.f15377a.equals(polynomialGF2mSmallM.f15377a) && this.b == polynomialGF2mSmallM.b && h(this.c, polynomialGF2mSmallM.c)) {
                return true;
            }
        }
        return false;
    }

    public int evaluateAt(int i) {
        int[] iArr = this.c;
        int i2 = this.b;
        int i3 = iArr[i2];
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            i3 = this.f15377a.mult(i3, i) ^ this.c[i4];
        }
        return i3;
    }

    public final int[] f(int[] iArr, int[] iArr2) {
        if (b(iArr) == -1) {
            return iArr2;
        }
        while (b(iArr2) != -1) {
            int[] j = j(iArr, iArr2);
            int length = iArr2.length;
            int[] iArr3 = new int[length];
            System.arraycopy(iArr2, 0, iArr3, 0, length);
            int length2 = j.length;
            int[] iArr4 = new int[length2];
            System.arraycopy(j, 0, iArr4, 0, length2);
            iArr2 = iArr4;
            iArr = iArr3;
        }
        return m(iArr, this.f15377a.inverse(g(iArr)));
    }

    public PolynomialGF2mSmallM gcd(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.f15377a, f(this.c, polynomialGF2mSmallM.c));
    }

    public int getCoefficient(int i) {
        if (i < 0 || i > this.b) {
            return 0;
        }
        return this.c[i];
    }

    public int getDegree() {
        int[] iArr = this.c;
        int length = iArr.length - 1;
        if (iArr[length] == 0) {
            return -1;
        }
        return length;
    }

    public byte[] getEncoded() {
        int i = 8;
        int i2 = 1;
        while (this.f15377a.getDegree() > i) {
            i2++;
            i += 8;
        }
        byte[] bArr = new byte[this.c.length * i2];
        int i3 = 0;
        for (int i4 = 0; i4 < this.c.length; i4++) {
            int i5 = 0;
            while (i5 < i) {
                bArr[i3] = (byte) (this.c[i4] >>> i5);
                i5 += 8;
                i3++;
            }
        }
        return bArr;
    }

    public int getHeadCoefficient() {
        int i = this.b;
        if (i == -1) {
            return 0;
        }
        return this.c[i];
    }

    public int hashCode() {
        int hashCode = this.f15377a.hashCode();
        int i = 0;
        while (true) {
            int[] iArr = this.c;
            if (i >= iArr.length) {
                return hashCode;
            }
            hashCode = (hashCode * 31) + iArr[i];
            i++;
        }
    }

    public final boolean i(int[] iArr) {
        if (iArr[0] == 0) {
            return false;
        }
        int b = b(iArr) >> 1;
        int[] iArr2 = {0, 1};
        int[] iArr3 = {0, 1};
        int degree = this.f15377a.getDegree();
        for (int i = 0; i < b; i++) {
            for (int i2 = degree - 1; i2 >= 0; i2--) {
                iArr2 = l(iArr2, iArr2, iArr);
            }
            iArr2 = p(iArr2);
            if (b(f(a(iArr2, iArr3), iArr)) != 0) {
                return false;
            }
        }
        return true;
    }

    public final int[] j(int[] iArr, int[] iArr2) {
        int b = b(iArr2);
        if (b != -1) {
            int length = iArr.length;
            int[] iArr3 = new int[length];
            int inverse = this.f15377a.inverse(g(iArr2));
            System.arraycopy(iArr, 0, iArr3, 0, length);
            while (b <= b(iArr3)) {
                iArr3 = a(m(n(iArr2, b(iArr3) - b), this.f15377a.mult(g(iArr3), inverse)), iArr3);
            }
            return iArr3;
        }
        throw new ArithmeticException("Division by zero");
    }

    public final int[] k(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] p = p(iArr3);
        int[] j = j(iArr2, iArr3);
        int[] iArr4 = {0};
        int[] j2 = j(iArr, iArr3);
        while (b(j) != -1) {
            int[][] e = e(p, j);
            int[] p2 = p(j);
            int[] p3 = p(e[1]);
            int[] a2 = a(iArr4, l(e[0], j2, iArr3));
            iArr4 = p(j2);
            j2 = p(a2);
            p = p2;
            j = p3;
        }
        return m(iArr4, this.f15377a.inverse(g(p)));
    }

    public final int[] l(int[] iArr, int[] iArr2, int[] iArr3) {
        return j(o(iArr, iArr2), iArr3);
    }

    public final int[] m(int[] iArr, int i) {
        int b = b(iArr);
        if (b == -1 || i == 0) {
            return new int[1];
        }
        if (i == 1) {
            return IntUtils.clone(iArr);
        }
        int[] iArr2 = new int[b + 1];
        while (b >= 0) {
            iArr2[b] = this.f15377a.mult(iArr[b], i);
            b--;
        }
        return iArr2;
    }

    public PolynomialGF2mSmallM mod(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.f15377a, j(this.c, polynomialGF2mSmallM.c));
    }

    public PolynomialGF2mSmallM modDiv(PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM polynomialGF2mSmallM2) {
        return new PolynomialGF2mSmallM(this.f15377a, k(this.c, polynomialGF2mSmallM.c, polynomialGF2mSmallM2.c));
    }

    public PolynomialGF2mSmallM modInverse(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.f15377a, k(new int[]{1}, this.c, polynomialGF2mSmallM.c));
    }

    public PolynomialGF2mSmallM modMultiply(PolynomialGF2mSmallM polynomialGF2mSmallM, PolynomialGF2mSmallM polynomialGF2mSmallM2) {
        return new PolynomialGF2mSmallM(this.f15377a, l(this.c, polynomialGF2mSmallM.c, polynomialGF2mSmallM2.c));
    }

    public PolynomialGF2mSmallM[] modPolynomialToFracton(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int i = polynomialGF2mSmallM.b >> 1;
        int[] p = p(polynomialGF2mSmallM.c);
        int[] j = j(this.c, polynomialGF2mSmallM.c);
        int[] iArr = {0};
        int[] iArr2 = {1};
        while (b(j) > i) {
            int[][] e = e(p, j);
            int[] iArr3 = e[1];
            int[] a2 = a(iArr, l(e[0], iArr2, polynomialGF2mSmallM.c));
            iArr = iArr2;
            iArr2 = a2;
            p = j;
            j = iArr3;
        }
        return new PolynomialGF2mSmallM[]{new PolynomialGF2mSmallM(this.f15377a, j), new PolynomialGF2mSmallM(this.f15377a, iArr2)};
    }

    public PolynomialGF2mSmallM modSquareMatrix(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int length = polynomialGF2mSmallMArr.length;
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        int i = 0;
        while (true) {
            int[] iArr3 = this.c;
            if (i >= iArr3.length) {
                break;
            }
            iArr2[i] = this.f15377a.mult(iArr3[i], iArr3[i]);
            i++;
        }
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < length; i3++) {
                if (i2 < polynomialGF2mSmallMArr[i3].c.length) {
                    iArr[i2] = this.f15377a.add(iArr[i2], this.f15377a.mult(polynomialGF2mSmallMArr[i3].c[i2], iArr2[i3]));
                }
            }
        }
        return new PolynomialGF2mSmallM(this.f15377a, iArr);
    }

    public PolynomialGF2mSmallM modSquareRoot(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        int[] clone = IntUtils.clone(this.c);
        int[] l = l(clone, clone, polynomialGF2mSmallM.c);
        while (!h(l, this.c)) {
            clone = p(l);
            l = l(clone, clone, polynomialGF2mSmallM.c);
        }
        return new PolynomialGF2mSmallM(this.f15377a, clone);
    }

    public PolynomialGF2mSmallM modSquareRootMatrix(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        int length = polynomialGF2mSmallMArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (i < polynomialGF2mSmallMArr[i2].c.length) {
                    int[] iArr2 = this.c;
                    if (i2 < iArr2.length) {
                        iArr[i] = this.f15377a.add(iArr[i], this.f15377a.mult(polynomialGF2mSmallMArr[i2].c[i], iArr2[i2]));
                    }
                }
            }
        }
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f15377a.sqRoot(iArr[i3]);
        }
        return new PolynomialGF2mSmallM(this.f15377a, iArr);
    }

    public void multThisWithElement(int i) {
        if (!this.f15377a.isElementOfThisField(i)) {
            throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
        }
        this.c = m(this.c, i);
        c();
    }

    public PolynomialGF2mSmallM multWithElement(int i) {
        if (this.f15377a.isElementOfThisField(i)) {
            return new PolynomialGF2mSmallM(this.f15377a, m(this.c, i));
        }
        throw new ArithmeticException("Not an element of the finite field this polynomial is defined over.");
    }

    public PolynomialGF2mSmallM multWithMonomial(int i) {
        return new PolynomialGF2mSmallM(this.f15377a, n(this.c, i));
    }

    public PolynomialGF2mSmallM multiply(PolynomialGF2mSmallM polynomialGF2mSmallM) {
        return new PolynomialGF2mSmallM(this.f15377a, o(this.c, polynomialGF2mSmallM.c));
    }

    public final int[] o(int[] iArr, int[] iArr2) {
        if (b(iArr) < b(iArr2)) {
            iArr2 = iArr;
            iArr = iArr2;
        }
        int[] p = p(iArr);
        int[] p2 = p(iArr2);
        if (p2.length == 1) {
            return m(p, p2[0]);
        }
        int length = p.length;
        int length2 = p2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        if (length2 != length) {
            int[] iArr4 = new int[length2];
            int i = length - length2;
            int[] iArr5 = new int[i];
            System.arraycopy(p, 0, iArr4, 0, length2);
            System.arraycopy(p, length2, iArr5, 0, i);
            return a(o(iArr4, p2), n(o(iArr5, p2), length2));
        }
        int i2 = (length + 1) >>> 1;
        int i3 = length - i2;
        int[] iArr6 = new int[i2];
        int[] iArr7 = new int[i2];
        int[] iArr8 = new int[i3];
        int[] iArr9 = new int[i3];
        System.arraycopy(p, 0, iArr6, 0, i2);
        System.arraycopy(p, i2, iArr8, 0, i3);
        System.arraycopy(p2, 0, iArr7, 0, i2);
        System.arraycopy(p2, i2, iArr9, 0, i3);
        int[] a2 = a(iArr6, iArr8);
        int[] a3 = a(iArr7, iArr9);
        int[] o = o(iArr6, iArr7);
        int[] o2 = o(a2, a3);
        int[] o3 = o(iArr8, iArr9);
        return a(n(a(a(a(o2, o), o3), n(o3, i2)), i2), o);
    }

    public String toString() {
        String str = " Polynomial over " + this.f15377a.toString() + ": \n";
        for (int i = 0; i < this.c.length; i++) {
            str = str + this.f15377a.elementToStr(this.c[i]) + "Y^" + i + "+";
        }
        return str + ";";
    }
}
