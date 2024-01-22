package com.google.zxing.client.result;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public final class URIResultParser extends ResultParser {
    public static final Pattern f = Pattern.compile("[-._~:/?#\\[\\]@!$&'()*+,;=%A-Za-z0-9]+");
    public static final Pattern g = Pattern.compile(":/*([^/@]+)@[^/]+");
    public static final Pattern h = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+:");
    public static final Pattern i = Pattern.compile("([a-zA-Z0-9\\-]+\\.){1,6}[a-zA-Z]{2,}(:\\d{1,5})?(/|\\?|$)");

    public static boolean g(String str) {
        if (str.contains(HexStringBuilder.DEFAULT_SEPARATOR)) {
            return false;
        }
        Matcher matcher = h.matcher(str);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = i.matcher(str);
        return matcher2.find() && matcher2.start() == 0;
    }

    public static boolean h(String str) {
        return !f.matcher(str).matches() || g.matcher(str).find();
    }

    @Override // com.google.zxing.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("URL:") && !massagedText.startsWith("URI:")) {
            String trim = massagedText.trim();
            if (!g(trim) || h(trim)) {
                return null;
            }
            return new URIParsedResult(trim, null);
        }
        return new URIParsedResult(massagedText.substring(4).trim(), null);
    }
}
