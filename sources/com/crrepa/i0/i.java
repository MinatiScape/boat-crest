package com.crrepa.i0;

import android.text.TextUtils;
/* loaded from: classes9.dex */
public class i {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.replace("📹", "[Video Camera]").replaceAll("[^\\u0000-\\uFFFF]", "[emoji]");
    }
}
