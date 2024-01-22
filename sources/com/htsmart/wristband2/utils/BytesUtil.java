package com.htsmart.wristband2.utils;

import androidx.annotation.NonNull;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.Locale;
/* loaded from: classes11.dex */
public class BytesUtil {
    public static String bytes2HexStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString().toUpperCase().trim();
    }

    public static int bytes2Int(byte[] bArr, int i, int i2, boolean z) {
        int i3;
        if (i2 < 0 || i2 > 4) {
            throw new IllegalArgumentException();
        }
        int i4 = 1;
        if (z) {
            int i5 = (i + i2) - 1;
            i3 = bArr[i5] & 255;
            while (i4 < i2) {
                i3 |= (bArr[i5 - i4] & 255) << (i4 * 8);
                i4++;
            }
        } else {
            i3 = bArr[i] & 255;
            while (i4 < i2) {
                i3 |= (bArr[i + i4] & 255) << (i4 * 8);
                i4++;
            }
        }
        return i3;
    }

    public static int bytes2Int(byte[] bArr, boolean z) {
        return bytes2Int(bArr, 0, 4, z);
    }

    public static long bytes2Long(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | (bArr[i] & 255);
        }
        return j;
    }

    public static long bytes2Long(byte[] bArr, int i, int i2, boolean z) {
        long j;
        if (i2 < 0 || i2 > 8) {
            throw new IllegalArgumentException();
        }
        int i3 = 1;
        if (z) {
            int i4 = (i + i2) - 1;
            j = bArr[i4] & 255;
            while (i3 < i2) {
                j |= (bArr[i4 - i3] & 255) << (i3 * 8);
                i3++;
            }
        } else {
            j = bArr[i] & 255;
            while (i3 < i2) {
                j |= (bArr[i + i3] & 255) << (i3 * 8);
                i3++;
            }
        }
        return j;
    }

    public static short bytes2Short(byte[] bArr, int i, int i2, boolean z) {
        short s;
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException();
        }
        int i3 = 1;
        if (z) {
            int i4 = (i + i2) - 1;
            s = (short) (bArr[i4] & 255);
            while (i3 < i2) {
                s = (short) (s | ((bArr[i4 - i3] & 255) << (i3 * 8)));
                i3++;
            }
        } else {
            s = (short) (bArr[i] & 255);
            while (i3 < i2) {
                s = (short) (s | ((bArr[i + i3] & 255) << (i3 * 8)));
                i3++;
            }
        }
        return s;
    }

    public static short bytes2Short(byte[] bArr, boolean z) {
        return bytes2Short(bArr, 0, 2, z);
    }

    public static byte[] hexStr2Bytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        String upperCase = str.toUpperCase();
        char[] charArray = upperCase.toCharArray();
        if (length % 2 == 1) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            if ((charArray[i] < '0' || charArray[i] > '9') && (charArray[i] < 'A' || charArray[i] > 'F')) {
                return null;
            }
        }
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(upperCase.charAt(i2), 16) << 4) + Character.digit(upperCase.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static byte[] int2Bytes(int i, boolean z) {
        byte[] bArr = new byte[4];
        if (z) {
            bArr[3] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            bArr[1] = (byte) ((i >> 16) & 255);
            bArr[0] = (byte) ((i >> 24) & 255);
        } else {
            bArr[0] = (byte) (i & 255);
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[2] = (byte) ((i >> 16) & 255);
            bArr[3] = (byte) ((i >> 24) & 255);
        }
        return bArr;
    }

    @NonNull
    public static String internalBytes2HexStr(@NonNull byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    public static byte[] long2Bytes(long j) {
        byte[] bArr = new byte[8];
        int i = 0;
        while (i < 8) {
            int i2 = i + 1;
            bArr[i] = (byte) ((j >> (64 - (i2 * 8))) & 255);
            i = i2;
        }
        return bArr;
    }

    public static byte[] short2Bytes(short s, boolean z) {
        byte[] bArr = new byte[2];
        if (z) {
            bArr[1] = (byte) (s & 255);
            bArr[0] = (byte) ((s >> 8) & 255);
        } else {
            bArr[0] = (byte) (s & 255);
            bArr[1] = (byte) ((s >> 8) & 255);
        }
        return bArr;
    }
}
