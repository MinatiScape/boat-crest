package com.mappls.sdk.navigation.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes11.dex */
public final class c {
    public static String a(double d, int i) {
        if (d < -180.0d || d > 180.0d || Double.isNaN(d)) {
            throw new IllegalArgumentException("coordinate=" + d);
        } else if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException("outputType=" + i);
        } else {
            StringBuilder sb = new StringBuilder();
            if (d < 0.0d) {
                sb.append(Soundex.SILENT_MARKER);
                d = -d;
            }
            DecimalFormat decimalFormat = new DecimalFormat("##0.00000", new DecimalFormatSymbols(Locale.US));
            if (i == 1 || i == 2) {
                int floor = (int) Math.floor(d);
                sb.append(floor);
                sb.append(':');
                d = (d - floor) * 60.0d;
                if (i == 2) {
                    int floor2 = (int) Math.floor(d);
                    sb.append(floor2);
                    sb.append(':');
                    d = (d - floor2) * 60.0d;
                }
            }
            sb.append(decimalFormat.format(d));
            return sb.toString();
        }
    }
}
