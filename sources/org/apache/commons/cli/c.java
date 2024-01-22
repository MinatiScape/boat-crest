package org.apache.commons.cli;
/* loaded from: classes12.dex */
public class c {
    public static String a(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length());
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("--")) {
            return str.substring(2, str.length());
        }
        return str.startsWith("-") ? str.substring(1, str.length()) : str;
    }
}