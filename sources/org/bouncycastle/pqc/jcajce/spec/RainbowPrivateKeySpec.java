package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
/* loaded from: classes13.dex */
public class RainbowPrivateKeySpec implements KeySpec {
    public short[][] h;
    public short[] i;
    public short[][] j;
    public short[] k;
    public int[] l;
    public Layer[] m;

    public RainbowPrivateKeySpec(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        this.h = sArr;
        this.i = sArr2;
        this.j = sArr3;
        this.k = sArr4;
        this.l = iArr;
        this.m = layerArr;
    }

    public short[] getB1() {
        return this.i;
    }

    public short[] getB2() {
        return this.k;
    }

    public short[][] getInvA1() {
        return this.h;
    }

    public short[][] getInvA2() {
        return this.j;
    }

    public Layer[] getLayers() {
        return this.m;
    }

    public int[] getVi() {
        return this.l;
    }
}
