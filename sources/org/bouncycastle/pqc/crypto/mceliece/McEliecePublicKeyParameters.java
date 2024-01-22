package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes13.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {
    public int j;
    public int k;
    public GF2Matrix l;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.j = i;
        this.k = i2;
        this.l = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.l;
    }

    public int getK() {
        return this.l.getNumRows();
    }

    public int getN() {
        return this.j;
    }

    public int getT() {
        return this.k;
    }
}
