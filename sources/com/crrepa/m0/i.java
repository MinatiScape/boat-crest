package com.crrepa.m0;

import android.content.Context;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Semaphore;
/* loaded from: classes9.dex */
public class i {
    public static boolean b = false;

    /* renamed from: a  reason: collision with root package name */
    public Semaphore f7766a = new Semaphore(1);

    public static float a(Context context, float f) {
        return f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    public static int a(String str) {
        return Integer.parseInt(new BigInteger(str, 2).toString(), 16);
    }

    public static int a(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            return 0;
        }
        return ((bArr[0] & 255) << 24) | (bArr[3] & 255) | ((bArr[2] & 255) << 8) | ((bArr[1] & 255) << 16);
    }

    public static String a(int i) {
        return new BigInteger(String.valueOf(i)).toString(2);
    }

    public static String a(String str, int i) {
        StringBuilder sb = new StringBuilder();
        int length = i > str.length() ? i - str.length() : 0;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 < length) {
                sb.append(BleConst.GetDeviceTime);
            } else {
                sb.append(str.charAt(i2 - length));
            }
        }
        return sb.toString();
    }

    public static void a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return;
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        bArr[2] = (byte) ((i >> 16) & 255);
        bArr[3] = (byte) ((i >> 24) & 255);
    }

    public static void a(long j, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return;
        }
        bArr[0] = (byte) (j & 255);
        bArr[1] = (byte) ((j >> 8) & 255);
    }

    public static void a(byte[] bArr, int i, byte b2, int i2) {
        if (bArr.length < i + i2) {
            return;
        }
        while (i < i2) {
            bArr[i] = 0;
            i++;
        }
    }

    public static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int b(Context context, float f) {
        return (int) (f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    public static int b(byte[] bArr) {
        if (bArr == null || bArr.length != 4) {
            return 0;
        }
        return ((bArr[3] & 255) << 24) | (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16);
    }

    public static String b(String str) {
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i2]) * 16) + "0123456789ABCDEF".indexOf(charArray[i2 + 1])) & 255);
        }
        return new String(bArr);
    }

    public static void b(long j, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return;
        }
        bArr[0] = (byte) (j & 255);
        bArr[1] = (byte) ((j >> 8) & 255);
        bArr[2] = (byte) ((j >> 16) & 255);
        bArr[3] = (byte) ((j >> 24) & 255);
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString + HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }

    public static String d(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(charArray[(bytes[i] & 240) >> 4]);
            sb.append(charArray[bytes[i] & 15]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static void d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        int length = bArr.length - 1;
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b2 = bArr[i];
            bArr[i] = bArr[length];
            bArr[length] = b2;
            length--;
        }
    }

    public static byte[] e(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        int length = lowerCase.length() / 2;
        byte[] bArr = new byte[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = (byte) ((((byte) (Character.digit(lowerCase.charAt(i), 16) & 255)) << 4) | ((byte) (Character.digit(lowerCase.charAt(i + 1), 16) & 255)));
            i += 2;
        }
        return bArr;
    }

    public Properties a(Context context, String str) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void a(Context context, String str, Properties properties) {
        try {
            properties.store(new FileOutputStream(str, false), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.close();
    }

    public void a(String str, byte[] bArr) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public void b(String str, String str2) {
        try {
            this.f7766a.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(str, true);
            fileWriter.write(str2);
            fileWriter.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.f7766a.release();
    }

    public float c(Context context, float f) {
        return f / (context.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    public byte[] c(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[fileInputStream.available()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return bArr;
    }
}
