package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes11.dex */
public final class AddressBookDoCoMoResultParser extends a {
    public static String i(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
        }
        return str;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String[] g;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("MECARD:") && (g = a.g("N:", massagedText)) != null) {
            String i = i(g[0]);
            String h = a.h("SOUND:", massagedText, true);
            String[] g2 = a.g("TEL:", massagedText);
            String[] g3 = a.g("EMAIL:", massagedText);
            String h2 = a.h("NOTE:", massagedText, false);
            String[] g4 = a.g("ADR:", massagedText);
            String h3 = a.h("BDAY:", massagedText, true);
            return new AddressBookParsedResult(ResultParser.maybeWrap(i), null, h, g2, null, g3, null, null, h2, g4, null, a.h("ORG:", massagedText, true), !ResultParser.isStringOfDigits(h3, 8) ? null : h3, null, a.g("URL:", massagedText), null);
        }
        return null;
    }
}
