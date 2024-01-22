package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class AddressBookAUResultParser extends ResultParser {
    public static String[] g(String str, String str2) {
        ArrayList arrayList = null;
        for (int i = 1; i <= 3; i++) {
            String d = ResultParser.d(str + i + ':', str2, '\r', true);
            if (d == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(d);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(ResultParser.e);
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.contains("MEMORY") && massagedText.contains("\r\n")) {
            String d = ResultParser.d("NAME1:", massagedText, '\r', true);
            String d2 = ResultParser.d("NAME2:", massagedText, '\r', true);
            String[] g = g("TEL", massagedText);
            String[] g2 = g("MAIL", massagedText);
            String d3 = ResultParser.d("MEMORY:", massagedText, '\r', false);
            String d4 = ResultParser.d("ADD:", massagedText, '\r', true);
            return new AddressBookParsedResult(ResultParser.maybeWrap(d), null, d2, g, null, g2, null, null, d3, d4 != null ? new String[]{d4} : null, null, null, null, null, null, null);
        }
        return null;
    }
}
