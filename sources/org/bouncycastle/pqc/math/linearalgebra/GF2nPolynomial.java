package org.bouncycastle.pqc.math.linearalgebra;
/* loaded from: classes13.dex */
public class GF2nPolynomial {

    /* renamed from: a  reason: collision with root package name */
    public GF2nElement[] f15370a;
    public int b;

    public GF2nPolynomial(int i) {
        this.b = i;
        this.f15370a = new GF2nElement[i];
    }

    public GF2nPolynomial(int i, GF2nElement gF2nElement) {
        this.b = i;
        this.f15370a = new GF2nElement[i];
        for (int i2 = 0; i2 < this.b; i2++) {
            this.f15370a[i2] = (GF2nElement) gF2nElement.clone();
        }
    }

    public GF2nPolynomial(GF2Polynomial gF2Polynomial, GF2nField gF2nField) {
        int degree = gF2nField.getDegree() + 1;
        this.b = degree;
        this.f15370a = new GF2nElement[degree];
        int i = 0;
        if (gF2nField instanceof GF2nONBField) {
            while (i < this.b) {
                if (gF2Polynomial.testBit(i)) {
                    this.f15370a[i] = GF2nONBElement.ONE((GF2nONBField) gF2nField);
                } else {
                    this.f15370a[i] = GF2nONBElement.ZERO((GF2nONBField) gF2nField);
                }
                i++;
            }
        } else if (!(gF2nField instanceof GF2nPolynomialField)) {
            throw new IllegalArgumentException("PolynomialGF2n(Bitstring, GF2nField): B1 must be an instance of GF2nONBField or GF2nPolynomialField!");
        } else {
            while (i < this.b) {
                if (gF2Polynomial.testBit(i)) {
                    this.f15370a[i] = GF2nPolynomialElement.ONE((GF2nPolynomialField) gF2nField);
                } else {
                    this.f15370a[i] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) gF2nField);
                }
                i++;
            }
        }
    }

    public GF2nPolynomial(GF2nPolynomial gF2nPolynomial) {
        int i = gF2nPolynomial.b;
        this.f15370a = new GF2nElement[i];
        this.b = i;
        for (int i2 = 0; i2 < this.b; i2++) {
            this.f15370a[i2] = (GF2nElement) gF2nPolynomial.f15370a[i2].clone();
        }
    }

    public final GF2nPolynomial add(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        int i = 0;
        if (size() >= gF2nPolynomial.size()) {
            gF2nPolynomial2 = new GF2nPolynomial(size());
            while (i < gF2nPolynomial.size()) {
                gF2nPolynomial2.f15370a[i] = (GF2nElement) this.f15370a[i].add(gF2nPolynomial.f15370a[i]);
                i++;
            }
            while (i < size()) {
                gF2nPolynomial2.f15370a[i] = this.f15370a[i];
                i++;
            }
        } else {
            gF2nPolynomial2 = new GF2nPolynomial(gF2nPolynomial.size());
            while (i < size()) {
                gF2nPolynomial2.f15370a[i] = (GF2nElement) this.f15370a[i].add(gF2nPolynomial.f15370a[i]);
                i++;
            }
            while (i < gF2nPolynomial.size()) {
                gF2nPolynomial2.f15370a[i] = gF2nPolynomial.f15370a[i];
                i++;
            }
        }
        return gF2nPolynomial2;
    }

    public final void assignZeroToElements() {
        for (int i = 0; i < this.b; i++) {
            this.f15370a[i].a();
        }
    }

    public final GF2nElement at(int i) {
        return this.f15370a[i];
    }

    public final GF2nPolynomial[] divide(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2;
        GF2nPolynomial[] gF2nPolynomialArr = new GF2nPolynomial[2];
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(this);
        gF2nPolynomial3.shrink();
        int degree = gF2nPolynomial.getDegree();
        GF2nElement gF2nElement = (GF2nElement) gF2nPolynomial.f15370a[degree].invert();
        if (gF2nPolynomial3.getDegree() < degree) {
            gF2nPolynomialArr[0] = new GF2nPolynomial(this);
            gF2nPolynomialArr[0].assignZeroToElements();
            gF2nPolynomialArr[0].shrink();
            gF2nPolynomialArr[1] = new GF2nPolynomial(this);
            gF2nPolynomial2 = gF2nPolynomialArr[1];
        } else {
            gF2nPolynomialArr[0] = new GF2nPolynomial(this);
            gF2nPolynomialArr[0].assignZeroToElements();
            while (true) {
                int degree2 = gF2nPolynomial3.getDegree() - degree;
                if (degree2 < 0) {
                    break;
                }
                GF2nElement gF2nElement2 = (GF2nElement) gF2nPolynomial3.f15370a[gF2nPolynomial3.getDegree()].multiply(gF2nElement);
                GF2nPolynomial scalarMultiply = gF2nPolynomial.scalarMultiply(gF2nElement2);
                scalarMultiply.shiftThisLeft(degree2);
                gF2nPolynomial3 = gF2nPolynomial3.add(scalarMultiply);
                gF2nPolynomial3.shrink();
                gF2nPolynomialArr[0].f15370a[degree2] = (GF2nElement) gF2nElement2.clone();
            }
            gF2nPolynomialArr[1] = gF2nPolynomial3;
            gF2nPolynomial2 = gF2nPolynomialArr[0];
        }
        gF2nPolynomial2.shrink();
        return gF2nPolynomialArr;
    }

    public final void enlarge(int i) {
        int i2 = this.b;
        if (i <= i2) {
            return;
        }
        GF2nElement[] gF2nElementArr = new GF2nElement[i];
        System.arraycopy(this.f15370a, 0, gF2nElementArr, 0, i2);
        GF2nField field = this.f15370a[0].getField();
        GF2nElement[] gF2nElementArr2 = this.f15370a;
        if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
            for (int i3 = this.b; i3 < i; i3++) {
                gF2nElementArr[i3] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
            }
        } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
            for (int i4 = this.b; i4 < i; i4++) {
                gF2nElementArr[i4] = GF2nONBElement.ZERO((GF2nONBField) field);
            }
        }
        this.b = i;
        this.f15370a = gF2nElementArr;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial) obj;
        if (getDegree() != gF2nPolynomial.getDegree()) {
            return false;
        }
        for (int i = 0; i < this.b; i++) {
            if (!this.f15370a[i].equals(gF2nPolynomial.f15370a[i])) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial gcd(GF2nPolynomial gF2nPolynomial) {
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial);
        gF2nPolynomial2.shrink();
        gF2nPolynomial3.shrink();
        GF2nPolynomial gF2nPolynomial4 = gF2nPolynomial2;
        GF2nPolynomial gF2nPolynomial5 = gF2nPolynomial3;
        while (!gF2nPolynomial5.isZero()) {
            GF2nPolynomial gF2nPolynomial6 = gF2nPolynomial5;
            gF2nPolynomial5 = gF2nPolynomial4.remainder(gF2nPolynomial5);
            gF2nPolynomial4 = gF2nPolynomial6;
        }
        return gF2nPolynomial4.scalarMultiply((GF2nElement) gF2nPolynomial4.f15370a[gF2nPolynomial4.getDegree()].invert());
    }

    public final int getDegree() {
        for (int i = this.b - 1; i >= 0; i--) {
            if (!this.f15370a[i].isZero()) {
                return i;
            }
        }
        return -1;
    }

    public int hashCode() {
        return getDegree() + this.f15370a.hashCode();
    }

    public final boolean isZero() {
        for (int i = 0; i < this.b; i++) {
            GF2nElement[] gF2nElementArr = this.f15370a;
            if (gF2nElementArr[i] != null && !gF2nElementArr[i].isZero()) {
                return false;
            }
        }
        return true;
    }

    public final GF2nPolynomial multiply(GF2nPolynomial gF2nPolynomial) {
        int size;
        if (size() == gF2nPolynomial.size()) {
            GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial((size << 1) - 1);
            for (int i = 0; i < size(); i++) {
                for (int i2 = 0; i2 < gF2nPolynomial.size(); i2++) {
                    GF2nElement[] gF2nElementArr = gF2nPolynomial2.f15370a;
                    int i3 = i + i2;
                    if (gF2nElementArr[i3] == null) {
                        gF2nElementArr[i3] = (GF2nElement) this.f15370a[i].multiply(gF2nPolynomial.f15370a[i2]);
                    } else {
                        gF2nElementArr[i3] = (GF2nElement) gF2nElementArr[i3].add(this.f15370a[i].multiply(gF2nPolynomial.f15370a[i2]));
                    }
                }
            }
            return gF2nPolynomial2;
        }
        throw new IllegalArgumentException("PolynomialGF2n.multiply: this and b must have the same size!");
    }

    public final GF2nPolynomial multiplyAndReduce(GF2nPolynomial gF2nPolynomial, GF2nPolynomial gF2nPolynomial2) {
        return multiply(gF2nPolynomial).reduce(gF2nPolynomial2);
    }

    public final GF2nPolynomial quotient(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return divide(gF2nPolynomial)[0];
    }

    public final GF2nPolynomial reduce(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return remainder(gF2nPolynomial);
    }

    public final GF2nPolynomial remainder(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return divide(gF2nPolynomial)[1];
    }

    public final GF2nPolynomial scalarMultiply(GF2nElement gF2nElement) {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(size());
        for (int i = 0; i < size(); i++) {
            gF2nPolynomial.f15370a[i] = (GF2nElement) this.f15370a[i].multiply(gF2nElement);
        }
        return gF2nPolynomial;
    }

    public final void set(int i, GF2nElement gF2nElement) {
        if (!(gF2nElement instanceof GF2nPolynomialElement) && !(gF2nElement instanceof GF2nONBElement)) {
            throw new IllegalArgumentException("PolynomialGF2n.set f must be an instance of either GF2nPolynomialElement or GF2nONBElement!");
        }
        this.f15370a[i] = (GF2nElement) gF2nElement.clone();
    }

    public final GF2nPolynomial shiftLeft(int i) {
        if (i <= 0) {
            return new GF2nPolynomial(this);
        }
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.b + i, this.f15370a[0]);
        gF2nPolynomial.assignZeroToElements();
        for (int i2 = 0; i2 < this.b; i2++) {
            gF2nPolynomial.f15370a[i2 + i] = this.f15370a[i2];
        }
        return gF2nPolynomial;
    }

    public final void shiftThisLeft(int i) {
        if (i > 0) {
            int i2 = this.b;
            GF2nField field = this.f15370a[0].getField();
            enlarge(this.b + i);
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                GF2nElement[] gF2nElementArr = this.f15370a;
                gF2nElementArr[i3 + i] = gF2nElementArr[i3];
            }
            GF2nElement[] gF2nElementArr2 = this.f15370a;
            if (gF2nElementArr2[0] instanceof GF2nPolynomialElement) {
                for (int i4 = i - 1; i4 >= 0; i4--) {
                    this.f15370a[i4] = GF2nPolynomialElement.ZERO((GF2nPolynomialField) field);
                }
            } else if (gF2nElementArr2[0] instanceof GF2nONBElement) {
                for (int i5 = i - 1; i5 >= 0; i5--) {
                    this.f15370a[i5] = GF2nONBElement.ZERO((GF2nONBField) field);
                }
            }
        }
    }

    public final void shrink() {
        int i = this.b;
        while (true) {
            i--;
            if (!this.f15370a[i].isZero() || i <= 0) {
                break;
            }
        }
        int i2 = i + 1;
        if (i2 < this.b) {
            GF2nElement[] gF2nElementArr = new GF2nElement[i2];
            System.arraycopy(this.f15370a, 0, gF2nElementArr, 0, i2);
            this.f15370a = gF2nElementArr;
            this.b = i2;
        }
    }

    public final int size() {
        return this.b;
    }
}
