package com.coveiot.android.bleabstract;

import com.htsmart.wristband2.a.a.a;
/* loaded from: classes2.dex */
public class JstyleResolveData {
    public static byte cmdCheck = -110;

    public static int CalcCRC16(byte[] bArr, int i) {
        int i2 = 65535;
        int i3 = 0;
        while (i > 0) {
            i--;
            int i4 = i3 + 1;
            i2 ^= bArr[i3] & 255;
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = i2 & 1;
                i2 >>= 1;
                if (i6 == 1) {
                    i2 ^= 40961;
                }
            }
            i3 = i4;
        }
        return i2;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, int i, int i2) {
        int length = bArr.length;
        int CalcCRC16 = CalcCRC16(bArr, bArr.length);
        byte[] bArr3 = new byte[27];
        bArr3[0] = cmdCheck;
        bArr3[1] = (byte) i;
        bArr3[2] = (byte) i2;
        bArr3[3] = (byte) (length & 255);
        bArr3[4] = (byte) ((length >> 8) & 255);
        bArr3[5] = (byte) ((length >> 16) & 255);
        bArr3[6] = (byte) ((length >> 24) & 255);
        bArr3[7] = (byte) (CalcCRC16 & 255);
        bArr3[8] = (byte) ((CalcCRC16 >> 8) & 255);
        System.arraycopy(bArr2, 0, bArr3, 9, bArr2.length);
        int CalcCRC162 = CalcCRC16(bArr3, 25);
        bArr3[25] = (byte) (CalcCRC162 & 255);
        bArr3[26] = (byte) ((CalcCRC162 >> 8) & 255);
        return bArr3;
    }

    public static String byte2Hex(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return "no data";
        }
        StringBuilder sb = new StringBuilder(bArr.length);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X ", Byte.valueOf(bArr[i])));
        }
        return sb.toString();
    }

    public static byte[] checkUpdateFile(byte[] bArr, byte[] bArr2, int i) {
        return a(bArr, bArr2, 1, i);
    }

    public static byte[] deleteAgpsFile() {
        int CalcCRC16 = CalcCRC16(r0, 2);
        byte[] bArr = {cmdCheck, a.J1, (byte) (CalcCRC16 & 255), (byte) ((CalcCRC16 >> 8) & 255)};
        return bArr;
    }

    public static byte[] deleteFile(int i) {
        int CalcCRC16 = CalcCRC16(r0, 7);
        byte[] bArr = {cmdCheck, a.J1, 0, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (CalcCRC16 & 255), (byte) ((CalcCRC16 >> 8) & 255)};
        return bArr;
    }

    public static byte[] endUpdateFile(byte[] bArr, byte[] bArr2, int i) {
        return a(bArr, bArr2, 2, i);
    }
}
