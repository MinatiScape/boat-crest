package com.google.android.gms.internal.vision;

import okio.Utf8;
/* loaded from: classes10.dex */
public final class o4 {
    public static void a(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) throws zzhc {
        if (!l(b2) && (((b << 28) + (b2 + com.htsmart.wristband2.a.a.a.J1)) >> 30) == 0 && !l(b3) && !l(b4)) {
            int i2 = ((b & 7) << 18) | ((b2 & 63) << 12) | ((b3 & 63) << 6) | (b4 & 63);
            cArr[i] = (char) ((i2 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
            cArr[i + 1] = (char) ((i2 & 1023) + Utf8.LOG_SURROGATE_HEADER);
            return;
        }
        throw zzhc.zzgt();
    }

    public static void b(byte b, byte b2, byte b3, char[] cArr, int i) throws zzhc {
        if (!l(b2) && ((b != -32 || b2 >= -96) && ((b != -19 || b2 < -96) && !l(b3)))) {
            cArr[i] = (char) (((b & 15) << 12) | ((b2 & 63) << 6) | (b3 & 63));
            return;
        }
        throw zzhc.zzgt();
    }

    public static void c(byte b, byte b2, char[] cArr, int i) throws zzhc {
        if (b >= -62 && !l(b2)) {
            cArr[i] = (char) (((b & 31) << 6) | (b2 & 63));
            return;
        }
        throw zzhc.zzgt();
    }

    public static void d(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    public static boolean i(byte b) {
        return b >= 0;
    }

    public static boolean j(byte b) {
        return b < -32;
    }

    public static boolean k(byte b) {
        return b < -16;
    }

    public static boolean l(byte b) {
        return b > -65;
    }
}
