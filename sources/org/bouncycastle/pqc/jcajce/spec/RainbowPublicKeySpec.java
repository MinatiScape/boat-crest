package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
/* loaded from: classes13.dex */
public class RainbowPublicKeySpec implements KeySpec {
    public short[][] h;
    public short[][] i;
    public short[] j;
    public int k;

    public RainbowPublicKeySpec(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.k = i;
        this.h = sArr;
        this.i = sArr2;
        this.j = sArr3;
    }

    public short[][] getCoeffQuadratic() {
        return this.h;
    }

    public short[] getCoeffScalar() {
        return this.j;
    }

    public short[][] getCoeffSingular() {
        return this.i;
    }

    public int getDocLength() {
        return this.k;
    }
}
