package com.google.zxing.client.result;

import androidx.core.net.MailTo;
import com.google.zxing.Result;
import com.mappls.sdk.services.account.Region;
import java.util.List;
/* loaded from: classes11.dex */
public final class VEventResultParser extends ResultParser {
    public static String g(CharSequence charSequence, String str) {
        List<String> j = VCardResultParser.j(charSequence, str, true, false);
        if (j == null || j.isEmpty()) {
            return null;
        }
        return j.get(0);
    }

    public static String[] h(CharSequence charSequence, String str) {
        List<List<String>> k = VCardResultParser.k(charSequence, str, true, false);
        if (k == null || k.isEmpty()) {
            return null;
        }
        int size = k.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = k.get(i).get(0);
        }
        return strArr;
    }

    public static String i(String str) {
        return str != null ? (str.startsWith(MailTo.MAILTO_SCHEME) || str.startsWith("MAILTO:")) ? str.substring(7) : str : str;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public CalendarParsedResult parse(Result result) {
        double parseDouble;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.indexOf("BEGIN:VEVENT") < 0) {
            return null;
        }
        String g = g("SUMMARY", massagedText);
        String g2 = g("DTSTART", massagedText);
        if (g2 == null) {
            return null;
        }
        String g3 = g("DTEND", massagedText);
        String g4 = g("DURATION", massagedText);
        String g5 = g("LOCATION", massagedText);
        String i = i(g("ORGANIZER", massagedText));
        String[] h = h("ATTENDEE", massagedText);
        if (h != null) {
            for (int i2 = 0; i2 < h.length; i2++) {
                h[i2] = i(h[i2]);
            }
        }
        String g6 = g("DESCRIPTION", massagedText);
        String g7 = g(Region.REGION_GEORGIA, massagedText);
        double d = Double.NaN;
        if (g7 == null) {
            parseDouble = Double.NaN;
        } else {
            int indexOf = g7.indexOf(59);
            if (indexOf < 0) {
                return null;
            }
            try {
                d = Double.parseDouble(g7.substring(0, indexOf));
                parseDouble = Double.parseDouble(g7.substring(indexOf + 1));
            } catch (NumberFormatException | IllegalArgumentException unused) {
                return null;
            }
        }
        return new CalendarParsedResult(g, g2, g3, g4, g5, i, h, g6, d, parseDouble);
    }
}
