package com.google.zxing.pdf417.decoder;

import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import java.lang.reflect.Array;
/* loaded from: classes11.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    public static final float[][] f11858a = (float[][]) Array.newInstance(float.class, PDF417Common.SYMBOL_TABLE.length, 8);

    static {
        int i;
        int i2 = 0;
        while (true) {
            int[] iArr = PDF417Common.SYMBOL_TABLE;
            if (i2 >= iArr.length) {
                return;
            }
            int i3 = iArr[i2];
            int i4 = i3 & 1;
            int i5 = 0;
            while (i5 < 8) {
                float f = 0.0f;
                while (true) {
                    i = i3 & 1;
                    if (i == i4) {
                        f += 1.0f;
                        i3 >>= 1;
                    }
                }
                f11858a[i2][(8 - i5) - 1] = f / 17.0f;
                i5++;
                i4 = i;
            }
            i2++;
        }
    }

    public static int a(int[] iArr) {
        long j = 0;
        for (int i = 0; i < iArr.length; i++) {
            for (int i2 = 0; i2 < iArr[i]; i2++) {
                int i3 = 1;
                long j2 = j << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                j = j2 | i3;
            }
        }
        return (int) j;
    }

    public static int b(int[] iArr) {
        int sum = MathUtils.sum(iArr);
        float[] fArr = new float[8];
        if (sum > 1) {
            for (int i = 0; i < 8; i++) {
                fArr[i] = iArr[i] / sum;
            }
        }
        float f = Float.MAX_VALUE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            float[][] fArr2 = f11858a;
            if (i3 >= fArr2.length) {
                return i2;
            }
            float f2 = 0.0f;
            float[] fArr3 = fArr2[i3];
            for (int i4 = 0; i4 < 8; i4++) {
                float f3 = fArr3[i4] - fArr[i4];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i2 = PDF417Common.SYMBOL_TABLE[i3];
                f = f2;
            }
            i3++;
        }
    }

    public static int c(int[] iArr) {
        int a2 = a(iArr);
        if (PDF417Common.getCodeword(a2) == -1) {
            return -1;
        }
        return a2;
    }

    public static int d(int[] iArr) {
        int c = c(e(iArr));
        return c != -1 ? c : b(iArr);
    }

    public static int[] e(int[] iArr) {
        float sum = MathUtils.sum(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (iArr[i2] + i <= (sum / 34.0f) + ((i3 * sum) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }
}