package com.google.android.gms.internal.auth;

import java.io.IOException;
/* loaded from: classes6.dex */
public final class n0 {
    public static int a(byte[] bArr, int i, m0 m0Var) throws zzfa {
        int j = j(bArr, i, m0Var);
        int i2 = m0Var.f8531a;
        if (i2 >= 0) {
            if (i2 <= bArr.length - j) {
                if (i2 == 0) {
                    m0Var.c = zzee.zzb;
                    return j;
                }
                m0Var.c = zzee.zzk(bArr, j, i2);
                return j + i2;
            }
            throw zzfa.zzf();
        }
        throw zzfa.zzc();
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int c(z1 z1Var, byte[] bArr, int i, int i2, int i3, m0 m0Var) throws IOException {
        r1 r1Var = (r1) z1Var;
        Object zzd = r1Var.zzd();
        int o = r1Var.o(zzd, bArr, i, i2, i3, m0Var);
        r1Var.zze(zzd);
        m0Var.c = zzd;
        return o;
    }

    public static int d(z1 z1Var, byte[] bArr, int i, int i2, m0 m0Var) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = k(i4, bArr, i3, m0Var);
            i4 = m0Var.f8531a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object zzd = z1Var.zzd();
            int i6 = i4 + i5;
            z1Var.f(zzd, bArr, i5, i6, m0Var);
            z1Var.zze(zzd);
            m0Var.c = zzd;
            return i6;
        }
        throw zzfa.zzf();
    }

    public static int e(z1 z1Var, int i, byte[] bArr, int i2, int i3, zzey zzeyVar, m0 m0Var) throws IOException {
        int d = d(z1Var, bArr, i2, i3, m0Var);
        zzeyVar.add(m0Var.c);
        while (d < i3) {
            int j = j(bArr, d, m0Var);
            if (i != m0Var.f8531a) {
                break;
            }
            d = d(z1Var, bArr, j, i3, m0Var);
            zzeyVar.add(m0Var.c);
        }
        return d;
    }

    public static int f(byte[] bArr, int i, zzey zzeyVar, m0 m0Var) throws IOException {
        e1 e1Var = (e1) zzeyVar;
        int j = j(bArr, i, m0Var);
        int i2 = m0Var.f8531a + j;
        while (j < i2) {
            j = j(bArr, j, m0Var);
            e1Var.a(m0Var.f8531a);
        }
        if (j == i2) {
            return j;
        }
        throw zzfa.zzf();
    }

    public static int g(byte[] bArr, int i, m0 m0Var) throws zzfa {
        int j = j(bArr, i, m0Var);
        int i2 = m0Var.f8531a;
        if (i2 >= 0) {
            if (i2 == 0) {
                m0Var.c = "";
                return j;
            }
            m0Var.c = new String(bArr, j, i2, zzez.f8562a);
            return j + i2;
        }
        throw zzfa.zzc();
    }

    public static int h(byte[] bArr, int i, m0 m0Var) throws zzfa {
        int j = j(bArr, i, m0Var);
        int i2 = m0Var.f8531a;
        if (i2 >= 0) {
            if (i2 == 0) {
                m0Var.c = "";
                return j;
            }
            m0Var.c = y2.b(bArr, j, i2);
            return j + i2;
        }
        throw zzfa.zzc();
    }

    public static int i(int i, byte[] bArr, int i2, int i3, zzgz zzgzVar, m0 m0Var) throws zzfa {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int m = m(bArr, i2, m0Var);
                zzgzVar.d(i, Long.valueOf(m0Var.b));
                return m;
            } else if (i4 == 1) {
                zzgzVar.d(i, Long.valueOf(n(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int j = j(bArr, i2, m0Var);
                int i5 = m0Var.f8531a;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - j) {
                        if (i5 == 0) {
                            zzgzVar.d(i, zzee.zzb);
                        } else {
                            zzgzVar.d(i, zzee.zzk(bArr, j, i5));
                        }
                        return j + i5;
                    }
                    throw zzfa.zzf();
                }
                throw zzfa.zzc();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzgzVar.d(i, Integer.valueOf(b(bArr, i2)));
                    return i2 + 4;
                }
                throw zzfa.zza();
            } else {
                int i6 = (i & (-8)) | 4;
                zzgz b = zzgz.b();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int j2 = j(bArr, i2, m0Var);
                    int i8 = m0Var.f8531a;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = j2;
                        break;
                    }
                    i7 = i8;
                    i2 = i(i8, bArr, j2, i3, b, m0Var);
                }
                if (i2 <= i3 && i7 == i6) {
                    zzgzVar.d(i, b);
                    return i2;
                }
                throw zzfa.zzd();
            }
        }
        throw zzfa.zza();
    }

    public static int j(byte[] bArr, int i, m0 m0Var) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            m0Var.f8531a = b;
            return i2;
        }
        return k(b, bArr, i2, m0Var);
    }

    public static int k(int i, byte[] bArr, int i2, m0 m0Var) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            m0Var.f8531a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            m0Var.f8531a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            m0Var.f8531a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            m0Var.f8531a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                m0Var.f8531a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int l(int i, byte[] bArr, int i2, int i3, zzey zzeyVar, m0 m0Var) {
        e1 e1Var = (e1) zzeyVar;
        int j = j(bArr, i2, m0Var);
        e1Var.a(m0Var.f8531a);
        while (j < i3) {
            int j2 = j(bArr, j, m0Var);
            if (i != m0Var.f8531a) {
                break;
            }
            j = j(bArr, j2, m0Var);
            e1Var.a(m0Var.f8531a);
        }
        return j;
    }

    public static int m(byte[] bArr, int i, m0 m0Var) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            m0Var.b = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (b & Byte.MAX_VALUE) << i4;
            b2 = bArr[i3];
            i3 = i5;
        }
        m0Var.b = j2;
        return i3;
    }

    public static long n(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }
}
