package com.abupdate.mqtt_libs.mqttv3.a.a;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1943a = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String a(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        int i = 0;
        while (length >= 3) {
            stringBuffer.append(b(((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8) | (bArr[i + 2] & 255), 4));
            i += 3;
            length -= 3;
        }
        if (length == 2) {
            stringBuffer.append(b(((bArr[i] & 255) << 8) | (bArr[i + 1] & 255), 3));
        }
        if (length == 1) {
            stringBuffer.append(b(bArr[i] & 255, 2));
        }
        return stringBuffer.toString();
    }

    public static final String b(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        while (i > 0) {
            i--;
            stringBuffer.append(f1943a[(int) (63 & j)]);
            j >>= 6;
        }
        return stringBuffer.toString();
    }

    public static byte[] a(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length * 3) / 4];
        int i = 0;
        int i2 = 0;
        while (true) {
            if (length < 4) {
                break;
            }
            long a2 = a(bytes, i, 4);
            length -= 4;
            i += 4;
            for (int i3 = 2; i3 >= 0; i3--) {
                bArr[i2 + i3] = (byte) (a2 & 255);
                a2 >>= 8;
            }
            i2 += 3;
        }
        if (length == 3) {
            long a3 = a(bytes, i, 3);
            for (int i4 = 1; i4 >= 0; i4--) {
                bArr[i2 + i4] = (byte) (a3 & 255);
                a3 >>= 8;
            }
        }
        if (length == 2) {
            bArr[i2] = (byte) (a(bytes, i, 2) & 255);
        }
        return bArr;
    }

    public static final long a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        long j = 0;
        while (i2 > 0) {
            i2--;
            int i4 = i + 1;
            byte b = bArr[i];
            long j2 = b == 47 ? 1L : 0L;
            if (b >= 48 && b <= 57) {
                j2 = (b + 2) - 48;
            }
            if (b >= 65 && b <= 90) {
                j2 = (b + 12) - 65;
            }
            if (b >= 97 && b <= 122) {
                j2 = (b + 38) - 97;
            }
            j += j2 << i3;
            i3 += 6;
            i = i4;
        }
        return j;
    }
}
