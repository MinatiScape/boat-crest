package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
/* loaded from: classes7.dex */
public final class t2 extends q2 {
    public static int f(byte[] bArr, int i, long j, int i2) {
        int d;
        int l;
        int f;
        if (i2 == 0) {
            d = p2.d(i);
            return d;
        } else if (i2 == 1) {
            l = p2.l(i, n2.a(bArr, j));
            return l;
        } else if (i2 == 2) {
            f = p2.f(i, n2.a(bArr, j), n2.a(bArr, j + 1));
            return f;
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
    @Override // com.google.android.gms.internal.clearcut.q2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(int r18, byte[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.t2.a(int, byte[], int, int):int");
    }

    @Override // com.google.android.gms.internal.clearcut.q2
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
            n2.k(bArr, j4, (byte) charAt);
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
                n2.k(bArr, j4, (byte) charAt3);
                j3 = j;
                j2 = j6;
                c2 = c;
            } else if (charAt3 < 2048 && j4 <= j5 - 2) {
                long j7 = j4 + j;
                n2.k(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                long j8 = j7 + j;
                n2.k(bArr, j7, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
                long j9 = j;
                c2 = 128;
                j2 = j8;
                j3 = j9;
            } else if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                if (j4 > j5 - 4) {
                    if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i4 + 1) != length && Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j4);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                    throw new s2(i4, length);
                }
                int i5 = i4 + 1;
                if (i5 != length) {
                    char charAt4 = charSequence.charAt(i5);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j10 = j4 + 1;
                        n2.k(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                        long j11 = j10 + 1;
                        c2 = 128;
                        n2.k(bArr, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j12 = j11 + 1;
                        n2.k(bArr, j11, (byte) (((codePoint >>> 6) & 63) | 128));
                        j3 = 1;
                        j2 = j12 + 1;
                        n2.k(bArr, j12, (byte) ((codePoint & 63) | 128));
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                }
                throw new s2(i4 - 1, length);
            } else {
                long j13 = j4 + j;
                n2.k(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                long j14 = j13 + j;
                n2.k(bArr, j13, (byte) (((charAt3 >>> 6) & 63) | 128));
                n2.k(bArr, j14, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
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

    @Override // com.google.android.gms.internal.clearcut.q2
    public final void c(CharSequence charSequence, ByteBuffer byteBuffer) {
        char c;
        int i;
        long j;
        int i2;
        int i3;
        long j2;
        char c2;
        char charAt;
        ByteBuffer byteBuffer2 = byteBuffer;
        long o = n2.o(byteBuffer);
        long position = byteBuffer.position() + o;
        long limit = byteBuffer.limit() + o;
        int length = charSequence.length();
        if (length > limit - position) {
            char charAt2 = charSequence.charAt(length - 1);
            int limit2 = byteBuffer.limit();
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(limit2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            n2.c(position, (byte) charAt);
            i4++;
            position++;
        }
        if (i4 == length) {
            i = (int) (position - o);
        } else {
            while (i4 < length) {
                char charAt3 = charSequence.charAt(i4);
                if (charAt3 >= c || position >= limit) {
                    if (charAt3 >= 2048 || position > limit - 2) {
                        j = o;
                        if ((charAt3 >= 55296 && 57343 >= charAt3) || position > limit - 3) {
                            if (position > limit - 4) {
                                if (55296 <= charAt3 && charAt3 <= 57343 && ((i2 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i2)))) {
                                    throw new s2(i4, length);
                                }
                                StringBuilder sb2 = new StringBuilder(46);
                                sb2.append("Failed writing ");
                                sb2.append(charAt3);
                                sb2.append(" at index ");
                                sb2.append(position);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            i3 = i4 + 1;
                            if (i3 != length) {
                                char charAt4 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(charAt3, charAt4)) {
                                    int codePoint = Character.toCodePoint(charAt3, charAt4);
                                    j2 = limit;
                                    long j3 = position + 1;
                                    n2.c(position, (byte) ((codePoint >>> 18) | 240));
                                    long j4 = j3 + 1;
                                    c2 = 128;
                                    n2.c(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    n2.c(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    n2.c(j5, (byte) ((codePoint & 63) | 128));
                                    position = j5 + 1;
                                } else {
                                    i4 = i3;
                                }
                            }
                            throw new s2(i4 - 1, length);
                        }
                        long j6 = position + 1;
                        n2.c(position, (byte) ((charAt3 >>> '\f') | 480));
                        long j7 = j6 + 1;
                        n2.c(j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                        n2.c(j7, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
                        position = j7 + 1;
                    } else {
                        j = o;
                        long j8 = position + 1;
                        n2.c(position, (byte) ((charAt3 >>> 6) | 960));
                        n2.c(j8, (byte) ((charAt3 & org.apache.commons.codec.net.a.SEP) | 128));
                        position = j8 + 1;
                    }
                    j2 = limit;
                    i3 = i4;
                    c2 = 128;
                } else {
                    n2.c(position, (byte) charAt3);
                    j2 = limit;
                    i3 = i4;
                    c2 = c;
                    position++;
                    j = o;
                }
                c = c2;
                o = j;
                limit = j2;
                i4 = i3 + 1;
            }
            i = (int) (position - o);
            byteBuffer2 = byteBuffer;
        }
        byteBuffer2.position(i);
    }
}
