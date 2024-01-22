package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes13.dex */
public class GF2nPolynomialField extends GF2nField {

    /* renamed from: a  reason: collision with root package name */
    public GF2Polynomial[] f15372a;
    public boolean b;
    public boolean c;
    public int d;
    public int[] e;

    public GF2nPolynomialField(int i, SecureRandom secureRandom) {
        super(secureRandom);
        this.b = false;
        this.c = false;
        this.e = new int[3];
        if (i < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = i;
        computeFieldPolynomial();
        a();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public GF2nPolynomialField(int i, SecureRandom secureRandom, GF2Polynomial gF2Polynomial) throws RuntimeException {
        super(secureRandom);
        this.b = false;
        this.c = false;
        this.e = new int[3];
        if (i < 3) {
            throw new IllegalArgumentException("degree must be at least 3");
        }
        if (gF2Polynomial.getLength() != i + 1) {
            throw new RuntimeException();
        }
        if (!gF2Polynomial.isIrreducible()) {
            throw new RuntimeException();
        }
        this.mDegree = i;
        this.fieldPolynomial = gF2Polynomial;
        a();
        int i2 = 2;
        for (int i3 = 1; i3 < this.fieldPolynomial.getLength() - 1; i3++) {
            if (this.fieldPolynomial.testBit(i3)) {
                i2++;
                if (i2 == 3) {
                    this.d = i3;
                }
                if (i2 <= 5) {
                    this.e[i2 - 3] = i3;
                }
            }
        }
        if (i2 == 3) {
            this.b = true;
        }
        if (i2 == 5) {
            this.c = true;
        }
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public GF2nPolynomialField(int i, SecureRandom secureRandom, boolean z) {
        super(secureRandom);
        this.b = false;
        this.c = false;
        this.e = new int[3];
        if (i < 3) {
            throw new IllegalArgumentException("k must be at least 3");
        }
        this.mDegree = i;
        if (z) {
            computeFieldPolynomial();
        } else {
            computeFieldPolynomial2();
        }
        a();
        this.fields = new java.util.Vector();
        this.matrices = new java.util.Vector();
    }

    public final void a() {
        int i = this.mDegree;
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i - 1];
        this.f15372a = new GF2Polynomial[i];
        int i2 = 0;
        while (true) {
            GF2Polynomial[] gF2PolynomialArr2 = this.f15372a;
            if (i2 >= gF2PolynomialArr2.length) {
                break;
            }
            gF2PolynomialArr2[i2] = new GF2Polynomial(this.mDegree, "ZERO");
            i2++;
        }
        for (int i3 = 0; i3 < this.mDegree - 1; i3++) {
            gF2PolynomialArr[i3] = new GF2Polynomial(1, "ONE").shiftLeft(this.mDegree + i3).remainder(this.fieldPolynomial);
        }
        for (int i4 = 1; i4 <= Math.abs(this.mDegree >> 1); i4++) {
            int i5 = 1;
            while (true) {
                int i6 = this.mDegree;
                if (i5 <= i6) {
                    if (gF2PolynomialArr[i6 - (i4 << 1)].testBit(i6 - i5)) {
                        this.f15372a[i5 - 1].setBit(this.mDegree - i4);
                    }
                    i5++;
                }
            }
        }
        int abs = Math.abs(this.mDegree >> 1) + 1;
        while (true) {
            int i7 = this.mDegree;
            if (abs > i7) {
                return;
            }
            this.f15372a[((abs << 1) - i7) - 1].setBit(i7 - abs);
            abs++;
        }
    }

    public final boolean b() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree + 1);
        this.fieldPolynomial = gF2Polynomial;
        gF2Polynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        boolean z = false;
        int i = 1;
        while (i <= this.mDegree - 3 && !z) {
            this.fieldPolynomial.setBit(i);
            int i2 = i + 1;
            int i3 = i2;
            while (i3 <= this.mDegree - 2 && !z) {
                this.fieldPolynomial.setBit(i3);
                int i4 = i3 + 1;
                for (int i5 = i4; i5 <= this.mDegree - 1 && !z; i5++) {
                    this.fieldPolynomial.setBit(i5);
                    if (((((this.mDegree & 1) != 0) | ((i & 1) != 0) | ((i3 & 1) != 0)) || ((i5 & 1) != 0)) && (z = this.fieldPolynomial.isIrreducible())) {
                        this.c = true;
                        int[] iArr = this.e;
                        iArr[0] = i;
                        iArr[1] = i3;
                        iArr[2] = i5;
                        return z;
                    }
                    this.fieldPolynomial.resetBit(i5);
                }
                this.fieldPolynomial.resetBit(i3);
                i3 = i4;
            }
            this.fieldPolynomial.resetBit(i);
            i = i2;
        }
        return z;
    }

    public final boolean c() {
        this.fieldPolynomial = new GF2Polynomial(this.mDegree + 1);
        do {
            this.fieldPolynomial.randomize();
            this.fieldPolynomial.setBit(this.mDegree);
            this.fieldPolynomial.setBit(0);
        } while (!this.fieldPolynomial.isIrreducible());
        return true;
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    public void computeCOBMatrix(GF2nField gF2nField) {
        GF2nElement randomRoot;
        GF2nElement[] gF2nElementArr;
        int i;
        int i2 = this.mDegree;
        if (i2 != gF2nField.mDegree) {
            throw new IllegalArgumentException("GF2nPolynomialField.computeCOBMatrix: B1 has a different degree and thus cannot be coverted to!");
        }
        boolean z = gF2nField instanceof GF2nONBField;
        if (z) {
            gF2nField.computeCOBMatrix(this);
            return;
        }
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[i2];
        for (int i3 = 0; i3 < this.mDegree; i3++) {
            gF2PolynomialArr[i3] = new GF2Polynomial(this.mDegree);
        }
        do {
            randomRoot = gF2nField.getRandomRoot(this.fieldPolynomial);
        } while (randomRoot.isZero());
        if (randomRoot instanceof GF2nONBElement) {
            int i4 = this.mDegree;
            gF2nElementArr = new GF2nONBElement[i4];
            gF2nElementArr[i4 - 1] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
        } else {
            int i5 = this.mDegree;
            gF2nElementArr = new GF2nPolynomialElement[i5];
            gF2nElementArr[i5 - 1] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
        }
        int i6 = this.mDegree;
        gF2nElementArr[i6 - 2] = randomRoot;
        for (int i7 = i6 - 3; i7 >= 0; i7--) {
            gF2nElementArr[i7] = (GF2nElement) gF2nElementArr[i7 + 1].multiply(randomRoot);
        }
        if (z) {
            for (int i8 = 0; i8 < this.mDegree; i8++) {
                int i9 = 0;
                while (true) {
                    if (i9 < this.mDegree) {
                        if (gF2nElementArr[i8].b((i - i9) - 1)) {
                            int i10 = this.mDegree;
                            gF2PolynomialArr[(i10 - i9) - 1].setBit((i10 - i8) - 1);
                        }
                        i9++;
                    }
                }
            }
        } else {
            for (int i11 = 0; i11 < this.mDegree; i11++) {
                for (int i12 = 0; i12 < this.mDegree; i12++) {
                    if (gF2nElementArr[i11].b(i12)) {
                        int i13 = this.mDegree;
                        gF2PolynomialArr[(i13 - i12) - 1].setBit((i13 - i11) - 1);
                    }
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
        if (d() || b()) {
            return;
        }
        c();
    }

    public void computeFieldPolynomial2() {
        if (d() || b()) {
            return;
        }
        c();
    }

    public final boolean d() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree + 1);
        this.fieldPolynomial = gF2Polynomial;
        boolean z = false;
        gF2Polynomial.setBit(0);
        this.fieldPolynomial.setBit(this.mDegree);
        for (int i = 1; i < this.mDegree && !z; i++) {
            this.fieldPolynomial.setBit(i);
            boolean isIrreducible = this.fieldPolynomial.isIrreducible();
            if (isIrreducible) {
                this.b = true;
                this.d = i;
                return isIrreducible;
            }
            this.fieldPolynomial.resetBit(i);
            z = this.fieldPolynomial.isIrreducible();
        }
        return z;
    }

    public int[] getPc() throws RuntimeException {
        if (this.c) {
            int[] iArr = new int[3];
            System.arraycopy(this.e, 0, iArr, 0, 3);
            return iArr;
        }
        throw new RuntimeException();
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GF2nField
    public GF2nElement getRandomRoot(GF2Polynomial gF2Polynomial) {
        GF2nPolynomial gcd;
        int degree;
        int degree2;
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(gF2Polynomial, this);
        while (gF2nPolynomial.getDegree() > 1) {
            while (true) {
                GF2nPolynomialElement gF2nPolynomialElement = new GF2nPolynomialElement(this, this.random);
                GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(2, GF2nPolynomialElement.ZERO(this));
                gF2nPolynomial2.set(1, gF2nPolynomialElement);
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

    public GF2Polynomial getSquaringVector(int i) {
        return new GF2Polynomial(this.f15372a[i]);
    }

    public int getTc() throws RuntimeException {
        if (this.b) {
            return this.d;
        }
        throw new RuntimeException();
    }

    public boolean isPentanomial() {
        return this.c;
    }

    public boolean isTrinomial() {
        return this.b;
    }
}
