package com.szabh.androiddfu.goodix;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.PrintStream;
/* loaded from: classes12.dex */
public class HexUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f13716a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};
    public static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] HexString2Bytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        byte[] bytes = str.getBytes();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = uniteBytes(bytes[i2], bytes[i2 + 1]);
        }
        return bArr;
    }

    public static byte[] decodeHex(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((toDigit(cArr[i], i) << 4) | toDigit(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new RuntimeException("Odd number of characters.");
    }

    public static char[] encodeHex(byte[] bArr) {
        return encodeHex(bArr, true);
    }

    public static String encodeHexStr(byte[] bArr) {
        return encodeHexStr(bArr, true);
    }

    public static byte[] intToBytes(int i, int i2) {
        if (i2 > 4) {
            i2 = 4;
        }
        int i3 = (i2 - 1) * 8;
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) (i >> (i3 - (i4 * 8)));
        }
        return bArr;
    }

    public static void main(String[] strArr) {
        String encodeHexStr = encodeHexStr("待转换字符串".getBytes());
        String str = new String(decodeHex(encodeHexStr.toCharArray()));
        PrintStream printStream = System.out;
        printStream.println("转换前：待转换字符串");
        PrintStream printStream2 = System.out;
        printStream2.println("转换后：" + encodeHexStr);
        PrintStream printStream3 = System.out;
        printStream3.println("还原后：" + str);
    }

    public static byte[] stringToHexStr(String str) {
        int i;
        int i2;
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[str.length() / 2];
        for (int i3 = 0; i3 < bytes.length / 2; i3++) {
            int i4 = i3 * 2;
            if (bytes[i4] >= 97) {
                i = bytes[i4] - 87;
            } else if (bytes[i4] >= 65) {
                i = bytes[i4] - 55;
            } else {
                i = bytes[i4] - 48;
            }
            byte b2 = (byte) i;
            int i5 = i4 + 1;
            if (bytes[i5] >= 97) {
                i2 = bytes[i5] - 87;
            } else if (bytes[i5] >= 65) {
                i2 = bytes[i5] - 55;
            } else {
                i2 = bytes[i5] - 48;
            }
            bArr[i3] = (byte) ((((byte) i2) & 255) | ((b2 & 255) << 4));
        }
        return bArr;
    }

    public static int toDigit(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
    }

    public static byte uniteBytes(byte b2, byte b3) {
        return (byte) (((byte) (Byte.decode(HexStringBuilder.DEFAULT_PREFIX + new String(new byte[]{b2})).byteValue() << 4)) ^ Byte.decode(HexStringBuilder.DEFAULT_PREFIX + new String(new byte[]{b3})).byteValue());
    }

    public static char[] encodeHex(byte[] bArr, boolean z) {
        return encodeHex(bArr, z ? f13716a : b);
    }

    public static String encodeHexStr(byte[] bArr, boolean z) {
        return encodeHexStr(bArr, z ? f13716a : b);
    }

    public static char[] encodeHex(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static String encodeHexStr(byte[] bArr, char[] cArr) {
        return new String(encodeHex(bArr, cArr));
    }
}
