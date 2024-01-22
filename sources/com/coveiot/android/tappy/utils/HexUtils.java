package com.coveiot.android.tappy.utils;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes7.dex */
public class HexUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f6014a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bin2hex(byte[] bArr) {
        return encodeHexString_imp(bArr);
    }

    @Deprecated
    public static byte[] decodeHexString(String str) {
        return decodeHexString_imp(str);
    }

    public static byte[] decodeHexString_imp(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int digit = Character.digit(charArray[i], 16) << 4;
                if (digit >= 0) {
                    int i3 = i + 1;
                    int digit2 = digit | Character.digit(charArray[i3], 16);
                    if (digit2 >= 0) {
                        i = i3 + 1;
                        bArr[i2] = (byte) (digit2 & 255);
                        i2++;
                    } else {
                        throw new IllegalArgumentException("Illegal hex: " + charArray[i3]);
                    }
                } else {
                    throw new IllegalArgumentException("Illegal hex: " + charArray[i]);
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Odd number of characters: " + str);
    }

    @Deprecated
    public static String encodeHexString(byte[] bArr) {
        return encodeHexString_imp(bArr);
    }

    public static String encodeHexString_imp(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6014a;
            cArr[i] = cArr2[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static byte[] hex2bin(String str) {
        return decodeHexString_imp(str);
    }

    public static byte[] stringToBin(String str) {
        return decodeHexString_imp(str.toUpperCase().replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "").replaceAll(":", "").replaceAll("0X", "").replaceAll("\n", "").replaceAll("\t", "").replaceAll(";", ""));
    }

    public static String bin2hex(byte b) {
        return encodeHexString_imp(b);
    }

    public static String encodeHexString_imp(byte b) {
        char[] cArr = new char[2];
        int i = 0;
        for (int i2 = 0; i2 < 1; i2++) {
            int i3 = i + 1;
            char[] cArr2 = f6014a;
            cArr[i] = cArr2[(b & 240) >>> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[b & 15];
        }
        return new String(cArr);
    }
}
