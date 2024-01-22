package com.ido.ble.common;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f12145a = "UTF-8";

    public static int a(byte b) {
        return b >= 0 ? b : b + 128 + 128;
    }

    public static int a(byte[] bArr) {
        int length = bArr.length - 1;
        int i = 0;
        for (int i2 = length; i2 >= 0; i2--) {
            i += bArr[i2] << (length - i2);
        }
        return i;
    }

    public static int a(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i3 >= 0) {
            i4 = (i4 << 8) | (bArr[i] & 255);
            i3--;
            i++;
        }
        return i4;
    }

    public static <T> T a(T t) {
        int length = Array.getLength(t);
        T t2 = (T) Array.newInstance(t.getClass().getComponentType(), length);
        System.arraycopy(t, 0, t2, 0, length);
        for (int i = 0; i < length / 2; i++) {
            Object obj = Array.get(t2, i);
            int i2 = (length - i) - 1;
            Array.set(t2, i, Array.get(t2, i2));
            Array.set(t2, i2, obj);
        }
        return t2;
    }

    public static void a(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        while (i3 >= 0) {
            bArr[i] = (byte) (j >> (i3 * 8));
            i3--;
            i++;
        }
    }

    public static void a(byte[] bArr, byte b, int i, int i2) {
        int i3 = i2 + i;
        int i4 = 0;
        while (i < i3) {
            byte b2 = bArr[i4];
            i++;
            i4++;
        }
    }

    public static void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            bArr2[i3] = bArr[i];
            i3++;
            i++;
        }
    }

    public static byte[] a(int i) {
        byte b = (byte) i;
        byte[] bArr = new byte[8];
        for (int i2 = 0; i2 <= 7; i2++) {
            bArr[i2] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return bArr;
    }

    public static byte[] a(int i, int i2) {
        byte[] bArr = new byte[i2];
        int i3 = i2 - 1;
        int i4 = 0;
        while (i3 >= 0) {
            bArr[i4] = (byte) (i >> (i3 * 8));
            i3--;
            i4++;
        }
        return bArr;
    }

    public static byte[] a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i3 - 1;
        while (i4 >= 0) {
            bArr[i2] = (byte) (i >> (i4 * 8));
            i4--;
            i2++;
        }
        return bArr;
    }

    public static byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = i - 1;
        int i3 = 0;
        while (i2 >= 0) {
            bArr[i3] = (byte) (j >> (i2 * 8));
            i2--;
            i3++;
        }
        return bArr;
    }

    public static byte[] a(String str) {
        try {
            File file = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
            byte[] bArr = new byte[1000];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(String str, String str2) {
        if (str != null) {
            try {
                if (!str.trim().equals("")) {
                    return str.getBytes(str2);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return new byte[0];
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte b(int i) {
        return (byte) (i & 255);
    }

    public static long b(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        long j = 0;
        while (i3 >= 0) {
            j = (j << 8) | (bArr[i] & 255);
            i3--;
            i++;
        }
        return j;
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }

    public static byte[] b(String str) {
        return a(str, f12145a);
    }

    public static byte c(int i) {
        return (byte) Integer.toHexString(i).charAt(0);
    }

    public static String c(byte[] bArr, int i, int i2) {
        if (bArr.length < i + i2) {
            return null;
        }
        String str = "";
        for (int i3 = 0; i3 < i2; i3++) {
            str = str + String.format("%02X", Byte.valueOf(bArr[i + i3]));
        }
        return str;
    }

    public static byte[] c(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return bArr;
    }

    public static String[] c(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        String[] strArr = new String[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                hexString = 0 + hexString;
            }
            strArr[i] = hexString;
        }
        return strArr;
    }

    public static int d(byte[] bArr, int i, int i2) {
        int i3 = (i + i2) - 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            i5 = (i5 << 8) | (bArr[i3] & 255);
            i4++;
            i3--;
        }
        return i5;
    }

    public static String d(byte[] bArr) {
        try {
            return new String(bArr, f12145a);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] d(String str) {
        return a(str, f12145a);
    }
}
