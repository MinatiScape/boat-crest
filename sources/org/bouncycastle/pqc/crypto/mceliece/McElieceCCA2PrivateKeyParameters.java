package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2m;
/* loaded from: classes13.dex */
public class McElieceCCA2PrivateKeyParameters extends McElieceCCA2KeyParameters {
    public int j;
    public int k;
    public GF2mField l;
    public PolynomialGF2mSmallM m;
    public Permutation n;
    public GF2Matrix o;
    public PolynomialGF2mSmallM[] p;

    public McElieceCCA2PrivateKeyParameters(int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, GF2Matrix gF2Matrix, Permutation permutation, String str) {
        super(true, str);
        this.j = i;
        this.k = i2;
        this.l = gF2mField;
        this.m = polynomialGF2mSmallM;
        this.o = gF2Matrix;
        this.n = permutation;
        this.p = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).getSquareRootMatrix();
    }

    public McElieceCCA2PrivateKeyParameters(int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, String str) {
        this(i, i2, gF2mField, polynomialGF2mSmallM, GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM), permutation, str);
    }

    public GF2mField getField() {
        return this.l;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.m;
    }

    public GF2Matrix getH() {
        return this.o;
    }

    public int getK() {
        return this.k;
    }

    public int getN() {
        return this.j;
    }

    public Permutation getP() {
        return this.n;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.p;
    }

    public int getT() {
        return this.m.getDegree();
    }
}
