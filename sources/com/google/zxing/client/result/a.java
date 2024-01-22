package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public abstract class a extends ResultParser {
    public static String[] g(String str, String str2) {
        return ResultParser.c(str, str2, ';', true);
    }

    public static String h(String str, String str2, boolean z) {
        return ResultParser.d(str, str2, ';', z);
    }
}
