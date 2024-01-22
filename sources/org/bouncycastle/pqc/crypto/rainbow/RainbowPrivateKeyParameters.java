package org.bouncycastle.pqc.crypto.rainbow;
/* loaded from: classes13.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    public short[][] j;
    public short[] k;
    public short[][] l;
    public short[] m;
    public int[] n;
    public Layer[] o;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        super(true, iArr[iArr.length - 1] - iArr[0]);
        this.j = sArr;
        this.k = sArr2;
        this.l = sArr3;
        this.m = sArr4;
        this.n = iArr;
        this.o = layerArr;
    }

    public short[] getB1() {
        return this.k;
    }

    public short[] getB2() {
        return this.m;
    }

    public short[][] getInvA1() {
        return this.j;
    }

    public short[][] getInvA2() {
        return this.l;
    }

    public Layer[] getLayers() {
        return this.o;
    }

    public int[] getVi() {
        return this.n;
    }
}
