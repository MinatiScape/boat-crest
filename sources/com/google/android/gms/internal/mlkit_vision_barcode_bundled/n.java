package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class n {
    public static int a(byte[] bArr, int i, m mVar) throws zzeo {
        int j = j(bArr, i, mVar);
        int i2 = mVar.f9604a;
        if (i2 >= 0) {
            if (i2 <= bArr.length - j) {
                if (i2 == 0) {
                    mVar.c = zzdb.zzb;
                    return j;
                }
                mVar.c = zzdb.zzr(bArr, j, i2);
                return j + i2;
            }
            throw zzeo.zzg();
        }
        throw zzeo.zzd();
    }

    public static int b(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << 24) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    public static int c(l1 l1Var, byte[] bArr, int i, int i2, int i3, m mVar) throws IOException {
        Object zze = l1Var.zze();
        int n = n(zze, l1Var, bArr, i, i2, i3, mVar);
        l1Var.zzf(zze);
        mVar.c = zze;
        return n;
    }

    public static int d(l1 l1Var, byte[] bArr, int i, int i2, m mVar) throws IOException {
        Object zze = l1Var.zze();
        int o = o(zze, l1Var, bArr, i, i2, mVar);
        l1Var.zzf(zze);
        mVar.c = zze;
        return o;
    }

    public static int e(l1 l1Var, int i, byte[] bArr, int i2, int i3, zzel zzelVar, m mVar) throws IOException {
        int d = d(l1Var, bArr, i2, i3, mVar);
        zzelVar.add(mVar.c);
        while (d < i3) {
            int j = j(bArr, d, mVar);
            if (i != mVar.f9604a) {
                break;
            }
            d = d(l1Var, bArr, j, i3, mVar);
            zzelVar.add(mVar.c);
        }
        return d;
    }

    public static int f(byte[] bArr, int i, zzel zzelVar, m mVar) throws IOException {
        j0 j0Var = (j0) zzelVar;
        int j = j(bArr, i, mVar);
        int i2 = mVar.f9604a + j;
        while (j < i2) {
            j = j(bArr, j, mVar);
            j0Var.c(mVar.f9604a);
        }
        if (j == i2) {
            return j;
        }
        throw zzeo.zzg();
    }

    public static int g(byte[] bArr, int i, m mVar) throws zzeo {
        int j = j(bArr, i, mVar);
        int i2 = mVar.f9604a;
        if (i2 >= 0) {
            if (i2 == 0) {
                mVar.c = "";
                return j;
            }
            mVar.c = new String(bArr, j, i2, zzem.f9632a);
            return j + i2;
        }
        throw zzeo.zzd();
    }

    public static int h(byte[] bArr, int i, m mVar) throws zzeo {
        int j = j(bArr, i, mVar);
        int i2 = mVar.f9604a;
        if (i2 >= 0) {
            if (i2 == 0) {
                mVar.c = "";
                return j;
            }
            int length = bArr.length;
            int i3 = l2.b;
            if ((j | i2 | ((length - j) - i2)) >= 0) {
                int i4 = j + i2;
                char[] cArr = new char[i2];
                int i5 = 0;
                while (j < i4) {
                    byte b = bArr[j];
                    if (!h2.d(b)) {
                        break;
                    }
                    j++;
                    cArr[i5] = (char) b;
                    i5++;
                }
                while (j < i4) {
                    int i6 = j + 1;
                    byte b2 = bArr[j];
                    if (h2.d(b2)) {
                        int i7 = i5 + 1;
                        cArr[i5] = (char) b2;
                        j = i6;
                        while (true) {
                            i5 = i7;
                            if (j < i4) {
                                byte b3 = bArr[j];
                                if (h2.d(b3)) {
                                    j++;
                                    i7 = i5 + 1;
                                    cArr[i5] = (char) b3;
                                }
                            }
                        }
                    } else if (b2 < -32) {
                        if (i6 < i4) {
                            h2.c(b2, bArr[i6], cArr, i5);
                            j = i6 + 1;
                            i5++;
                        } else {
                            throw zzeo.zzc();
                        }
                    } else if (b2 < -16) {
                        if (i6 < i4 - 1) {
                            int i8 = i6 + 1;
                            h2.b(b2, bArr[i6], bArr[i8], cArr, i5);
                            j = i8 + 1;
                            i5++;
                        } else {
                            throw zzeo.zzc();
                        }
                    } else if (i6 < i4 - 2) {
                        int i9 = i6 + 1;
                        byte b4 = bArr[i6];
                        int i10 = i9 + 1;
                        h2.a(b2, b4, bArr[i9], bArr[i10], cArr, i5);
                        i5 += 2;
                        j = i10 + 1;
                    } else {
                        throw zzeo.zzc();
                    }
                }
                mVar.c = new String(cArr, 0, i5);
                return i4;
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(j), Integer.valueOf(i2)));
        }
        throw zzeo.zzd();
    }

    public static int i(int i, byte[] bArr, int i2, int i3, zzgz zzgzVar, m mVar) throws zzeo {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int m = m(bArr, i2, mVar);
                zzgzVar.f(i, Long.valueOf(mVar.b));
                return m;
            } else if (i4 == 1) {
                zzgzVar.f(i, Long.valueOf(q(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int j = j(bArr, i2, mVar);
                int i5 = mVar.f9604a;
                if (i5 >= 0) {
                    if (i5 <= bArr.length - j) {
                        if (i5 == 0) {
                            zzgzVar.f(i, zzdb.zzb);
                        } else {
                            zzgzVar.f(i, zzdb.zzr(bArr, j, i5));
                        }
                        return j + i5;
                    }
                    throw zzeo.zzg();
                }
                throw zzeo.zzd();
            } else if (i4 != 3) {
                if (i4 == 5) {
                    zzgzVar.f(i, Integer.valueOf(b(bArr, i2)));
                    return i2 + 4;
                }
                throw zzeo.zzb();
            } else {
                int i6 = (i & (-8)) | 4;
                zzgz c = zzgz.c();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int j2 = j(bArr, i2, mVar);
                    int i8 = mVar.f9604a;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = j2;
                        break;
                    }
                    int i9 = i(i7, bArr, j2, i3, c, mVar);
                    i7 = i8;
                    i2 = i9;
                }
                if (i2 <= i3 && i7 == i6) {
                    zzgzVar.f(i, c);
                    return i2;
                }
                throw zzeo.zze();
            }
        }
        throw zzeo.zzb();
    }

    public static int j(byte[] bArr, int i, m mVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            mVar.f9604a = b;
            return i2;
        }
        return k(b, bArr, i2, mVar);
    }

    public static int k(int i, byte[] bArr, int i2, m mVar) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            mVar.f9604a = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            mVar.f9604a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            mVar.f9604a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            mVar.f9604a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                mVar.f9604a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int l(int i, byte[] bArr, int i2, int i3, zzel zzelVar, m mVar) {
        j0 j0Var = (j0) zzelVar;
        int j = j(bArr, i2, mVar);
        j0Var.c(mVar.f9604a);
        while (j < i3) {
            int j2 = j(bArr, j, mVar);
            if (i != mVar.f9604a) {
                break;
            }
            j = j(bArr, j2, mVar);
            j0Var.c(mVar.f9604a);
        }
        return j;
    }

    public static int m(byte[] bArr, int i, m mVar) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            mVar.b = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= (b2 & Byte.MAX_VALUE) << i4;
            i3 = i5;
            b = b2;
        }
        mVar.b = j2;
        return i3;
    }

    public static int n(Object obj, l1 l1Var, byte[] bArr, int i, int i2, int i3, m mVar) throws IOException {
        int y = ((y0) l1Var).y(obj, bArr, i, i2, i3, mVar);
        mVar.c = obj;
        return y;
    }

    public static int o(Object obj, l1 l1Var, byte[] bArr, int i, int i2, m mVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = k(i4, bArr, i3, mVar);
            i4 = mVar.f9604a;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            int i6 = i4 + i5;
            l1Var.a(obj, bArr, i5, i6, mVar);
            mVar.c = obj;
            return i6;
        }
        throw zzeo.zzg();
    }

    public static int p(int i, byte[] bArr, int i2, int i3, m mVar) throws zzeo {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 5) {
                                return i2 + 4;
                            }
                            throw zzeo.zzb();
                        }
                        int i5 = (i & (-8)) | 4;
                        int i6 = 0;
                        while (i2 < i3) {
                            i2 = j(bArr, i2, mVar);
                            i6 = mVar.f9604a;
                            if (i6 == i5) {
                                break;
                            }
                            i2 = p(i6, bArr, i2, i3, mVar);
                        }
                        if (i2 > i3 || i6 != i5) {
                            throw zzeo.zze();
                        }
                        return i2;
                    }
                    return j(bArr, i2, mVar) + mVar.f9604a;
                }
                return i2 + 8;
            }
            return m(bArr, i2, mVar);
        }
        throw zzeo.zzb();
    }

    public static long q(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }
}
