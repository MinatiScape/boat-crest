package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes6.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f8376a = Pattern.compile("\\\\u[0-9a-fA-F]{4}");

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = f8376a.matcher(str);
        StringBuilder sb = null;
        int i = 0;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuilder();
            }
            int start = matcher.start();
            int i2 = start;
            while (i2 >= 0 && str.charAt(i2) == '\\') {
                i2--;
            }
            if ((start - i2) % 2 != 0) {
                int parseInt = Integer.parseInt(matcher.group().substring(2), 16);
                sb.append((CharSequence) str, i, matcher.start());
                if (parseInt == 92) {
                    sb.append("\\\\");
                } else {
                    sb.append(Character.toChars(parseInt));
                }
                i = matcher.end();
            }
        }
        if (sb == null) {
            return str;
        }
        if (i < matcher.regionEnd()) {
            sb.append((CharSequence) str, i, matcher.regionEnd());
        }
        return sb.toString();
    }
}
