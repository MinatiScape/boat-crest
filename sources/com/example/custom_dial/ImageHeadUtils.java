package com.example.custom_dial;

import com.coveiot.sdk.ble.api.BleUUID;
import java.nio.charset.Charset;
import java.util.Calendar;
/* loaded from: classes9.dex */
public class ImageHeadUtils {

    /* loaded from: classes9.dex */
    public enum a {
        timeTxt,
        point
    }

    public final byte[] a(boolean z) {
        byte[] bArr = new byte[4];
        if (!z) {
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = BleUUID.CMD_ID_A0;
            bArr[3] = 6;
        }
        return bArr;
    }

    public final byte[] b() {
        return new byte[8];
    }

    public final byte[] c(a aVar) {
        byte[] bArr = new byte[40];
        if (aVar == a.timeTxt) {
            bArr[0] = 1;
            bArr[1] = 6;
            bArr[2] = 6;
            bArr[3] = 1;
            bArr[4] = 6;
            bArr[5] = 6;
            bArr[6] = 5;
            bArr[7] = 5;
        } else if (aVar == a.point) {
            bArr[0] = 1;
            bArr[1] = 2;
            bArr[2] = 2;
            bArr[3] = 2;
        }
        return bArr;
    }

    public final byte[] d() {
        return new byte[4];
    }

    public final byte[] e(boolean z) {
        byte[] bArr = new byte[90];
        if (z) {
            byte[] bytes = "5526".getBytes(Charset.forName("UTF-8"));
            bArr[0] = bytes[0];
            bArr[1] = bytes[1];
            bArr[2] = bytes[2];
            bArr[3] = bytes[3];
        }
        return bArr;
    }

    public final byte[] f() {
        byte[] bArr = new byte[32];
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(5);
        int i3 = calendar.get(10);
        int i4 = calendar.get(12);
        int i5 = calendar.get(13);
        bArr[0] = (byte) i;
        bArr[1] = (byte) (i >> 8);
        bArr[2] = (byte) (i >> 16);
        bArr[3] = (byte) (i >> 24);
        bArr[4] = (byte) (calendar.get(2) + 1);
        bArr[5] = (byte) i2;
        bArr[6] = (byte) i3;
        bArr[7] = (byte) i4;
        bArr[8] = (byte) i5;
        return bArr;
    }

    public final byte[] g() {
        return new byte[6];
    }

    public byte[] getFixHeaderInfo(boolean z, a aVar) {
        byte[] a2 = a(z);
        byte[] b = b();
        byte[] g = g();
        byte[] e = e(z);
        byte[] f = f();
        byte[] d = d();
        byte[] c = c(aVar);
        byte[] bArr = new byte[a2.length + b.length + g.length + e.length + f.length + d.length + c.length];
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        System.arraycopy(b, 0, bArr, a2.length, b.length);
        System.arraycopy(g, 0, bArr, a2.length + b.length, g.length);
        System.arraycopy(e, 0, bArr, a2.length + b.length + g.length, e.length);
        System.arraycopy(f, 0, bArr, a2.length + b.length + g.length + e.length, f.length);
        System.arraycopy(d, 0, bArr, a2.length + b.length + g.length + e.length + f.length, d.length);
        System.arraycopy(c, 0, bArr, a2.length + b.length + g.length + e.length + f.length + d.length, c.length);
        return bArr;
    }
}
