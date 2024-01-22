package com.elvishew.xlog.formatter.border;

import com.elvishew.xlog.internal.SystemCompat;
/* loaded from: classes9.dex */
public class DefaultBorderFormatter implements BorderFormatter {
    public static String a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 10);
        String[] split = str.split(SystemCompat.lineSeparator);
        int length = split.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(SystemCompat.lineSeparator);
            }
            String str2 = split[i];
            sb.append((char) 9553);
            sb.append(str2);
        }
        return sb.toString();
    }

    @Override // com.elvishew.xlog.formatter.Formatter
    public String format(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        String[] strArr2 = new String[strArr.length];
        int i = 0;
        for (String str : strArr) {
            if (str != null) {
                strArr2[i] = str;
                i++;
            }
        }
        if (i == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════════════════════════════════════════════════════");
        sb.append(SystemCompat.lineSeparator);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(a(strArr2[i2]));
            if (i2 != i - 1) {
                sb.append(SystemCompat.lineSeparator);
                sb.append("╟───────────────────────────────────────────────────────────────────────────────────────────────────");
                sb.append(SystemCompat.lineSeparator);
            } else {
                sb.append(SystemCompat.lineSeparator);
                sb.append("╚═══════════════════════════════════════════════════════════════════════════════════════════════════");
            }
        }
        return sb.toString();
    }
}
