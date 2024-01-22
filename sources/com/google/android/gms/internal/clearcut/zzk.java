package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes7.dex */
public final class zzk {
    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long b(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static void c(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long d = d(bArr, i);
        long d2 = d(bArr, i + 8);
        long d3 = d(bArr, i + 16);
        long d4 = d(bArr, i + 24);
        long j3 = j + d;
        long j4 = d2 + j3 + d3;
        jArr[0] = j4 + d4;
        jArr[1] = Long.rotateRight(j2 + j3 + d4, 21) + Long.rotateRight(j4, 44) + j3;
    }

    public static long d(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }

    public static long zza(byte[] bArr) {
        int length = bArr.length;
        if (length < 0 || length > bArr.length) {
            StringBuilder sb = new StringBuilder(67);
            sb.append("Out of bound index with offput: 0 and length: ");
            sb.append(length);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int i = 37;
        char c = 0;
        if (length <= 32) {
            if (length > 16) {
                long j = (length << 1) - 7286425919675154353L;
                long d = d(bArr, 0) * (-5435081209227447693L);
                long d2 = d(bArr, 8);
                int i2 = length + 0;
                long d3 = d(bArr, i2 - 8) * j;
                return b(Long.rotateRight(d + d2, 43) + Long.rotateRight(d3, 30) + (d(bArr, i2 - 16) * (-7286425919675154353L)), d + Long.rotateRight(d2 - 7286425919675154353L, 18) + d3, j);
            } else if (length >= 8) {
                long j2 = (length << 1) - 7286425919675154353L;
                long d4 = d(bArr, 0) - 7286425919675154353L;
                long d5 = d(bArr, (length + 0) - 8);
                return b((Long.rotateRight(d5, 37) * j2) + d4, (Long.rotateRight(d4, 25) + d5) * j2, j2);
            } else if (length >= 4) {
                return b(length + ((a(bArr, 0) & 4294967295L) << 3), a(bArr, (length + 0) - 4) & 4294967295L, (length << 1) - 7286425919675154353L);
            } else if (length > 0) {
                long j3 = (((bArr[0] & 255) + ((bArr[(length >> 1) + 0] & 255) << 8)) * (-7286425919675154353L)) ^ ((length + ((bArr[(length - 1) + 0] & 255) << 2)) * (-4348849565147123417L));
                return (j3 ^ (j3 >>> 47)) * (-7286425919675154353L);
            } else {
                return -7286425919675154353L;
            }
        } else if (length <= 64) {
            long j4 = (length << 1) - 7286425919675154353L;
            long d6 = d(bArr, 0) * (-7286425919675154353L);
            long d7 = d(bArr, 8);
            int i3 = length + 0;
            long d8 = d(bArr, i3 - 8) * j4;
            long rotateRight = Long.rotateRight(d6 + d7, 43) + Long.rotateRight(d8, 30) + (d(bArr, i3 - 16) * (-7286425919675154353L));
            long b = b(rotateRight, Long.rotateRight((-7286425919675154353L) + d7, 18) + d6 + d8, j4);
            long d9 = d(bArr, 16) * j4;
            long d10 = d(bArr, 24);
            long d11 = (rotateRight + d(bArr, i3 - 32)) * j4;
            return b(Long.rotateRight(d9 + d10, 43) + Long.rotateRight(d11, 30) + ((b + d(bArr, i3 - 24)) * j4), d9 + Long.rotateRight(d10 + d6, 18) + d11, j4);
        } else {
            long j5 = 2480279821605975764L;
            long j6 = 1390051526045402406L;
            long[] jArr = new long[2];
            long[] jArr2 = new long[2];
            long d12 = d(bArr, 0) + 95310865018149119L;
            int i4 = length - 1;
            int i5 = ((i4 / 64) << 6) + 0;
            int i6 = i4 & 63;
            int i7 = (i5 + i6) - 63;
            int i8 = 0;
            while (true) {
                long rotateRight2 = (Long.rotateRight(((d12 + j5) + jArr[c]) + d(bArr, i8 + 8), i) * (-5435081209227447693L)) ^ jArr2[1];
                long rotateRight3 = (Long.rotateRight(j5 + jArr[1] + d(bArr, i8 + 48), 42) * (-5435081209227447693L)) + jArr[0] + d(bArr, i8 + 40);
                long rotateRight4 = Long.rotateRight(j6 + jArr2[0], 33) * (-5435081209227447693L);
                int i9 = i6;
                int i10 = i5;
                c(bArr, i8, jArr[1] * (-5435081209227447693L), rotateRight2 + jArr2[0], jArr);
                c(bArr, i8 + 32, rotateRight4 + jArr2[1], rotateRight3 + d(bArr, i8 + 16), jArr2);
                int i11 = i8 + 64;
                if (i11 == i10) {
                    long j7 = ((255 & rotateRight2) << 1) - 5435081209227447693L;
                    jArr2[0] = jArr2[0] + i9;
                    jArr[0] = jArr[0] + jArr2[0];
                    jArr2[0] = jArr2[0] + jArr[0];
                    long rotateRight5 = (Long.rotateRight(((rotateRight4 + rotateRight3) + jArr[0]) + d(bArr, i7 + 8), 37) * j7) ^ (jArr2[1] * 9);
                    long rotateRight6 = (Long.rotateRight(rotateRight3 + jArr[1] + d(bArr, i7 + 48), 42) * j7) + (jArr[0] * 9) + d(bArr, i7 + 40);
                    long rotateRight7 = Long.rotateRight(rotateRight2 + jArr2[0], 33) * j7;
                    c(bArr, i7, jArr[1] * j7, rotateRight5 + jArr2[0], jArr);
                    c(bArr, i7 + 32, jArr2[1] + rotateRight7, d(bArr, i7 + 16) + rotateRight6, jArr2);
                    return b(b(jArr[0], jArr2[0], j7) + (((rotateRight6 >>> 47) ^ rotateRight6) * (-4348849565147123417L)) + rotateRight5, b(jArr[1], jArr2[1], j7) + rotateRight7, j7);
                }
                i8 = i11;
                i5 = i10;
                i6 = i9;
                j6 = rotateRight2;
                d12 = rotateRight4;
                i = 37;
                c = 0;
                j5 = rotateRight3;
            }
        }
    }
}
