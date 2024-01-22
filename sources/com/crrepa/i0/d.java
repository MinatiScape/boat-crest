package com.crrepa.i0;

import android.text.TextUtils;
/* loaded from: classes9.dex */
public class d {
    public static String a(String str) {
        if (str == null || !str.contains("-")) {
            return null;
        }
        return str.substring(4);
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.toLowerCase().contains("dfu");
    }
}
