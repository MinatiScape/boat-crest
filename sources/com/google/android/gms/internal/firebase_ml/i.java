package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class i extends f {
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r13[r14] > (-65)) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
        if (r13[r14] > (-65)) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0082, code lost:
        if (r13[r14] > (-65)) goto L48;
     */
    @Override // com.google.android.gms.internal.firebase_ml.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r12, byte[] r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.i.a(int, byte[], int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.firebase_ml.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.i.b(java.lang.CharSequence, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.firebase_ml.f
    public final String d(byte[] bArr, int i, int i2) throws zzxk {
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
                i5 = g.i(b);
                if (!i5) {
                    break;
                }
                i++;
                g.d(b, cArr, i7);
                i7++;
            }
            int i8 = i7;
            while (i < i6) {
                int i9 = i + 1;
                byte b2 = bArr[i];
                i3 = g.i(b2);
                if (i3) {
                    int i10 = i8 + 1;
                    g.d(b2, cArr, i8);
                    while (i9 < i6) {
                        byte b3 = bArr[i9];
                        i4 = g.i(b3);
                        if (!i4) {
                            break;
                        }
                        i9++;
                        g.d(b3, cArr, i10);
                        i10++;
                    }
                    i = i9;
                    i8 = i10;
                } else {
                    j = g.j(b2);
                    if (!j) {
                        k = g.k(b2);
                        if (k) {
                            if (i9 < i6 - 1) {
                                int i11 = i9 + 1;
                                g.b(b2, bArr[i9], bArr[i11], cArr, i8);
                                i = i11 + 1;
                                i8++;
                            } else {
                                throw zzxk.zzvj();
                            }
                        } else if (i9 < i6 - 2) {
                            int i12 = i9 + 1;
                            byte b4 = bArr[i9];
                            int i13 = i12 + 1;
                            g.a(b2, b4, bArr[i12], bArr[i13], cArr, i8);
                            i = i13 + 1;
                            i8 = i8 + 1 + 1;
                        } else {
                            throw zzxk.zzvj();
                        }
                    } else if (i9 < i6) {
                        g.c(b2, bArr[i9], cArr, i8);
                        i = i9 + 1;
                        i8++;
                    } else {
                        throw zzxk.zzvj();
                    }
                }
            }
            return new String(cArr, 0, i8);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }
}
