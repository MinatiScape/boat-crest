package com.jieli.jl_rcsp.util;

import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
/* loaded from: classes11.dex */
public class CHexConver {
    private static final String b = "0123456789ABCDEF";

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f12513a = b.toCharArray();

    private static String a(char c) {
        return c == '0' ? "0000" : c == '1' ? "0001" : c == '2' ? "0010" : c == '3' ? "0011" : c == '4' ? "0100" : c == '5' ? "0101" : c == '6' ? "0110" : c == '7' ? "0111" : c == '8' ? "1000" : c == '9' ? "1001" : (c == 'A' || c == 'a') ? "1010" : (c == 'B' || c == 'b') ? "1011" : (c == 'C' || c == 'c') ? "1100" : (c == 'D' || c == 'd') ? "1101" : (c == 'E' || c == 'e') ? "1110" : (c == 'F' || c == 'f') ? "1111" : "";
    }

    public static String byte2HexStr(byte[] bArr) {
        return bArr == null ? "" : byte2HexStr(bArr, bArr.length);
    }

    public static String byte2String(byte[] bArr, int i) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(String.valueOf((int) bArr[i2]));
        }
        return sb.toString();
    }

    public static String byteToBit(byte b2) {
        return "" + ((int) ((byte) ((b2 >> 7) & 1))) + ((int) ((byte) ((b2 >> 6) & 1))) + ((int) ((byte) ((b2 >> 5) & 1))) + ((int) ((byte) ((b2 >> 4) & 1))) + ((int) ((byte) ((b2 >> 3) & 1))) + ((int) ((byte) ((b2 >> 2) & 1))) + ((int) ((byte) ((b2 >> 1) & 1))) + ((int) ((byte) ((b2 >> 0) & 1)));
    }

    public static String byteToHexString(byte b2) {
        return intToHexString(b2 & 255);
    }

    public static int byteToInt(byte b2) {
        return b2 & 255;
    }

    public static int bytesLittleToInt(byte[] bArr, int i, int i2) {
        if (i2 == 4) {
            return (bArr[i] & 255) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
        } else if (i2 == 2) {
            return bytesToInt(bArr[i + 1], bArr[i]);
        } else {
            return 0;
        }
    }

    public static int bytesToInt(byte b2, byte b3) {
        return ((b2 & 255) << 8) + (b3 & 255);
    }

    public static int bytesToInt(byte[] bArr, int i, int i2) {
        if (i2 == 4) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return bytesToInt(bArr2);
        } else if (i2 == 2) {
            return bytesToInt(bArr[i], bArr[i + 1]);
        } else {
            return 0;
        }
    }

    public static long bytesToLong(byte[] bArr, int i, int i2) {
        long j = 0;
        int i3 = 0;
        int i4 = i;
        while (i4 < i + i2) {
            j |= (bArr[i4] & 255) << (((i2 - 1) - i3) * 8);
            i4++;
            i3++;
        }
        return j;
    }

    public static short bytesToShort(byte b2, byte b3) {
        return (short) bytesToInt(b2, b3);
    }

    public static String bytesToStr(byte[] bArr) {
        return bArr == null ? "" : hexStr2Str(byte2HexStr(bArr, bArr.length));
    }

    public static boolean checkHexStr(String str) {
        String upperCase;
        int length;
        if (str != null && (length = (upperCase = str.trim().replace(HexStringBuilder.DEFAULT_SEPARATOR, "").toUpperCase(Locale.US)).length()) > 1 && length % 2 == 0) {
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                if (!b.contains(upperCase.substring(i, i2))) {
                    return false;
                }
                i = i2;
            }
            return true;
        }
        return false;
    }

    public static byte decodeBinaryString(String str) {
        int parseInt;
        if (str == null) {
            return (byte) 0;
        }
        int length = str.length();
        if (length == 4 || length == 8) {
            if (length == 8) {
                if (str.charAt(0) == '0') {
                    parseInt = Integer.parseInt(str, 2);
                } else {
                    parseInt = Integer.parseInt(str, 2) + InputDeviceCompat.SOURCE_ANY;
                }
            } else {
                parseInt = Integer.parseInt(str, 2);
            }
            return (byte) parseInt;
        }
        return (byte) 0;
    }

    public static byte decodeHexChar(char c, char c2) {
        String a2 = a(c);
        String a3 = a(c2);
        if (TextUtils.isEmpty(a2)) {
            a2 = "";
        }
        if (!TextUtils.isEmpty(a3)) {
            a2 = a2 + a3;
        }
        if (TextUtils.isEmpty(a2)) {
            return (byte) 0;
        }
        return decodeBinaryString(a2);
    }

    public static int getBitByPosition(int i, int i2) {
        if (i2 < 0 || i2 >= 32) {
            return 0;
        }
        return (i >> i2) & 1;
    }

    public static byte[] getBooleanArray(byte b2) {
        byte[] bArr = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bArr[i] = (byte) (b2 & 1);
            b2 = (byte) (b2 >> 1);
        }
        return bArr;
    }

    public static byte[] getBooleanArrayBig(byte b2) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (b2 & 1);
            b2 = (byte) (b2 >> 1);
        }
        return bArr;
    }

    public static String hexDataCovetToAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length == 6) {
            for (int i = 0; i < bArr.length; i++) {
                char[] cArr = f12513a;
                sb.append(cArr[(bArr[i] & 255) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
                if (i != bArr.length - 1) {
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }

    public static byte[] hexStr2Bytes(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = str.trim().replace(HexStringBuilder.DEFAULT_SEPARATOR, "").toUpperCase(Locale.US);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) (Integer.decode(HexStringBuilder.DEFAULT_PREFIX + upperCase.substring(i2, i3) + upperCase.substring(i3, i3 + 1)).intValue() & 255);
        }
        return bArr;
    }

    public static String hexStr2Str(String str) {
        if (str == null) {
            return null;
        }
        String upperCase = str.trim().replace(HexStringBuilder.DEFAULT_SEPARATOR, "").toUpperCase(Locale.US);
        char[] charArray = upperCase.toCharArray();
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((b.indexOf(charArray[i2 + 1]) | (b.indexOf(charArray[i2]) << 4)) & 255);
        }
        try {
            return new String(bArr, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String int2HexStr(int[] iArr, int i) {
        if (iArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            char[] cArr = f12513a;
            sb.append(cArr[(((byte) iArr[i2]) & 255) >> 4]);
            sb.append(cArr[((byte) iArr[i2]) & 15]);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static byte[] int2byte2(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] intToBigBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte intToByte(int i) {
        return (byte) i;
    }

    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static String intToHexString(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    public static byte[] shortToBigBytes(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    public static byte[] shortToBytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }

    public static String str2HexStr(String str) {
        byte[] bArr = null;
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            bArr = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bArr == null) {
            return "";
        }
        for (byte b2 : bArr) {
            char[] cArr = f12513a;
            sb.append(cArr[(b2 & 255) >> 4]);
            sb.append(cArr[b2 & 15]);
        }
        return sb.toString().trim();
    }

    public static String strToUnicode(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            String hexString = Integer.toHexString(charAt);
            if (charAt > 128) {
                sb.append("\\u");
            } else {
                sb.append("\\u00");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String unicodeToString(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length() / 6;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            int i2 = i * 6;
            i++;
            String substring = str.substring(i2, i * 6);
            sb.append(new String(Character.toChars(Integer.valueOf(substring.substring(4), 16).intValue() | (Integer.valueOf(substring.substring(2, 4), 16).intValue() << 8))));
        }
        return sb.toString();
    }

    public static String byte2HexStr(byte[] bArr, int i) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            char[] cArr = f12513a;
            sb.append(cArr[(bArr[i2] & 255) >> 4]);
            sb.append(cArr[bArr[i2] & 15]);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static int bytesToInt(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return 0;
        }
        return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }
}
