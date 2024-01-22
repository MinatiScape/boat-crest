package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class w5 {
    public static int a(int i, byte[] bArr, int i2, int i3, v5 v5Var) throws zzxk {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzxk.zzvg();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = i(bArr, i2, v5Var);
                            i6 = v5Var.f8744a;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = a(i6, bArr, i2, i3, v5Var);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzxk.zzvi();
                        }
                        return i2;
                    }
                    return i(bArr, i2, v5Var) + v5Var.f8744a;
                }
                return i2 + 8;
            }
            return k(bArr, i2, v5Var);
        }
        throw zzxk.zzvg();
    }

    public static int b(int i, byte[] bArr, int i2, int i3, zzxl<?> zzxlVar, v5 v5Var) {
        w6 w6Var = (w6) zzxlVar;
        int i4 = i(bArr, i2, v5Var);
        w6Var.zzds(v5Var.f8744a);
        while (i4 < i3) {
            int i5 = i(bArr, i4, v5Var);
            if (i != v5Var.f8744a) {
                break;
            }
            i4 = i(bArr, i5, v5Var);
            w6Var.zzds(v5Var.f8744a);
        }
        return i4;
    }

    public static int c(int i, byte[] bArr, int i2, int i3, zzzz zzzzVar, v5 v5Var) throws zzxk {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int k = k(bArr, i2, v5Var);
                zzzzVar.c(i, Long.valueOf(v5Var.b));
                return k;
            } else if (i4 == 1) {
                zzzzVar.c(i, Long.valueOf(l(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int i5 = i(bArr, i2, v5Var);
                int i6 = v5Var.f8744a;
                if (i6 >= 0) {
                    if (i6 <= bArr.length - i5) {
                        if (i6 == 0) {
                            zzzzVar.c(i, zzvv.zzchp);
                        } else {
                            zzzzVar.c(i, zzvv.zzc(bArr, i5, i6));
                        }
                        return i5 + i6;
                    }
                    throw zzxk.zzve();
                }
                throw zzxk.zzvf();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzzzVar.c(i, Integer.valueOf(h(bArr, i2)));
                    return i2 + 4;
                }
                throw zzxk.zzvg();
            } else {
                zzzz f = zzzz.f();
                int i7 = (i & (-8)) | 4;
                int i8 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int i9 = i(bArr, i2, v5Var);
                    int i10 = v5Var.f8744a;
                    i8 = i10;
                    if (i10 == i7) {
                        i2 = i9;
                        break;
                    }
                    int c = c(i8, bArr, i9, i3, f, v5Var);
                    i8 = i10;
                    i2 = c;
                }
                if (i2 <= i3 && i8 == i7) {
                    zzzzVar.c(i, f);
                    return i2;
                }
                throw zzxk.zzvi();
            }
        }
        throw zzxk.zzvg();
    }

    public static int d(int i, byte[] bArr, int i2, v5 v5Var) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            v5Var.f8744a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            v5Var.f8744a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            v5Var.f8744a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            v5Var.f8744a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                v5Var.f8744a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int e(c8<?> c8Var, int i, byte[] bArr, int i2, int i3, zzxl<?> zzxlVar, v5 v5Var) throws IOException {
        int g = g(c8Var, bArr, i2, i3, v5Var);
        zzxlVar.add(v5Var.c);
        while (g < i3) {
            int i4 = i(bArr, g, v5Var);
            if (i != v5Var.f8744a) {
                break;
            }
            g = g(c8Var, bArr, i4, i3, v5Var);
            zzxlVar.add(v5Var.c);
        }
        return g;
    }

    public static int f(c8 c8Var, byte[] bArr, int i, int i2, int i3, v5 v5Var) throws IOException {
        o7 o7Var = (o7) c8Var;
        Object newInstance = o7Var.newInstance();
        int m = o7Var.m(newInstance, bArr, i, i2, i3, v5Var);
        o7Var.e(newInstance);
        v5Var.c = newInstance;
        return m;
    }

    public static int g(c8 c8Var, byte[] bArr, int i, int i2, v5 v5Var) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = d(i4, bArr, i3, v5Var);
            i4 = v5Var.f8744a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            Object newInstance = c8Var.newInstance();
            int i6 = i4 + i5;
            c8Var.f(newInstance, bArr, i5, i6, v5Var);
            c8Var.e(newInstance);
            v5Var.c = newInstance;
            return i6;
        }
        throw zzxk.zzve();
    }

    public static int h(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int i(byte[] bArr, int i, v5 v5Var) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            v5Var.f8744a = b;
            return i2;
        }
        return d(b, bArr, i2, v5Var);
    }

    public static int j(byte[] bArr, int i, zzxl<?> zzxlVar, v5 v5Var) throws IOException {
        w6 w6Var = (w6) zzxlVar;
        int i2 = i(bArr, i, v5Var);
        int i3 = v5Var.f8744a + i2;
        while (i2 < i3) {
            i2 = i(bArr, i2, v5Var);
            w6Var.zzds(v5Var.f8744a);
        }
        if (i2 == i3) {
            return i2;
        }
        throw zzxk.zzve();
    }

    public static int k(byte[] bArr, int i, v5 v5Var) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            v5Var.b = j;
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
        v5Var.b = j2;
        return i3;
    }

    public static long l(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public static double m(byte[] bArr, int i) {
        return Double.longBitsToDouble(l(bArr, i));
    }

    public static int n(byte[] bArr, int i, v5 v5Var) throws zzxk {
        int i2 = i(bArr, i, v5Var);
        int i3 = v5Var.f8744a;
        if (i3 >= 0) {
            if (i3 == 0) {
                v5Var.c = "";
                return i2;
            }
            v5Var.c = new String(bArr, i2, i3, zzxd.f8814a);
            return i2 + i3;
        }
        throw zzxk.zzvf();
    }

    public static float o(byte[] bArr, int i) {
        return Float.intBitsToFloat(h(bArr, i));
    }

    public static int p(byte[] bArr, int i, v5 v5Var) throws zzxk {
        int i2 = i(bArr, i, v5Var);
        int i3 = v5Var.f8744a;
        if (i3 >= 0) {
            if (i3 == 0) {
                v5Var.c = "";
                return i2;
            }
            v5Var.c = e.j(bArr, i2, i3);
            return i2 + i3;
        }
        throw zzxk.zzvf();
    }

    public static int q(byte[] bArr, int i, v5 v5Var) throws zzxk {
        int i2 = i(bArr, i, v5Var);
        int i3 = v5Var.f8744a;
        if (i3 >= 0) {
            if (i3 <= bArr.length - i2) {
                if (i3 == 0) {
                    v5Var.c = zzvv.zzchp;
                    return i2;
                }
                v5Var.c = zzvv.zzc(bArr, i2, i3);
                return i2 + i3;
            }
            throw zzxk.zzve();
        }
        throw zzxk.zzvf();
    }
}
