package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final f f8673a;

    static {
        f iVar;
        if ((b.J() && b.K()) && !t5.b()) {
            iVar = new k();
        } else {
            iVar = new i();
        }
        f8673a = iVar;
    }

    public static int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return f8673a.b(charSequence, bArr, i, i2);
    }

    public static int b(int i, byte[] bArr, int i2, int i3) {
        return f8673a.a(i, bArr, i2, i3);
    }

    public static int c(CharSequence charSequence) {
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
                                throw new h(i2, length2);
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

    public static int d(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int e(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static boolean h(byte[] bArr, int i, int i2) {
        return f8673a.c(bArr, i, i2);
    }

    public static int i(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 != 0) {
            if (i3 != 1) {
                if (i3 == 2) {
                    return d(b, bArr[i], bArr[i + 1]);
                }
                throw new AssertionError();
            }
            return m(b, bArr[i]);
        }
        return e(b);
    }

    public static String j(byte[] bArr, int i, int i2) throws zzxk {
        return f8673a.d(bArr, i, i2);
    }

    public static boolean l(byte[] bArr) {
        return f8673a.c(bArr, 0, bArr.length);
    }

    public static int m(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }
}
