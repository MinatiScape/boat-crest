package com.google.android.gms.internal.measurement;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class h2 {
    public static int a(byte[] bArr, int i, g2 g2Var) throws zzkj {
        int j = j(bArr, i, g2Var);
        int i2 = g2Var.f8911a;
        if (i2 >= 0) {
            if (i2 <= bArr.length - j) {
                if (i2 == 0) {
                    g2Var.c = zziy.zzb;
                    return j;
                }
                g2Var.c = zziy.zzl(bArr, j, i2);
                return j + i2;
            }
            throw zzkj.zzf();
        }
        throw zzkj.zzd();
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int c(x3 x3Var, byte[] bArr, int i, int i2, int i3, g2 g2Var) throws IOException {
        p3 p3Var = (p3) x3Var;
        Object zze = p3Var.zze();
        int y = p3Var.y(zze, bArr, i, i2, i3, g2Var);
        p3Var.zzf(zze);
        g2Var.c = zze;
        return y;
    }

    public static int d(x3 x3Var, byte[] bArr, int i, int i2, g2 g2Var) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = k(i4, bArr, i3, g2Var);
            i4 = g2Var.f8911a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object zze = x3Var.zze();
            int i6 = i4 + i5;
            x3Var.a(zze, bArr, i5, i6, g2Var);
            x3Var.zzf(zze);
            g2Var.c = zze;
            return i6;
        }
        throw zzkj.zzf();
    }

    public static int e(x3<?> x3Var, int i, byte[] bArr, int i2, int i3, zzkg<?> zzkgVar, g2 g2Var) throws IOException {
        int d = d(x3Var, bArr, i2, i3, g2Var);
        zzkgVar.add(g2Var.c);
        while (d < i3) {
            int j = j(bArr, d, g2Var);
            if (i != g2Var.f8911a) {
                break;
            }
            d = d(x3Var, bArr, j, i3, g2Var);
            zzkgVar.add(g2Var.c);
        }
        return d;
    }

    public static int f(byte[] bArr, int i, zzkg<?> zzkgVar, g2 g2Var) throws IOException {
        c3 c3Var = (c3) zzkgVar;
        int j = j(bArr, i, g2Var);
        int i2 = g2Var.f8911a + j;
        while (j < i2) {
            j = j(bArr, j, g2Var);
            c3Var.zzh(g2Var.f8911a);
        }
        if (j == i2) {
            return j;
        }
        throw zzkj.zzf();
    }

    public static int g(byte[] bArr, int i, g2 g2Var) throws zzkj {
        int j = j(bArr, i, g2Var);
        int i2 = g2Var.f8911a;
        if (i2 >= 0) {
            if (i2 == 0) {
                g2Var.c = "";
                return j;
            }
            g2Var.c = new String(bArr, j, i2, zzkh.f8963a);
            return j + i2;
        }
        throw zzkj.zzd();
    }

    public static int h(byte[] bArr, int i, g2 g2Var) throws zzkj {
        int j = j(bArr, i, g2Var);
        int i2 = g2Var.f8911a;
        if (i2 >= 0) {
            if (i2 == 0) {
                g2Var.c = "";
                return j;
            }
            g2Var.c = y4.d(bArr, j, i2);
            return j + i2;
        }
        throw zzkj.zzd();
    }

    public static int i(int i, byte[] bArr, int i2, int i3, zzmj zzmjVar, g2 g2Var) throws zzkj {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int m = m(bArr, i2, g2Var);
                zzmjVar.d(i, Long.valueOf(g2Var.b));
                return m;
            } else if (i4 == 1) {
                zzmjVar.d(i, Long.valueOf(n(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int j = j(bArr, i2, g2Var);
                int i5 = g2Var.f8911a;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - j) {
                        if (i5 == 0) {
                            zzmjVar.d(i, zziy.zzb);
                        } else {
                            zzmjVar.d(i, zziy.zzl(bArr, j, i5));
                        }
                        return j + i5;
                    }
                    throw zzkj.zzf();
                }
                throw zzkj.zzd();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzmjVar.d(i, Integer.valueOf(b(bArr, i2)));
                    return i2 + 4;
                }
                throw zzkj.zzb();
            } else {
                int i6 = (i & (-8)) | 4;
                zzmj b = zzmj.b();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int j2 = j(bArr, i2, g2Var);
                    int i8 = g2Var.f8911a;
                    if (i8 == i6) {
                        i7 = i8;
                        i2 = j2;
                        break;
                    }
                    i7 = i8;
                    i2 = i(i8, bArr, j2, i3, b, g2Var);
                }
                if (i2 <= i3 && i7 == i6) {
                    zzmjVar.d(i, b);
                    return i2;
                }
                throw zzkj.zze();
            }
        }
        throw zzkj.zzb();
    }

    public static int j(byte[] bArr, int i, g2 g2Var) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            g2Var.f8911a = b;
            return i2;
        }
        return k(b, bArr, i2, g2Var);
    }

    public static int k(int i, byte[] bArr, int i2, g2 g2Var) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            g2Var.f8911a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            g2Var.f8911a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            g2Var.f8911a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            g2Var.f8911a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                g2Var.f8911a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int l(int i, byte[] bArr, int i2, int i3, zzkg<?> zzkgVar, g2 g2Var) {
        c3 c3Var = (c3) zzkgVar;
        int j = j(bArr, i2, g2Var);
        c3Var.zzh(g2Var.f8911a);
        while (j < i3) {
            int j2 = j(bArr, j, g2Var);
            if (i != g2Var.f8911a) {
                break;
            }
            j = j(bArr, j2, g2Var);
            c3Var.zzh(g2Var.f8911a);
        }
        return j;
    }

    public static int m(byte[] bArr, int i, g2 g2Var) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            g2Var.b = j;
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
        g2Var.b = j2;
        return i3;
    }

    public static long n(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }
}
