package com.crrepa.i0;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes9.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f7730a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};
    public static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static MessageDigest c;

    static {
        try {
            c = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static byte a(char c2) {
        return (byte) "0123456789ABCDEF".indexOf(c2);
    }

    public static int a(char c2, int i) {
        int digit = Character.digit(c2, 16);
        if (digit != -1) {
            return digit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + c2 + " at index " + i);
    }

    public static String a(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                fileInputStream.close();
                return d(c.digest());
            }
            c.update(bArr, 0, read);
        }
    }

    public static String a(byte[] bArr, int i) {
        return c(new byte[]{bArr[i]});
    }

    public static boolean a(String str, File file) {
        if (!TextUtils.isEmpty(str) && file != null && file.exists()) {
            String str2 = null;
            try {
                str2 = a(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str2 != null && TextUtils.equals(str.toLowerCase(), str2.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (a(charArray[i2 + 1]) | (a(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static byte[] a(char[] cArr) {
        int length = cArr.length;
        if ((length & 1) == 0) {
            byte[] bArr = new byte[length >> 1];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i + 1;
                i = i3 + 1;
                bArr[i2] = (byte) (((a(cArr[i], i) << 4) | a(cArr[i3], i3)) & 255);
                i2++;
            }
            return bArr;
        }
        throw new RuntimeException("Odd number of characters.");
    }

    public static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? f7730a : b);
    }

    public static char[] a(byte[] bArr, char[] cArr) {
        if (bArr == null) {
            return null;
        }
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

    public static String b(byte[] bArr, boolean z) {
        return b(bArr, z ? f7730a : b);
    }

    public static String b(byte[] bArr, char[] cArr) {
        return new String(a(bArr, cArr));
    }

    public static char[] b(byte[] bArr) {
        return a(bArr, true);
    }

    public static String c(byte[] bArr) {
        return b(bArr, true);
    }

    public static String d(byte[] bArr) {
        return e(bArr, 0, bArr.length);
    }

    public static String e(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            f(bArr[i], stringBuffer);
            i++;
        }
        return stringBuffer.toString();
    }

    public static void f(byte b2, StringBuffer stringBuffer) {
        char[] cArr = f7730a;
        char c2 = cArr[(b2 & 240) >> 4];
        char c3 = cArr[b2 & 15];
        stringBuffer.append(c2);
        stringBuffer.append(c3);
    }
}
