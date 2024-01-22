package org.bouncycastle.pqc.math.linearalgebra;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Random;
/* loaded from: classes13.dex */
public class GF2nONBField extends GF2nField {

    /* renamed from: a  reason: collision with root package name */
    public int f15369a;
    public int b;
    public int c;
    public int[][] d;

    public GF2nONBField(int i, SecureRandom secureRandom) throws RuntimeException {
        super(secureRandom);
        if (i < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = i;
        int i2 = i / 64;
        this.f15369a = i2;
        int i3 = i & 63;
        this.b = i3;
        if (i3 == 0) {
            this.b = 64;
        } else {
            this.f15369a = i2 + 1;
        }
        b();
        if (this.c >= 3) {
            throw new RuntimeException("\nThe type of this field is " + this.c);
        }
        this.d = (int[][]) Array.newInstance(int.class, this.mDegree, 2);
        for (int i4 = 0; i4 < this.mDegree; i4++) {
            int[][] iArr = this.d;
            iArr[i4][0] = -1;
            iArr[i4][1] = -1;
        }
        a();
        computeFieldPolynomial();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public final void a() {
        int i;
        int i2 = this.c;
        if ((i2 & 7) == 0) {
            throw new RuntimeException("bisher nur fuer Gausssche Normalbasen implementiert");
        }
        int i3 = (this.mDegree * i2) + 1;
        int[] iArr = new int[i3];
        int c = i2 == 1 ? 1 : i2 == 2 ? i3 - 1 : c(i2, i3);
        int i4 = 1;
        int i5 = 0;
        while (true) {
            i = this.c;
            if (i5 >= i) {
                break;
            }
            int i6 = i4;
            for (int i7 = 0; i7 < this.mDegree; i7++) {
                iArr[i6] = i7;
                i6 = (i6 << 1) % i3;
                if (i6 < 0) {
                    i6 += i3;
                }
            }
            i4 = (i4 * c) % i3;
            if (i4 < 0) {
                i4 += i3;
            }
            i5++;
        }
        if (i != 1) {
            if (i != 2) {
                throw new RuntimeException("only type 1 or type 2 implemented");
            }
            int i8 = 1;
            while (i8 < i3 - 1) {
                int[][] iArr2 = this.d;
                int i9 = i8 + 1;
                if (iArr2[iArr[i9]][0] == -1) {
                    iArr2[iArr[i9]][0] = iArr[i3 - i8];
                } else {
                    iArr2[iArr[i9]][1] = iArr[i3 - i8];
                }
                i8 = i9;
            }
            return;
        }
        int i10 = 1;
        while (i10 < i3 - 1) {
            int[][] iArr3 = this.d;
            int i11 = i10 + 1;
            if (iArr3[iArr[i11]][0] == -1) {
                iArr3[iArr[i11]][0] = iArr[i3 - i10];
            } else {
                iArr3[iArr[i11]][1] = iArr[i3 - i10];
            }
            i10 = i11;
        }
        int i12 = this.mDegree >> 1;
        for (int i13 = 1; i13 <= i12; i13++) {
            int[][] iArr4 = this.d;
            int i14 = i13 - 1;
            if (iArr4[i14][0] == -1) {
                iArr4[i14][0] = (i12 + i13) - 1;
            } else {
                iArr4[i14][1] = (i12 + i13) - 1;
            }
            int i15 = (i12 + i13) - 1;
            if (iArr4[i15][0] == -1) {
                iArr4[i15][0] = i14;
            } else {
                iArr4[i15][1] = i14;
            }
        }
    }

    public final void b() throws RuntimeException {
        if ((this.mDegree & 7) == 0) {
            throw new RuntimeException("The extension degree is divisible by 8!");
        }
        this.c = 1;
        int i = 0;
        while (i != 1) {
            int i2 = (this.c * this.mDegree) + 1;
            if (IntegerFunctions.isPrime(i2)) {
                int order = IntegerFunctions.order(2, i2);
                int i3 = this.c;
                int i4 = this.mDegree;
                i = IntegerFunctions.gcd((i3 * i4) / order, i4);
            }
            this.c++;
        }
        int i5 = this.c - 1;
        this.c = i5;
        if (i5 == 1) {
            int i6 = (this.mDegree << 1) + 1;
            if (IntegerFunctions.isPrime(i6)) {
                int order2 = IntegerFunctions.order(2, i6);
                int i7 = this.mDegree;
                if (IntegerFunctions.gcd((i7 << 1) / order2, i7) == 1) {
                    this.c++;
                }
            }
        }
    }

    public final int c(int i, int i2) {
        int order;
        Random random = new Random();
        int i3 = 0;
        while (i3 == 0) {
            int i4 = i2 - 1;
            i3 = random.nextInt() % i4;
            if (i3 < 0) {
                i3 += i4;
            }
        }
        while (true) {
            order = IntegerFunctions.order(i3, i2);
            if (order % i == 0 && order != 0) {
                break;
            }
            while (i3 == 0) {
                int i5 = i2 - 1;
                i3 = random.nextInt() % i5;
                if (i3 < 0) {
                    i3 += i5;
                }
            }
        }
        int i6 = i3;
        for (int i7 = 2; i7 <= i / order; i7++) {
            i6 *= i3;
        }
        return i6;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    public void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement randomRoot;
        int i = this.mDegree;
        if (i != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i];
        for (int i2 = 0; i2 < this.mDegree; i2++) {
            gF2PolynomialArr[i2] = new GF2Polynomial(this.mDegree);
        }
        do {
            randomRoot = gF2nField.getRandomRoot(this.fieldPolynomial);
        } while (randomRoot.isZero());
        GF2nElement[] gF2nElementArr = new GF2nPolynomialElement[this.mDegree];
        gF2nElementArr[0] = (GF2nElement) randomRoot.clone();
        for (int i3 = 1; i3 < this.mDegree; i3++) {
            gF2nElementArr[i3] = gF2nElementArr[i3 - 1].square();
        }
        for (int i4 = 0; i4 < this.mDegree; i4++) {
            for (int i5 = 0; i5 < this.mDegree; i5++) {
                if (gF2nElementArr[i4].b(i5)) {
                    int i6 = this.mDegree;
                    gF2PolynomialArr[(i6 - i5) - 1].setBit((i6 - i4) - 1);
                }
            }
        }
        this.fields.addElement(gF2nField);
        this.matrices.addElement(gF2PolynomialArr);
        gF2nField.fields.addElement(this);
        gF2nField.matrices.addElement(invertMatrix(gF2PolynomialArr));
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    public void computeFieldPolynomial() {
        GF2Polynomial gF2Polynomial;
        int i = this.c;
        if (i == 1) {
            gF2Polynomial = new GF2Polynomial(this.mDegree + 1, "ALL");
        } else if (i != 2) {
            return;
        } else {
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree + 1, "ONE");
            GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this.mDegree + 1, "X");
            gF2Polynomial3.addToThis(gF2Polynomial2);
            GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
            gF2Polynomial = gF2Polynomial3;
            int i2 = 1;
            while (i2 < this.mDegree) {
                GF2Polynomial shiftLeft = gF2Polynomial.shiftLeft();
                shiftLeft.addToThis(gF2Polynomial4);
                i2++;
                gF2Polynomial4 = gF2Polynomial;
                gF2Polynomial = shiftLeft;
            }
        }
        this.fieldPolynomial = gF2Polynomial;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.f15369a;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    public GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nONBElement gF2nONBElement = new GF2nONBElement(this, this.random);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nONBElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nONBElement);
                GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial2);
                for (int i = 1; i <= this.mDegree - 1; i++) {
                    gF2nPolynomial3 = gF2nPolynomial3.multiplyAndReduce(gF2nPolynomial3, gF2nPolynomial).add(gF2nPolynomial2);
                }
                gcd = gF2nPolynomial3.gcd(gF2nPolynomial);
                degree = gcd.getDegree();
                degree2 = gF2nPolynomial.getDegree();
                if (degree != 0 && degree != degree2) {
                    break;
                }
            }
            gF2nPolynomial = (degree << 1) > degree2 ? gF2nPolynomial.quotient(gcd) : new GF2nPolynomial(gcd);
        }
        return gF2nPolynomial.at(0);
    }
}
