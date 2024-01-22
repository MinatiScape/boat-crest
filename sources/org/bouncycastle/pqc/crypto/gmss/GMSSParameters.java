package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class GMSSParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f15289a;
    public int[] b;
    public int[] c;
    public int[] d;

    public GMSSParameters(int i) throws IllegalArgumentException {
        if (i <= 10) {
            a(1, new int[]{10}, new int[]{3}, new int[]{2});
        } else if (i <= 20) {
            a(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2});
        } else {
            a(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2});
        }
    }

    public GMSSParameters(int i, int[] iArr, int[] iArr2, int[] iArr3) throws IllegalArgumentException {
        a(i, iArr, iArr2, iArr3);
    }

    public final void a(int i, int[] iArr, int[] iArr2, int[] iArr3) throws IllegalArgumentException {
        String str;
        boolean z;
        this.f15289a = i;
        if (i == iArr2.length && i == iArr.length && i == iArr3.length) {
            z = true;
            str = "";
        } else {
            str = "Unexpected parameterset format";
            z = false;
        }
        for (int i2 = 0; i2 < this.f15289a; i2++) {
            if (iArr3[i2] < 2 || (iArr[i2] - iArr3[i2]) % 2 != 0) {
                str = "Wrong parameter K (K >= 2 and H-K even required)!";
                z = false;
            }
            if (iArr[i2] < 4 || iArr2[i2] < 2) {
                str = "Wrong parameter H or w (H > 3 and w > 1 required)!";
                z = false;
            }
        }
        if (!z) {
            throw new IllegalArgumentException(str);
        }
        this.b = Arrays.clone(iArr);
        this.c = Arrays.clone(iArr2);
        this.d = Arrays.clone(iArr3);
    }

    public int[] getHeightOfTrees() {
        return Arrays.clone(this.b);
    }

    public int[] getK() {
        return Arrays.clone(this.d);
    }

    public int getNumOfLayers() {
        return this.f15289a;
    }

    public int[] getWinternitzParameter() {
        return Arrays.clone(this.c);
    }
}
