package com.ido.ble.common;

import android.text.TextUtils;
import com.ido.ble.protocol.model.SupportFunctionInfo;
/* loaded from: classes11.dex */
public class i {
    private static String a(int i, String str) {
        byte[] d;
        if (i <= 0) {
            return str;
        }
        String str2 = "";
        if (TextUtils.isEmpty(str) || (d = c.d(str)) == null) {
            return "";
        }
        if (d.length <= i) {
            return str;
        }
        int i2 = 0;
        while (i2 < str.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            int i3 = i2 + 1;
            sb.append(str.substring(i2, i3));
            String sb2 = sb.toString();
            byte[] d2 = c.d(sb2);
            if (d2 == null) {
                return sb2;
            }
            if (d2.length > i) {
                return sb2.substring(0, i2);
            }
            i2 = i3;
            str2 = sb2;
        }
        return str2;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a(b() ? 250 : a() ? 128 : 64, str);
    }

    private static boolean a() {
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        return Z != null && Z.ex_noitice_128byte;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a((a() || b()) ? 32 : 16, str);
    }

    private static boolean b() {
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        return Z != null && Z.ex_notice_250byte;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return a((a() || b()) ? 64 : 32, str);
    }
}
