package com.google.common.hash;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
/* loaded from: classes10.dex */
public final class j extends e {
    public static final HashFunction h = new j();

    @VisibleForTesting
    public static long a(byte[] bArr, int i, int i2) {
        if (i2 <= 32) {
            if (i2 <= 16) {
                return b(bArr, i, i2);
            }
            return d(bArr, i, i2);
        } else if (i2 <= 64) {
            return e(bArr, i, i2);
        } else {
            return f(bArr, i, i2);
        }
    }

    public static long b(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = (i2 * 2) - 7286425919675154353L;
            long b = m.b(bArr, i) - 7286425919675154353L;
            long b2 = m.b(bArr, (i + i2) - 8);
            return c((Long.rotateRight(b2, 37) * j) + b, (Long.rotateRight(b, 25) + b2) * j, j);
        } else if (i2 >= 4) {
            return c(i2 + ((m.a(bArr, i) & 4294967295L) << 3), m.a(bArr, (i + i2) - 4) & 4294967295L, (i2 * 2) - 7286425919675154353L);
        } else if (i2 > 0) {
            return g((((bArr[i] & 255) + ((bArr[(i2 >> 1) + i] & 255) << 8)) * (-7286425919675154353L)) ^ ((i2 + ((bArr[i + (i2 - 1)] & 255) << 2)) * (-4348849565147123417L))) * (-7286425919675154353L);
        } else {
            return -7286425919675154353L;
        }
    }

    public static long c(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static long d(byte[] bArr, int i, int i2) {
        long j = (i2 * 2) - 7286425919675154353L;
        long b = m.b(bArr, i) * (-5435081209227447693L);
        long b2 = m.b(bArr, i + 8);
        int i3 = i + i2;
        long b3 = m.b(bArr, i3 - 8) * j;
        return c((m.b(bArr, i3 - 16) * (-7286425919675154353L)) + Long.rotateRight(b + b2, 43) + Long.rotateRight(b3, 30), b + Long.rotateRight(b2 - 7286425919675154353L, 18) + b3, j);
    }

    public static long e(byte[] bArr, int i, int i2) {
        long j = (i2 * 2) - 7286425919675154353L;
        long b = m.b(bArr, i) * (-7286425919675154353L);
        long b2 = m.b(bArr, i + 8);
        int i3 = i + i2;
        long b3 = m.b(bArr, i3 - 8) * j;
        long rotateRight = Long.rotateRight(b + b2, 43) + Long.rotateRight(b3, 30) + (m.b(bArr, i3 - 16) * (-7286425919675154353L));
        long c = c(rotateRight, b3 + Long.rotateRight(b2 - 7286425919675154353L, 18) + b, j);
        long b4 = m.b(bArr, i + 16) * j;
        long b5 = m.b(bArr, i + 24);
        long b6 = (rotateRight + m.b(bArr, i3 - 32)) * j;
        return c(((c + m.b(bArr, i3 - 24)) * j) + Long.rotateRight(b4 + b5, 43) + Long.rotateRight(b6, 30), b4 + Long.rotateRight(b5 + b, 18) + b6, j);
    }

    public static long f(byte[] bArr, int i, int i2) {
        long g = g(-7956866745689871395L) * (-7286425919675154353L);
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        long b = 95310865018149119L + m.b(bArr, i);
        int i3 = i2 - 1;
        int i4 = i + ((i3 / 64) * 64);
        int i5 = i3 & 63;
        int i6 = (i4 + i5) - 63;
        long j = 2480279821605975764L;
        int i7 = i;
        while (true) {
            long rotateRight = (Long.rotateRight(((b + j) + jArr[0]) + m.b(bArr, i7 + 8), 37) * (-5435081209227447693L)) ^ jArr2[1];
            long rotateRight2 = (Long.rotateRight(j + jArr[1] + m.b(bArr, i7 + 48), 42) * (-5435081209227447693L)) + jArr[0] + m.b(bArr, i7 + 40);
            long rotateRight3 = Long.rotateRight(g + jArr2[0], 33) * (-5435081209227447693L);
            h(bArr, i7, jArr[1] * (-5435081209227447693L), rotateRight + jArr2[0], jArr);
            h(bArr, i7 + 32, rotateRight3 + jArr2[1], rotateRight2 + m.b(bArr, i7 + 16), jArr2);
            i7 += 64;
            if (i7 == i4) {
                long j2 = ((rotateRight & 255) << 1) - 5435081209227447693L;
                jArr2[0] = jArr2[0] + i5;
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = (Long.rotateRight(((rotateRight3 + rotateRight2) + jArr[0]) + m.b(bArr, i6 + 8), 37) * j2) ^ (jArr2[1] * 9);
                long rotateRight5 = (Long.rotateRight(rotateRight2 + jArr[1] + m.b(bArr, i6 + 48), 42) * j2) + (jArr[0] * 9) + m.b(bArr, i6 + 40);
                long rotateRight6 = Long.rotateRight(rotateRight + jArr2[0], 33) * j2;
                h(bArr, i6, jArr[1] * j2, rotateRight4 + jArr2[0], jArr);
                h(bArr, i6 + 32, rotateRight6 + jArr2[1], m.b(bArr, i6 + 16) + rotateRight5, jArr2);
                return c(c(jArr[0], jArr2[0], j2) + (g(rotateRight5) * (-4348849565147123417L)) + rotateRight4, c(jArr[1], jArr2[1], j2) + rotateRight6, j2);
            }
            g = rotateRight;
            j = rotateRight2;
            b = rotateRight3;
        }
    }

    public static long g(long j) {
        return j ^ (j >>> 47);
    }

    public static void h(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long b = m.b(bArr, i);
        long b2 = m.b(bArr, i + 8);
        long b3 = m.b(bArr, i + 16);
        long b4 = m.b(bArr, i + 24);
        long j3 = j + b;
        long j4 = b2 + j3 + b3;
        jArr[0] = j4 + b4;
        jArr[1] = Long.rotateRight(j2 + j3 + b4, 21) + Long.rotateRight(j4, 44) + j3;
    }

    @Override // com.google.common.hash.HashFunction
    public int bits() {
        return 64;
    }

    @Override // com.google.common.hash.c, com.google.common.hash.HashFunction
    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        return HashCode.fromLong(a(bArr, i, i2));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }
}
