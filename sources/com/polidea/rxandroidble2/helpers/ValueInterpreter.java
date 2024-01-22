package com.polidea.rxandroidble2.helpers;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import com.polidea.rxandroidble2.internal.RxBleLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class ValueInterpreter {
    public static final int FORMAT_FLOAT = 52;
    public static final int FORMAT_SFLOAT = 50;
    public static final int FORMAT_SINT16 = 34;
    public static final int FORMAT_SINT32 = 36;
    public static final int FORMAT_SINT8 = 33;
    public static final int FORMAT_UINT16 = 18;
    public static final int FORMAT_UINT32 = 20;
    public static final int FORMAT_UINT8 = 17;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface FloatFormatType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface IntFormatType {
    }

    public static float a(byte b, byte b2) {
        return (float) (g(d(b) + ((d(b2) & 15) << 8), 12) * Math.pow(10.0d, g(d(b2) >> 4, 4)));
    }

    public static float b(byte b, byte b2, byte b3, byte b4) {
        return (float) (g(d(b) + (d(b2) << 8) + (d(b3) << 16), 24) * Math.pow(10.0d, b4));
    }

    public static int c(int i) {
        return i & 15;
    }

    public static int d(byte b) {
        return b & 255;
    }

    public static int e(byte b, byte b2) {
        return d(b) + (d(b2) << 8);
    }

    public static int f(byte b, byte b2, byte b3, byte b4) {
        return d(b) + (d(b2) << 8) + (d(b3) << 16) + (d(b4) << 24);
    }

    public static int g(int i, int i2) {
        int i3 = 1 << (i2 - 1);
        return (i & i3) != 0 ? (i3 - (i & (i3 - 1))) * (-1) : i;
    }

    public static Float getFloatValue(@NonNull byte[] bArr, int i, @IntRange(from = 0) int i2) {
        if (c(i) + i2 > bArr.length) {
            RxBleLog.w("Float formatType (0x%x) is longer than remaining bytes (%d) - returning null", Integer.valueOf(i), Integer.valueOf(bArr.length - i2));
            return null;
        } else if (i != 50) {
            if (i != 52) {
                RxBleLog.w("Passed an invalid float formatType (0x%x) - returning null", Integer.valueOf(i));
                return null;
            }
            return Float.valueOf(b(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]));
        } else {
            return Float.valueOf(a(bArr[i2], bArr[i2 + 1]));
        }
    }

    public static Integer getIntValue(@NonNull byte[] bArr, int i, @IntRange(from = 0) int i2) {
        if (c(i) + i2 > bArr.length) {
            RxBleLog.w("Int formatType (0x%x) is longer than remaining bytes (%d) - returning null", Integer.valueOf(i), Integer.valueOf(bArr.length - i2));
            return null;
        } else if (i != 17) {
            if (i != 18) {
                if (i != 20) {
                    if (i != 36) {
                        if (i != 33) {
                            if (i != 34) {
                                RxBleLog.w("Passed an invalid integer formatType (0x%x) - returning null", Integer.valueOf(i));
                                return null;
                            }
                            return Integer.valueOf(g(e(bArr[i2], bArr[i2 + 1]), 16));
                        }
                        return Integer.valueOf(g(d(bArr[i2]), 8));
                    }
                    return Integer.valueOf(g(f(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]), 32));
                }
                return Integer.valueOf(f(bArr[i2], bArr[i2 + 1], bArr[i2 + 2], bArr[i2 + 3]));
            }
            return Integer.valueOf(e(bArr[i2], bArr[i2 + 1]));
        } else {
            return Integer.valueOf(d(bArr[i2]));
        }
    }

    public static String getStringValue(@NonNull byte[] bArr, @IntRange(from = 0) int i) {
        if (i > bArr.length) {
            RxBleLog.w("Passed offset that exceeds the length of the byte array - returning null", new Object[0]);
            return null;
        }
        byte[] bArr2 = new byte[bArr.length - i];
        for (int i2 = 0; i2 != bArr.length - i; i2++) {
            bArr2[i2] = bArr[i + i2];
        }
        return new String(bArr2);
    }
}
