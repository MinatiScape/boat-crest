package org.bouncycastle.pqc.crypto.rainbow;
/* loaded from: classes13.dex */
public class RainbowPublicKeyParameters extends RainbowKeyParameters {
    public short[][] j;
    public short[][] k;
    public short[] l;

    public RainbowPublicKeyParameters(int i, short[][] sArr, short[][] sArr2, short[] sArr3) {
        super(false, i);
        this.j = sArr;
        this.k = sArr2;
        this.l = sArr3;
    }

    public short[][] getCoeffQuadratic() {
        return this.j;
    }

    public short[] getCoeffScalar() {
        return this.l;
    }

    public short[][] getCoeffSingular() {
        return this.k;
    }
}
