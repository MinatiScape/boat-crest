package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes11.dex */
public final class WifiResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public WifiParsedResult parse(Result result) {
        String substring;
        String d;
        String str;
        boolean z;
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("WIFI:") || (d = ResultParser.d("S:", (substring = massagedText.substring(5)), ';', false)) == null || d.isEmpty()) {
            return null;
        }
        String d2 = ResultParser.d("P:", substring, ';', false);
        String d3 = ResultParser.d("T:", substring, ';', false);
        if (d3 == null) {
            d3 = "nopass";
        }
        String str2 = d3;
        String d4 = ResultParser.d("PH2:", substring, ';', false);
        String d5 = ResultParser.d("H:", substring, ';', false);
        if (d5 == null) {
            str = d4;
        } else if (d4 != null || "true".equalsIgnoreCase(d5) || "false".equalsIgnoreCase(d5)) {
            str = d4;
            z = Boolean.parseBoolean(d5);
            return new WifiParsedResult(str2, d, d2, z, ResultParser.d("I:", substring, ';', false), ResultParser.d("A:", substring, ';', false), ResultParser.d("E:", substring, ';', false), str);
        } else {
            str = d5;
        }
        z = false;
        return new WifiParsedResult(str2, d, d2, z, ResultParser.d("I:", substring, ';', false), ResultParser.d("A:", substring, ';', false), ResultParser.d("E:", substring, ';', false), str);
    }
}
