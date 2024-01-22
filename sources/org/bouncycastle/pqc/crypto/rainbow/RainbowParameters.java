package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class RainbowParameters implements CipherParameters {
    public final int[] h;
    public int[] i;

    public RainbowParameters() {
        int[] iArr = {6, 12, 17, 22, 33};
        this.h = iArr;
        this.i = iArr;
    }

    public RainbowParameters(int[] iArr) {
        this.h = new int[]{6, 12, 17, 22, 33};
        this.i = iArr;
        try {
            a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a() throws Exception {
        int[] iArr;
        int i;
        int[] iArr2 = this.i;
        if (iArr2 == null) {
            throw new Exception("no layers defined.");
        }
        if (iArr2.length <= 1) {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int i2 = 0;
        do {
            iArr = this.i;
            if (i2 >= iArr.length - 1) {
                return;
            }
            i = iArr[i2];
            i2++;
        } while (i < iArr[i2]);
        throw new Exception("v[i] has to be smaller than v[i+1]");
    }

    public int getDocLength() {
        int[] iArr = this.i;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.i.length - 1;
    }

    public int[] getVi() {
        return this.i;
    }
}
