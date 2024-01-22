package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class s4 extends n4 {
    public static int e(byte[] bArr, int i, long j, int i2) {
        int c;
        int l;
        int e;
        if (i2 == 0) {
            c = m4.c(i);
            return c;
        } else if (i2 == 1) {
            l = m4.l(i, j4.a(bArr, j));
            return l;
        } else if (i2 == 2) {
            e = m4.e(i, j4.a(bArr, j), j4.a(bArr, j + 1));
            return e;
        } else {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ba, code lost:
        return -1;
     */
    @Override // com.google.android.gms.internal.vision.n4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r18, byte[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.s4.a(int, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.vision.n4
    public final int b(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        char c2;
        int i3;
        char charAt;
        long j4 = i;
        long j5 = i2 + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            j4.h(bArr, j4, (byte) charAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 < c && j4 < j5) {
                long j6 = j4 + j;
                j4.h(bArr, j4, (byte) charAt3);
                j3 = j;
                j2 = j6;
                c2 = c;
            } else if (charAt3 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                j4.h(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                long j8 = j7 + j;
                j4.h(bArr, j7, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
                long j9 = j;
                c2 = 128;
                j2 = j8;
                j3 = j9;
            } else if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                if (j4 <= j5 - 4) {
                    int i5 = i4 + 1;
                    if (i5 != length) {
                        char charAt4 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt3, charAt4)) {
                            int codePoint = Character.toCodePoint(charAt3, charAt4);
                            long j10 = j4 + 1;
                            j4.h(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                            long j11 = j10 + 1;
                            c2 = 128;
                            j4.h(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j12 = j11 + 1;
                            j4.h(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                            j3 = 1;
                            j2 = j12 + 1;
                            j4.h(bArr, j12, (byte) ((codePoint & 63) | 128));
                            i4 = i5;
                        } else {
                            i4 = i5;
                        }
                    }
                    throw new p4(i4 - 1, length);
                } else if (55296 <= charAt3 && charAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                    throw new p4(i4, length);
                } else {
                    StringBuilder sb2 = new StringBuilder(46);
                    sb2.append("Failed writing ");
                    sb2.append(charAt3);
                    sb2.append(" at index ");
                    sb2.append(j4);
                    throw new ArrayIndexOutOfBoundsException(sb2.toString());
                }
            } else {
                long j13 = j4 + j;
                j4.h(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                long j14 = j13 + j;
                j4.h(bArr, j13, (byte) (((charAt3 >>> 6) & 63) | 128));
                j4.h(bArr, j14, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
                j2 = j14 + 1;
                j3 = 1;
                c2 = 128;
            }
            i4++;
            c = c2;
            long j15 = j3;
            j4 = j2;
            j = j15;
        }
        return (int) j4;
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
                byte a2 = j4.a(bArr, i);
                i5 = o4.i(a2);
                if (!i5) {
                    break;
                }
                i++;
                o4.d(a2, cArr, i7);
                i7++;
            }
            int i8 = i7;
            while (i < i6) {
                int i9 = i + 1;
                byte a3 = j4.a(bArr, i);
                i3 = o4.i(a3);
                if (i3) {
                    int i10 = i8 + 1;
                    o4.d(a3, cArr, i8);
                    while (i9 < i6) {
                        byte a4 = j4.a(bArr, i9);
                        i4 = o4.i(a4);
                        if (!i4) {
                            break;
                        }
                        i9++;
                        o4.d(a4, cArr, i10);
                        i10++;
                    }
                    i = i9;
                    i8 = i10;
                } else {
                    j = o4.j(a3);
                    if (!j) {
                        k = o4.k(a3);
                        if (k) {
                            if (i9 < i6 - 1) {
                                int i11 = i9 + 1;
                                o4.b(a3, j4.a(bArr, i9), j4.a(bArr, i11), cArr, i8);
                                i = i11 + 1;
                                i8++;
                            } else {
                                throw zzhc.zzgt();
                            }
                        } else if (i9 < i6 - 2) {
                            int i12 = i9 + 1;
                            int i13 = i12 + 1;
                            o4.a(a3, j4.a(bArr, i9), j4.a(bArr, i12), j4.a(bArr, i13), cArr, i8);
                            i = i13 + 1;
                            i8 = i8 + 1 + 1;
                        } else {
                            throw zzhc.zzgt();
                        }
                    } else if (i9 < i6) {
                        o4.c(a3, j4.a(bArr, i9), cArr, i8);
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
