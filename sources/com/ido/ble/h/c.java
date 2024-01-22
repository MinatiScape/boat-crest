package com.ido.ble.h;
/* loaded from: classes11.dex */
public class c {
    public static double a(String str, String str2) {
        int indexOf;
        if (str.equalsIgnoreCase("0.0E") || str.equalsIgnoreCase("0.0N") || str.indexOf(".") - 2 <= 0) {
            return 0.0d;
        }
        double parseInt = Integer.parseInt(str.substring(0, indexOf)) + (Double.parseDouble(str.substring(indexOf, str.length() - 1)) / 60.0d);
        return !str.substring(str.length() + (-1)).equalsIgnoreCase(str2) ? -parseInt : parseInt;
    }
}
