package org.bouncycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
/* loaded from: classes13.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;

    /* renamed from: a  reason: collision with root package name */
    public int f15357a;
    public int b;
    public int c;
    public int d;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f15357a = 0;
        this.c = 1;
        while (true) {
            int i2 = this.c;
            if (i2 >= i) {
                int i3 = i2 >>> 1;
                this.b = i3;
                int i4 = this.f15357a;
                this.b = i3 / i4;
                this.d = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                return;
            }
            this.c = i2 << 1;
            this.f15357a++;
        }
    }

    public McElieceKeyGenParameterSpec(int i, int i2) throws InvalidParameterException {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f15357a = i;
        int i3 = 1 << i;
        this.c = i3;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.b = i2;
        this.d = PolynomialRingGF2.getIrreduciblePolynomial(i);
    }

    public McElieceKeyGenParameterSpec(int i, int i2, int i3) {
        this.f15357a = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int i4 = 1 << i;
        this.c = i4;
        this.b = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i4) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i3) != i || !PolynomialRingGF2.isIrreducible(i3)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.d = i3;
    }

    public int getFieldPoly() {
        return this.d;
    }

    public int getM() {
        return this.f15357a;
    }

    public int getN() {
        return this.c;
    }

    public int getT() {
        return this.b;
    }
}
