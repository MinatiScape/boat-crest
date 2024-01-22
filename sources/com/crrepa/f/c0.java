package com.crrepa.f;

import java.nio.charset.StandardCharsets;
/* loaded from: classes9.dex */
public class c0 {
    public static byte[] a(String str) {
        return d1.a(69, b(str));
    }

    public static byte[] b(String str) {
        int i = 24;
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i -= str.substring(i2, i4).getBytes(StandardCharsets.UTF_8).length;
            if (i <= 0) {
                break;
            }
            i3++;
            i2 = i4;
        }
        return str.substring(0, i3).getBytes(StandardCharsets.UTF_8);
    }
}
