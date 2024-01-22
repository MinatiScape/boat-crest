package com.crrepa.f;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class m {
    public static int a(byte[] bArr) {
        String str = new String(bArr);
        if (str.contains("DFU")) {
            Matcher matcher = Pattern.compile("\\d+").matcher(str);
            ArrayList arrayList = new ArrayList();
            while (matcher.find()) {
                arrayList.add(Integer.valueOf(matcher.group(0)));
            }
            return (!arrayList.isEmpty() && ((Integer) arrayList.get(0)).intValue() > 0) ? 1 : 0;
        }
        return 0;
    }
}
