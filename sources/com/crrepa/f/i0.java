package com.crrepa.f;

import android.text.TextUtils;
import com.crrepa.ble.conn.bean.CRPContactInfo;
import java.nio.charset.StandardCharsets;
/* loaded from: classes9.dex */
public class i0 {
    public static byte[] a() {
        return d1.a(-14, new byte[]{-17});
    }

    public static byte[] a(byte b) {
        return d1.a(-14, new byte[]{4, b});
    }

    public static byte[] a(CRPContactInfo cRPContactInfo) {
        byte[] bArr = new byte[49];
        bArr[0] = 2;
        bArr[1] = (byte) cRPContactInfo.getId();
        bArr[2] = 1;
        bArr[3] = (byte) cRPContactInfo.getWidth();
        bArr[4] = (byte) cRPContactInfo.getHeight();
        byte[] a2 = com.crrepa.i0.e.a(cRPContactInfo.getAddress());
        System.arraycopy(a2, 0, bArr, 5, a2.length);
        byte[] b = b(cRPContactInfo.getName(), 20, 19);
        System.arraycopy(b, 0, bArr, 9, b.length);
        byte[] b2 = b(cRPContactInfo.getNumber(), 20, 19);
        System.arraycopy(b2, 0, bArr, 29, b2.length);
        return d1.a(-14, bArr);
    }

    public static byte[] b() {
        return d1.a(-14, new byte[]{com.crrepa.j.o.c});
    }

    public static byte[] b(byte b) {
        return d1.a(-14, new byte[]{1, b});
    }

    public static byte[] b(String str, int i, int i2) {
        byte[] bytes;
        byte[] bArr = new byte[i];
        if (!TextUtils.isEmpty(str) && i2 < i) {
            int length = str.length();
            if (i2 < length) {
                length = i2;
            }
            do {
                bytes = str.substring(0, length).getBytes(StandardCharsets.UTF_8);
                length--;
            } while (i2 < bytes.length);
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        }
        return bArr;
    }

    public static byte[] c() {
        return d1.a(-14, new byte[]{0});
    }
}
