package com.crrepa.j;

import java.util.ArrayList;
/* loaded from: classes9.dex */
public class i {
    public static int a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return -1;
        }
        return bArr[0];
    }

    public static int[] b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        int length = (bArr.length - 1) / 4;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            System.arraycopy(bArr, (4 * i) + 1, bArr2, 0, 4);
            String stringBuffer = new StringBuffer(Integer.toBinaryString(com.crrepa.i0.e.b(bArr2))).reverse().toString();
            com.crrepa.i0.c.c("parseSupportLanguageArray: " + stringBuffer);
            int i2 = 0;
            while (i2 < stringBuffer.length()) {
                int i3 = i2 + 1;
                if (Integer.parseInt(stringBuffer.substring(i2, i3)) > 0) {
                    arrayList.add(Integer.valueOf((i * 32) + i2));
                }
                i2 = i3;
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            iArr[i4] = ((Integer) arrayList.get(i4)).intValue();
        }
        return iArr;
    }
}
