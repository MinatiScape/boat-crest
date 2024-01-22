package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2m;
/* loaded from: classes13.dex */
public class McEliecePrivateKeyParameters extends McElieceKeyParameters {
    public int j;
    public int k;
    public GF2mField l;
    public PolynomialGF2mSmallM m;
    public GF2Matrix n;
    public Permutation o;
    public Permutation p;
    public GF2Matrix q;
    public PolynomialGF2mSmallM[] r;

    public McEliecePrivateKeyParameters(int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix) {
        super(true, null);
        this.k = i2;
        this.j = i;
        this.l = gF2mField;
        this.m = polynomialGF2mSmallM;
        this.n = gF2Matrix;
        this.o = permutation;
        this.p = permutation2;
        this.q = GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM);
        this.r = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).getSquareRootMatrix();
    }

    public McEliecePrivateKeyParameters(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[][] bArr7) {
        super(true, null);
        this.j = i;
        this.k = i2;
        GF2mField gF2mField = new GF2mField(bArr);
        this.l = gF2mField;
        this.m = new PolynomialGF2mSmallM(gF2mField, bArr2);
        this.n = new GF2Matrix(bArr3);
        this.o = new Permutation(bArr4);
        this.p = new Permutation(bArr5);
        this.q = new GF2Matrix(bArr6);
        this.r = new PolynomialGF2mSmallM[bArr7.length];
        for (int i3 = 0; i3 < bArr7.length; i3++) {
            this.r[i3] = new PolynomialGF2mSmallM(this.l, bArr7[i3]);
        }
    }

    public GF2mField getField() {
        return this.l;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.m;
    }

    public GF2Matrix getH() {
        return this.q;
    }

    public int getK() {
        return this.k;
    }

    public int getN() {
        return this.j;
    }

    public Permutation getP1() {
        return this.o;
    }

    public Permutation getP2() {
        return this.p;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.r;
    }

    public GF2Matrix getSInv() {
        return this.n;
    }
}
