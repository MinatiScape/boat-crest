package com.crrepa.k;

import com.crrepa.i0.e;
/* loaded from: classes9.dex */
public class c extends a {
    @Override // com.crrepa.k.a
    public int[] a(byte[] bArr) {
        if (e.e(bArr) || bArr.length % 2 != 0) {
            return null;
        }
        int length = bArr.length / 2;
        int[] iArr = new int[length];
        byte[] bArr2 = new byte[2];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length && length > i; i2 += 2) {
            System.arraycopy(bArr, i2, bArr2, 0, 2);
            int a2 = e.a(bArr2[1], bArr2[0]);
            com.crrepa.i0.c.c("ecg: " + a2);
            iArr[i] = a2;
            i++;
        }
        return iArr;
    }
}
