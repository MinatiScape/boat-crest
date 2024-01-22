package com.crrepa.g0;

import android.graphics.Bitmap;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class b extends com.crrepa.a0.a {
    @Override // com.crrepa.a0.a
    public byte[] b(boolean z, Bitmap[] bitmapArr) {
        byte[] bArr = null;
        if (bitmapArr != null && bitmapArr.length >= 1) {
            ArrayList<byte[]> arrayList = new ArrayList();
            int i = 0;
            for (Bitmap bitmap : bitmapArr) {
                if (bitmap != null) {
                    byte[] z2 = z(bitmap);
                    i += z2.length;
                    arrayList.add(z2);
                }
            }
            if (i == 0) {
                return null;
            }
            bArr = new byte[i];
            int i2 = 0;
            for (byte[] bArr2 : arrayList) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i2, length);
                i2 = length;
            }
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] z(android.graphics.Bitmap r17) {
        /*
            Method dump skipped, instructions count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.g0.b.z(android.graphics.Bitmap):byte[]");
    }
}
