package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class u4 {

    /* renamed from: a  reason: collision with root package name */
    public static final t4 f8854a;

    static {
        t4 w4Var;
        if ((s4.q() && s4.r()) && !w1.a()) {
            w4Var = new y4();
        } else {
            w4Var = new w4();
        }
        f8854a = w4Var;
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
                                throw new v4(i2, length2);
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

    public static int b(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static int d(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f8854a.a(charSequence, bArr, i, i2);
    }

    public static boolean e(byte[] bArr) {
        return f8854a.c(bArr, 0, bArr.length);
    }

    public static boolean f(byte[] bArr, int i, int i2) {
        return f8854a.c(bArr, i, i2);
    }

    public static int g(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int h(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 != 1) {
                if (i3 == 2) {
                    return g(b, bArr[i], bArr[i + 1]);
                }
                throw new AssertionError();
            }
            return k(b, bArr[i]);
        }
        return b(b);
    }

    public static int k(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }
}
