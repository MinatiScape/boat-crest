package com.google.android.gms.internal.clearcut;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class n {
    public static int a(int i, byte[] bArr, int i2, int i3, o oVar) throws zzco {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzco.zzbm();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = e(bArr, i2, oVar);
                            i6 = oVar.f8592a;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = a(i6, bArr, i2, i3, oVar);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzco.zzbo();
                        }
                        return i2;
                    }
                    return e(bArr, i2, oVar) + oVar.f8592a;
                }
                return i2 + 8;
            }
            return g(bArr, i2, oVar);
        }
        throw zzco.zzbm();
    }

    public static int b(int i, byte[] bArr, int i2, int i3, zzcn<?> zzcnVar, o oVar) {
        o0 o0Var = (o0) zzcnVar;
        int e = e(bArr, i2, oVar);
        while (true) {
            o0Var.a(oVar.f8592a);
            if (e >= i3) {
                break;
            }
            int e2 = e(bArr, e, oVar);
            if (i != oVar.f8592a) {
                break;
            }
            e = e(bArr, e2, oVar);
        }
        return e;
    }

    public static int c(int i, byte[] bArr, int i2, int i3, zzey zzeyVar, o oVar) throws IOException {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int g = g(bArr, i2, oVar);
                zzeyVar.d(i, Long.valueOf(oVar.b));
                return g;
            } else if (i4 == 1) {
                zzeyVar.d(i, Long.valueOf(k(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int e = e(bArr, i2, oVar);
                int i5 = oVar.f8592a;
                zzeyVar.d(i, i5 == 0 ? zzbb.zzfi : zzbb.zzb(bArr, e, i5));
                return e + i5;
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzeyVar.d(i, Integer.valueOf(h(bArr, i2)));
                    return i2 + 4;
                }
                throw zzco.zzbm();
            } else {
                zzey f = zzey.f();
                int i6 = (i & (-8)) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int e2 = e(bArr, i2, oVar);
                    int i8 = oVar.f8592a;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = e2;
                        break;
                    }
                    int c = c(i7, bArr, e2, i3, f, oVar);
                    i7 = i8;
                    i2 = c;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzco.zzbo();
                }
                zzeyVar.d(i, f);
                return i2;
            }
        }
        throw zzco.zzbm();
    }

    public static int d(int i, byte[] bArr, int i2, o oVar) {
        int i3;
        int i4;
        int i5 = i & 127;
        int i6 = i2 + 1;
        byte b = bArr[i2];
        if (b < 0) {
            int i7 = i5 | ((b & Byte.MAX_VALUE) << 7);
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            if (b2 >= 0) {
                i3 = b2 << 14;
            } else {
                i5 = i7 | ((b2 & Byte.MAX_VALUE) << 14);
                i6 = i8 + 1;
                byte b3 = bArr[i8];
                if (b3 >= 0) {
                    i4 = b3 << 21;
                } else {
                    i7 = i5 | ((b3 & Byte.MAX_VALUE) << 21);
                    i8 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 >= 0) {
                        i3 = b4 << 28;
                    } else {
                        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i10 = i8 + 1;
                            if (bArr[i8] >= 0) {
                                oVar.f8592a = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            oVar.f8592a = i7 | i3;
            return i8;
        }
        i4 = b << 7;
        oVar.f8592a = i5 | i4;
        return i6;
    }

    public static int e(byte[] bArr, int i, o oVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            oVar.f8592a = b;
            return i2;
        }
        return d(b, bArr, i2, oVar);
    }

    public static int f(byte[] bArr, int i, zzcn<?> zzcnVar, o oVar) throws IOException {
        o0 o0Var = (o0) zzcnVar;
        int e = e(bArr, i, oVar);
        int i2 = oVar.f8592a + e;
        while (e < i2) {
            e = e(bArr, e, oVar);
            o0Var.a(oVar.f8592a);
        }
        if (e == i2) {
            return e;
        }
        throw zzco.zzbl();
    }

    public static int g(byte[] bArr, int i, o oVar) {
        byte b;
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            oVar.b = j;
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
        oVar.b = j2;
        return i3;
    }

    public static int h(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int i(byte[] bArr, int i, o oVar) {
        int e = e(bArr, i, oVar);
        int i2 = oVar.f8592a;
        if (i2 == 0) {
            oVar.c = "";
            return e;
        }
        oVar.c = new String(bArr, e, i2, zzci.f8618a);
        return e + i2;
    }

    public static int j(byte[] bArr, int i, o oVar) throws IOException {
        int e = e(bArr, i, oVar);
        int i2 = oVar.f8592a;
        if (i2 == 0) {
            oVar.c = "";
            return e;
        }
        int i3 = e + i2;
        if (p2.i(bArr, e, i3)) {
            oVar.c = new String(bArr, e, i2, zzci.f8618a);
            return i3;
        }
        throw zzco.zzbp();
    }

    public static long k(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public static double l(byte[] bArr, int i) {
        return Double.longBitsToDouble(k(bArr, i));
    }

    public static int m(byte[] bArr, int i, o oVar) {
        int e = e(bArr, i, oVar);
        int i2 = oVar.f8592a;
        if (i2 == 0) {
            oVar.c = zzbb.zzfi;
            return e;
        }
        oVar.c = zzbb.zzb(bArr, e, i2);
        return e + i2;
    }

    public static float n(byte[] bArr, int i) {
        return Float.intBitsToFloat(h(bArr, i));
    }
}
