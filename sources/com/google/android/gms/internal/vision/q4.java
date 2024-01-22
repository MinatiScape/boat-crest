package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class q4 extends n4 {
    @Override // com.google.android.gms.internal.vision.n4
    public final int a(int i, byte[] bArr, int i2, int i3) {
        int h;
        int h2;
        while (i2 < i3 && bArr[i2] >= 0) {
            i2++;
        }
        if (i2 >= i3) {
            return 0;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b < 0) {
                if (b < -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        i2 = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                } else if (b >= -16) {
                    if (i4 >= i3 - 2) {
                        h2 = m4.h(bArr, i4, i3);
                        return h2;
                    }
                    int i5 = i4 + 1;
                    byte b2 = bArr[i4];
                    if (b2 <= -65 && (((b << 28) + (b2 + com.htsmart.wristband2.a.a.a.J1)) >> 30) == 0) {
                        int i6 = i5 + 1;
                        if (bArr[i5] <= -65) {
                            i4 = i6 + 1;
                            if (bArr[i6] > -65) {
                            }
                        }
                    }
                    return -1;
                } else if (i4 >= i3 - 1) {
                    h = m4.h(bArr, i4, i3);
                    return h;
                } else {
                    int i7 = i4 + 1;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        i2 = i7 + 1;
                        if (bArr[i7] > -65) {
                        }
                    }
                    return -1;
                }
            }
            i2 = i4;
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.vision.n4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.q4.b(java.lang.CharSequence, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.vision.n4
    public final String d(byte[] bArr, int i, int i2) throws zzhc {
        boolean i3;
        boolean i4;
        boolean j;
        boolean k;
        boolean i5;
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i6 = i + i2;
            char[] cArr = new char[i2];
            int i7 = 0;
            while (i < i6) {
                byte b = bArr[i];
                i5 = o4.i(b);
                if (!i5) {
                    break;
                }
                i++;
                o4.d(b, cArr, i7);
                i7++;
            }
            int i8 = i7;
            while (i < i6) {
                int i9 = i + 1;
                byte b2 = bArr[i];
                i3 = o4.i(b2);
                if (i3) {
                    int i10 = i8 + 1;
                    o4.d(b2, cArr, i8);
                    while (i9 < i6) {
                        byte b3 = bArr[i9];
                        i4 = o4.i(b3);
                        if (!i4) {
                            break;
                        }
                        i9++;
                        o4.d(b3, cArr, i10);
                        i10++;
                    }
                    i = i9;
                    i8 = i10;
                } else {
                    j = o4.j(b2);
                    if (!j) {
                        k = o4.k(b2);
                        if (k) {
                            if (i9 < i6 - 1) {
                                int i11 = i9 + 1;
                                o4.b(b2, bArr[i9], bArr[i11], cArr, i8);
                                i = i11 + 1;
                                i8++;
                            } else {
                                throw zzhc.zzgt();
                            }
                        } else if (i9 < i6 - 2) {
                            int i12 = i9 + 1;
                            byte b4 = bArr[i9];
                            int i13 = i12 + 1;
                            o4.a(b2, b4, bArr[i12], bArr[i13], cArr, i8);
                            i = i13 + 1;
                            i8 = i8 + 1 + 1;
                        } else {
                            throw zzhc.zzgt();
                        }
                    } else if (i9 < i6) {
                        o4.c(b2, bArr[i9], cArr, i8);
                        i = i9 + 1;
                        i8++;
                    } else {
                        throw zzhc.zzgt();
                    }
                }
            }
            return new String(cArr, 0, i8);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }
}
