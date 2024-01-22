package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class l2 {

    /* renamed from: a  reason: collision with root package name */
    public static final i2 f9603a;
    public static final /* synthetic */ int b = 0;

    static {
        if (g2.C() && g2.D()) {
            int i = l.f9602a;
        }
        f9603a = new j2();
    }

    public static /* bridge */ /* synthetic */ int c(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte b2 = bArr[i - 1];
        if (i3 == 0) {
            if (b2 > -12) {
                return -1;
            }
            return b2;
        } else if (i3 != 1) {
            if (i3 == 2) {
                return j(b2, bArr[i], bArr[i + 1]);
            }
            throw new AssertionError();
        } else {
            return i(b2, bArr[i]);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ff, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.l2.d(java.lang.CharSequence, byte[], int, int):int");
    }

    public static int e(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new k2(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i3 + 4294967296L));
    }

    public static int f(int i, byte[] bArr, int i2, int i3) {
        return f9603a.a(i, bArr, i2, i3);
    }

    public static boolean g(byte[] bArr) {
        return f9603a.b(bArr, 0, bArr.length);
    }

    public static boolean h(byte[] bArr, int i, int i2) {
        return f9603a.b(bArr, i, i2);
    }

    public static int i(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int j(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }
}
