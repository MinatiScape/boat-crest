package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public final class EmailDoCoMoResultParser extends a {
    public static final Pattern f = Pattern.compile("[a-zA-Z0-9@.!#$%&'*+\\-/=?^_`{|}~]+");

    public static boolean i(String str) {
        return str != null && f.matcher(str).matches() && str.indexOf(64) >= 0;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public EmailAddressParsedResult parse(Result result) {
        String[] g;
        String massagedText = ResultParser.getMassagedText(result);
        if (massagedText.startsWith("MATMSG:") && (g = a.g("TO:", massagedText)) != null) {
            for (String str : g) {
                if (!i(str)) {
                    return null;
                }
            }
            return new EmailAddressParsedResult(g, null, null, a.h("SUB:", massagedText, false), a.h("BODY:", massagedText, false));
        }
        return null;
    }
}
