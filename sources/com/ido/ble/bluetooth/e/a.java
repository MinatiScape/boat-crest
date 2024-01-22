package com.ido.ble.bluetooth.e;

import android.util.Log;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12114a = "DFUServiceParser";
    private static final int b = 6;
    private static final int c = 2;
    private static final String d = "2148";
    private static final String e = "-289";

    public static boolean a(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        try {
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                byte b2 = bArr[i];
                if (b2 == 0) {
                    return false;
                }
                int i2 = i + 1;
                byte b3 = bArr[i2];
                if (b3 == 6) {
                    return a(bArr, i2 + 1, b2 - 1);
                }
                if (b3 == 2) {
                    return b(bArr, i2 + 1, b2 - 1);
                }
                i = i2 + (b2 - 1) + 1;
            }
        } catch (Exception e2) {
            LogTool.b(f12114a, e2.getMessage());
        }
        return false;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        Log.d(f12114a, "StartPosition: " + i + " Data length: " + i2);
        if (bArr == null || bArr.length == 0 || (i3 = i + i2) < 4 || i3 - 4 > bArr.length) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Byte.toString(bArr[i3 - 3]));
        sb.append(Byte.toString(bArr[i4]));
        return sb.toString().equals(d);
    }

    private static boolean b(byte[] bArr, int i, int i2) {
        Log.d(f12114a, "StartPosition: " + i + " Data length: " + i2);
        if (bArr == null || bArr.length == 0 || i2 < 2) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Byte.toString(bArr[i + 1]));
        sb.append(Byte.toString(bArr[i]));
        return sb.toString().equals(e);
    }
}
