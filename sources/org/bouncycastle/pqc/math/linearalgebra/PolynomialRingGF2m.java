package org.bouncycastle.pqc.math.linearalgebra;
/* loaded from: classes13.dex */
public class PolynomialRingGF2m {

    /* renamed from: a  reason: collision with root package name */
    public GF2mField f15378a;
    public PolynomialGF2mSmallM b;
    public PolynomialGF2mSmallM[] sqMatrix;
    public PolynomialGF2mSmallM[] sqRootMatrix;

    public PolynomialRingGF2m(GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM) {
        this.f15378a = gF2mField;
        this.b = polynomialGF2mSmallM;
        b();
        a();
    }

    public static void c(PolynomialGF2mSmallM[] polynomialGF2mSmallMArr, int i, int i2) {
        PolynomialGF2mSmallM polynomialGF2mSmallM = polynomialGF2mSmallMArr[i];
        polynomialGF2mSmallMArr[i] = polynomialGF2mSmallMArr[i2];
        polynomialGF2mSmallMArr[i2] = polynomialGF2mSmallM;
    }

    public final void a() {
        int coefficient;
        int degree = this.b.getDegree();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArr = new PolynomialGF2mSmallM[degree];
        int i = degree - 1;
        for (int i2 = i; i2 >= 0; i2--) {
            polynomialGF2mSmallMArr[i2] = new PolynomialGF2mSmallM(this.sqMatrix[i2]);
        }
        this.sqRootMatrix = new PolynomialGF2mSmallM[degree];
        while (i >= 0) {
            this.sqRootMatrix[i] = new PolynomialGF2mSmallM(this.f15378a, i);
            i--;
        }
        for (int i3 = 0; i3 < degree; i3++) {
            if (polynomialGF2mSmallMArr[i3].getCoefficient(i3) == 0) {
                int i4 = i3 + 1;
                boolean z = false;
                while (i4 < degree) {
                    if (polynomialGF2mSmallMArr[i4].getCoefficient(i3) != 0) {
                        c(polynomialGF2mSmallMArr, i3, i4);
                        c(this.sqRootMatrix, i3, i4);
                        i4 = degree;
                        z = true;
                    }
                    i4++;
                }
                if (!z) {
                    throw new ArithmeticException("Squaring matrix is not invertible.");
                }
            }
            int inverse = this.f15378a.inverse(polynomialGF2mSmallMArr[i3].getCoefficient(i3));
            polynomialGF2mSmallMArr[i3].multThisWithElement(inverse);
            this.sqRootMatrix[i3].multThisWithElement(inverse);
            for (int i5 = 0; i5 < degree; i5++) {
                if (i5 != i3 && (coefficient = polynomialGF2mSmallMArr[i5].getCoefficient(i3)) != 0) {
                    PolynomialGF2mSmallM multWithElement = polynomialGF2mSmallMArr[i3].multWithElement(coefficient);
                    PolynomialGF2mSmallM multWithElement2 = this.sqRootMatrix[i3].multWithElement(coefficient);
                    polynomialGF2mSmallMArr[i5].addToThis(multWithElement);
                    this.sqRootMatrix[i5].addToThis(multWithElement2);
                }
            }
        }
    }

    public final void b() {
        int i;
        int degree = this.b.getDegree();
        this.sqMatrix = new PolynomialGF2mSmallM[degree];
        int i2 = 0;
        while (true) {
            i = degree >> 1;
            if (i2 >= i) {
                break;
            }
            int i3 = i2 << 1;
            int[] iArr = new int[i3 + 1];
            iArr[i3] = 1;
            this.sqMatrix[i2] = new PolynomialGF2mSmallM(this.f15378a, iArr);
            i2++;
        }
        while (i < degree) {
            int i4 = i << 1;
            int[] iArr2 = new int[i4 + 1];
            iArr2[i4] = 1;
            this.sqMatrix[i] = new PolynomialGF2mSmallM(this.f15378a, iArr2).mod(this.b);
            i++;
        }
    }

    public PolynomialGF2mSmallM[] getSquareRootMatrix() {
        return this.sqRootMatrix;
    }

    public PolynomialGF2mSmallM[] getSquaringMatrix() {
        return this.sqMatrix;
    }
}
