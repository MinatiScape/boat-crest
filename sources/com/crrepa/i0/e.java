package com.crrepa.i0;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes9.dex */
public class e {
    public static byte a(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    public static int a(byte b) {
        return b & 255;
    }

    public static int a(byte b, byte b2) {
        return (b << 8) | (b2 & 255);
    }

    public static int a(byte[] bArr) {
        return ((bArr[0] << 24) >>> 8) | (bArr[2] & 255) | ((bArr[1] << 8) & 65280);
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] a(long j) {
        return new byte[]{(byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)};
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return bArr;
    }

    public static int b(byte b, byte b2) {
        return ((b & 255) << 8) + (b2 & 255);
    }

    public static int b(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (a(str.charAt(i2)) > 0) {
                i = (int) (i + Math.pow(2.0d, (length - i2) - 1));
            }
        }
        return i;
    }

    public static int b(byte[] bArr) {
        return ((bArr[0] & 255) << 24) + ((bArr[1] & 255) << 16) + ((bArr[2] & 255) << 8) + (bArr[3] & 255);
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }

    public static long d(byte[] bArr) {
        return ((bArr[3] & 255) << 24) + ((bArr[2] & 255) << 16) + ((bArr[1] & 255) << 8) + (bArr[0] & 255);
    }

    public static boolean e(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

    public static boolean f(byte[] bArr) {
        for (byte b : bArr) {
            if (b != -1) {
                return true;
            }
        }
        return false;
    }

    public static int g(byte[] bArr) {
        if (f(bArr)) {
            return ((bArr[2] << 24) >>> 8) | (bArr[0] & 255) | ((bArr[1] << 8) & 65280);
        }
        return 0;
    }

    public static String h(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append("0123456789abcdef".charAt(b % 16));
        }
        return sb.toString();
    }
}
