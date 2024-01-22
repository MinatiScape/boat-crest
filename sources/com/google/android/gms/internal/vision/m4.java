package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class m4 {

    /* renamed from: a  reason: collision with root package name */
    public static final n4 f9993a;

    static {
        n4 q4Var;
        if ((j4.t() && j4.u()) && !e1.a()) {
            q4Var = new s4();
        } else {
            q4Var = new q4();
        }
        f9993a = q4Var;
    }

    public static int a(CharSequence charSequence) {
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
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new p4(i2, length2);
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
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(i3 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    public static int b(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f9993a.b(charSequence, bArr, i, i2);
    }

    public static int c(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static int e(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean g(byte[] bArr, int i, int i2) {
        return f9993a.c(bArr, i, i2);
    }

    public static int h(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 != 1) {
                if (i3 == 2) {
                    return e(b, bArr[i], bArr[i + 1]);
                }
                throw new AssertionError();
            }
            return l(b, bArr[i]);
        }
        return c(b);
    }

    public static boolean i(byte[] bArr) {
        return f9993a.c(bArr, 0, bArr.length);
    }

    public static String j(byte[] bArr, int i, int i2) throws zzhc {
        return f9993a.d(bArr, i, i2);
    }

    public static int l(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }
}
