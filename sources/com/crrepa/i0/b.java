package com.crrepa.i0;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class b {
    public static int a(String str) {
        String[] b = b(str, "-");
        int i = 0;
        if (b != null && b.length >= 3) {
            Matcher matcher = Pattern.compile("\\d+").matcher(b[2]);
            ArrayList<Integer> arrayList = new ArrayList();
            while (matcher.find()) {
                arrayList.add(Integer.valueOf(matcher.group(0)));
            }
            for (Integer num : arrayList) {
                i = (i * 10) + num.intValue();
            }
        }
        return i;
    }

    public static String[] b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(str2);
    }
}
