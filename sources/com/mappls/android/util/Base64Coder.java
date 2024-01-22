package com.mappls.android.util;
/* loaded from: classes11.dex */
public class Base64Coder {
    private static final char[] map1 = new char[64];
    private static final byte[] map2;

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = map1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        map2 = new byte[128];
        int i2 = 0;
        while (true) {
            byte[] bArr = map2;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            map2[map1[i3]] = (byte) i3;
        }
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(char[] cArr) {
        int i;
        char c;
        int length = cArr.length;
        if (length % 4 == 0) {
            while (length > 0 && cArr[length - 1] == '=') {
                length--;
            }
            int i2 = (length * 3) / 4;
            byte[] bArr = new byte[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4 = i) {
                int i5 = i4 + 1;
                char c2 = cArr[i4];
                i = i5 + 1;
                char c3 = cArr[i5];
                char c4 = 'A';
                if (i < length) {
                    c = cArr[i];
                    i++;
                } else {
                    c = 'A';
                }
                if (i < length) {
                    c4 = cArr[i];
                    i++;
                }
                if (c2 > 127 || c3 > 127 || c > 127 || c4 > 127) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                byte[] bArr2 = map2;
                byte b = bArr2[c2];
                byte b2 = bArr2[c3];
                byte b3 = bArr2[c];
                byte b4 = bArr2[c4];
                if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                int i6 = (b << 2) | (b2 >>> 4);
                int i7 = ((b2 & 15) << 4) | (b3 >>> 2);
                int i8 = b4 | ((b3 & 3) << 6);
                int i9 = i3 + 1;
                bArr[i3] = (byte) i6;
                if (i9 < i2) {
                    bArr[i9] = (byte) i7;
                    i9++;
                }
                if (i9 < i2) {
                    bArr[i9] = (byte) i8;
                    i3 = i9 + 1;
                } else {
                    i3 = i9;
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }

    public static String decodeString(String str) {
        return new String(decode(str));
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        int i2;
        int i3;
        int i4 = ((i * 4) + 2) / 3;
        char[] cArr = new char[((i + 2) / 3) * 4];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i) {
            int i7 = i5 + 1;
            int i8 = bArr[i5] & 255;
            if (i7 < i) {
                i2 = bArr[i7] & 255;
                i7++;
            } else {
                i2 = 0;
            }
            if (i7 < i) {
                i3 = bArr[i7] & 255;
                i7++;
            } else {
                i3 = 0;
            }
            int i9 = i8 >>> 2;
            int i10 = ((i8 & 3) << 4) | (i2 >>> 4);
            int i11 = ((i2 & 15) << 2) | (i3 >>> 6);
            int i12 = i3 & 63;
            int i13 = i6 + 1;
            char[] cArr2 = map1;
            cArr[i6] = cArr2[i9];
            int i14 = i13 + 1;
            cArr[i13] = cArr2[i10];
            char c = '=';
            cArr[i14] = i14 < i4 ? cArr2[i11] : '=';
            int i15 = i14 + 1;
            if (i15 < i4) {
                c = cArr2[i12];
            }
            cArr[i15] = c;
            i6 = i15 + 1;
            i5 = i7;
        }
        return cArr;
    }

    public static String encodeString(String str) {
        return new String(encode(str.getBytes()));
    }
}
