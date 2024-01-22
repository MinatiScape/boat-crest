package com.crrepa.f;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import kotlin.text.Typography;
/* loaded from: classes9.dex */
public class p0 {
    public static final byte b = 0;
    public static final byte c = 1;

    public static String a(String str) {
        int i = 120;
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i -= str.substring(i2, i4).getBytes(StandardCharsets.UTF_8).length;
            if (i <= 0) {
                return (String) TextUtils.concat(str.substring(0, i3), String.valueOf((char) Typography.ellipsis));
            }
            i3++;
            i2 = i4;
        }
        return str;
    }

    public static byte[] a() {
        return d1.a(68, new byte[]{-1});
    }

    public static byte[] a(byte b2, String str) {
        byte[] bytes = a(str).getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[bytes.length + 1];
        bArr[0] = b2;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        return d1.a(68, bArr);
    }
}
