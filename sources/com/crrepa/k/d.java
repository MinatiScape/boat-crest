package com.crrepa.k;

import com.crrepa.i0.e;
/* loaded from: classes9.dex */
public class d extends a {
    @Override // com.crrepa.k.a
    public int[] a(byte[] bArr) {
        if (e.e(bArr) || bArr.length % 4 != 0) {
            return null;
        }
        int length = bArr.length / 4;
        int[] iArr = new int[length];
        byte[] bArr2 = new byte[4];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length && length > i; i2 += 4) {
            System.arraycopy(bArr, i2, bArr2, 0, 4);
            int d = (int) e.d(bArr2);
            com.crrepa.i0.c.c("ecg: " + d);
            iArr[i] = d;
            i++;
        }
        return iArr;
    }
}
