package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes10.dex */
public final class d1 {
    public static int a(int i, byte[] bArr, int i2, int i3, f1 f1Var) throws zzhc {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzhc.zzgp();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = i(bArr, i2, f1Var);
                            i6 = f1Var.f9971a;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = a(i6, bArr, i2, i3, f1Var);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzhc.zzgs();
                        }
                        return i2;
                    }
                    return i(bArr, i2, f1Var) + f1Var.f9971a;
                }
                return i2 + 8;
            }
            return k(bArr, i2, f1Var);
        }
        throw zzhc.zzgp();
    }

    public static int b(int i, byte[] bArr, int i2, int i3, zzgz<?> zzgzVar, f1 f1Var) {
        m2 m2Var = (m2) zzgzVar;
        int i4 = i(bArr, i2, f1Var);
        m2Var.c(f1Var.f9971a);
        while (i4 < i3) {
            int i5 = i(bArr, i4, f1Var);
            if (i != f1Var.f9971a) {
                break;
            }
            i4 = i(bArr, i5, f1Var);
            m2Var.c(f1Var.f9971a);
        }
        return i4;
    }

    public static int c(int i, byte[] bArr, int i2, int i3, zzjm zzjmVar, f1 f1Var) throws zzhc {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int k = k(bArr, i2, f1Var);
                zzjmVar.d(i, Long.valueOf(f1Var.b));
                return k;
            } else if (i4 == 1) {
                zzjmVar.d(i, Long.valueOf(m(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int i5 = i(bArr, i2, f1Var);
                int i6 = f1Var.f9971a;
                if (i6 >= 0) {
                    if (i6 <= bArr.length - i5) {
                        if (i6 == 0) {
                            zzjmVar.d(i, zzfh.zzsd);
                        } else {
                            zzjmVar.d(i, zzfh.zza(bArr, i5, i6));
                        }
                        return i5 + i6;
                    }
                    throw zzhc.zzgm();
                }
                throw zzhc.zzgn();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzjmVar.d(i, Integer.valueOf(h(bArr, i2)));
                    return i2 + 4;
                }
                throw zzhc.zzgp();
            } else {
                zzjm f = zzjm.f();
                int i7 = (i & (-8)) | 4;
                int i8 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int i9 = i(bArr, i2, f1Var);
                    int i10 = f1Var.f9971a;
                    i8 = i10;
                    if (i10 == i7) {
                        i2 = i9;
                        break;
                    }
                    int c = c(i8, bArr, i9, i3, f, f1Var);
                    i8 = i10;
                    i2 = c;
                }
                if (i2 <= i3 && i8 == i7) {
                    zzjmVar.d(i, f);
                    return i2;
                }
                throw zzhc.zzgs();
            }
        }
        throw zzhc.zzgp();
    }

    public static int d(int i, byte[] bArr, int i2, f1 f1Var) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            f1Var.f9971a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            f1Var.f9971a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            f1Var.f9971a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            f1Var.f9971a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                f1Var.f9971a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int e(o3<?> o3Var, int i, byte[] bArr, int i2, int i3, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        int g = g(o3Var, bArr, i2, i3, f1Var);
        zzgzVar.add(f1Var.c);
        while (g < i3) {
            int i4 = i(bArr, g, f1Var);
            if (i != f1Var.f9971a) {
                break;
            }
            g = g(o3Var, bArr, i4, i3, f1Var);
            zzgzVar.add(f1Var.c);
        }
        return g;
    }

    public static int f(o3 o3Var, byte[] bArr, int i, int i2, int i3, f1 f1Var) throws IOException {
        f3 f3Var = (f3) o3Var;
        Object newInstance = f3Var.newInstance();
        int n = f3Var.n(newInstance, bArr, i, i2, i3, f1Var);
        f3Var.e(newInstance);
        f1Var.c = newInstance;
        return n;
    }

    public static int g(o3 o3Var, byte[] bArr, int i, int i2, f1 f1Var) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = d(i4, bArr, i3, f1Var);
            i4 = f1Var.f9971a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object newInstance = o3Var.newInstance();
            int i6 = i4 + i5;
            o3Var.g(newInstance, bArr, i5, i6, f1Var);
            o3Var.e(newInstance);
            f1Var.c = newInstance;
            return i6;
        }
        throw zzhc.zzgm();
    }

    public static int h(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int i(byte[] bArr, int i, f1 f1Var) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            f1Var.f9971a = b;
            return i2;
        }
        return d(b, bArr, i2, f1Var);
    }

    public static int j(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        m2 m2Var = (m2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2 = i(bArr, i2, f1Var);
            m2Var.c(f1Var.f9971a);
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int k(byte[] bArr, int i, f1 f1Var) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            f1Var.b = j;
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
        f1Var.b = j2;
        return i3;
    }

    public static int l(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        v2 v2Var = (v2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2 = k(bArr, i2, f1Var);
            v2Var.a(f1Var.b);
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static long m(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public static double n(byte[] bArr, int i) {
        return Double.longBitsToDouble(m(bArr, i));
    }

    public static int o(byte[] bArr, int i, f1 f1Var) throws zzhc {
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a;
        if (i3 >= 0) {
            if (i3 == 0) {
                f1Var.c = "";
                return i2;
            }
            f1Var.c = new String(bArr, i2, i3, zzgt.f10024a);
            return i2 + i3;
        }
        throw zzhc.zzgn();
    }

    public static int p(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        m2 m2Var = (m2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            m2Var.c(h(bArr, i2));
            i2 += 4;
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static float q(byte[] bArr, int i) {
        return Float.intBitsToFloat(h(bArr, i));
    }

    public static int r(byte[] bArr, int i, f1 f1Var) throws zzhc {
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a;
        if (i3 >= 0) {
            if (i3 == 0) {
                f1Var.c = "";
                return i2;
            }
            f1Var.c = m4.j(bArr, i2, i3);
            return i2 + i3;
        }
        throw zzhc.zzgn();
    }

    public static int s(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        v2 v2Var = (v2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            v2Var.a(m(bArr, i2));
            i2 += 8;
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int t(byte[] bArr, int i, f1 f1Var) throws zzhc {
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a;
        if (i3 >= 0) {
            if (i3 <= bArr.length - i2) {
                if (i3 == 0) {
                    f1Var.c = zzfh.zzsd;
                    return i2;
                }
                f1Var.c = zzfh.zza(bArr, i2, i3);
                return i2 + i3;
            }
            throw zzhc.zzgm();
        }
        throw zzhc.zzgn();
    }

    public static int u(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        i2 i2Var = (i2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2Var.c(q(bArr, i2));
            i2 += 4;
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int v(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        z1 z1Var = (z1) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            z1Var.c(n(bArr, i2));
            i2 += 8;
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int w(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        j1 j1Var = (j1) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2 = k(bArr, i2, f1Var);
            j1Var.addBoolean(f1Var.b != 0);
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int x(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        m2 m2Var = (m2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2 = i(bArr, i2, f1Var);
            m2Var.c(zzft.zzav(f1Var.f9971a));
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }

    public static int y(byte[] bArr, int i, zzgz<?> zzgzVar, f1 f1Var) throws IOException {
        v2 v2Var = (v2) zzgzVar;
        int i2 = i(bArr, i, f1Var);
        int i3 = f1Var.f9971a + i2;
        while (i2 < i3) {
            i2 = k(bArr, i2, f1Var);
            v2Var.a(zzft.zzr(f1Var.b));
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzhc.zzgm();
    }
}
