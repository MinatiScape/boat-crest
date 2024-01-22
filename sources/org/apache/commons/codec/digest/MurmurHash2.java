package org.apache.commons.codec.digest;

import org.apache.commons.codec.binary.StringUtils;
/* loaded from: classes12.dex */
public final class MurmurHash2 {
    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static long b(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public static int hash32(byte[] bArr, int i, int i2) {
        int i3 = i2 ^ i;
        int i4 = i >> 2;
        for (int i5 = 0; i5 < i4; i5++) {
            int a2 = a(bArr, i5 << 2) * 1540483477;
            i3 = (i3 * 1540483477) ^ ((a2 ^ (a2 >>> 24)) * 1540483477);
        }
        int i6 = i4 << 2;
        int i7 = i - i6;
        if (i7 != 1) {
            if (i7 != 2) {
                if (i7 == 3) {
                    i3 ^= (bArr[i6 + 2] & 255) << 16;
                }
                int i8 = ((i3 >>> 13) ^ i3) * 1540483477;
                return i8 ^ (i8 >>> 15);
            }
            i3 ^= (bArr[i6 + 1] & 255) << 8;
        }
        i3 = ((bArr[i6] & 255) ^ i3) * 1540483477;
        int i82 = ((i3 >>> 13) ^ i3) * 1540483477;
        return i82 ^ (i82 >>> 15);
    }

    public static long hash64(byte[] bArr, int i, int i2) {
        long j = (i2 & 4294967295L) ^ (i * (-4132994306676758123L));
        int i3 = i >> 3;
        for (int i4 = 0; i4 < i3; i4++) {
            long b = b(bArr, i4 << 3) * (-4132994306676758123L);
            j = (j ^ ((b ^ (b >>> 47)) * (-4132994306676758123L))) * (-4132994306676758123L);
        }
        int i5 = i3 << 3;
        switch (i - i5) {
            case 7:
                j ^= (bArr[i5 + 6] & 255) << 48;
            case 6:
                j ^= (bArr[i5 + 5] & 255) << 40;
            case 5:
                j ^= (bArr[i5 + 4] & 255) << 32;
            case 4:
                j ^= (bArr[i5 + 3] & 255) << 24;
            case 3:
                j ^= (bArr[i5 + 2] & 255) << 16;
            case 2:
                j ^= (bArr[i5 + 1] & 255) << 8;
            case 1:
                j = ((bArr[i5] & 255) ^ j) * (-4132994306676758123L);
                break;
        }
        long j2 = ((j >>> 47) ^ j) * (-4132994306676758123L);
        return j2 ^ (j2 >>> 47);
    }

    public static int hash32(byte[] bArr, int i) {
        return hash32(bArr, i, -1756908916);
    }

    public static int hash32(String str) {
        byte[] bytesUtf8 = StringUtils.getBytesUtf8(str);
        return hash32(bytesUtf8, bytesUtf8.length);
    }

    public static int hash32(String str, int i, int i2) {
        return hash32(str.substring(i, i2 + i));
    }

    public static long hash64(byte[] bArr, int i) {
        return hash64(bArr, i, -512093083);
    }

    public static long hash64(String str) {
        byte[] bytesUtf8 = StringUtils.getBytesUtf8(str);
        return hash64(bytesUtf8, bytesUtf8.length);
    }

    public static long hash64(String str, int i, int i2) {
        return hash64(str.substring(i, i2 + i));
    }
}
