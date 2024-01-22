package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes11.dex */
public final class BookmarkDoCoMoResultParser extends a {
    @Override // com.google.zxing.client.result.ResultParser
    public URIParsedResult parse(Result result) {
        String text = result.getText();
        if (text.startsWith("MEBKM:")) {
            String h = a.h("TITLE:", text, true);
            String[] g = a.g("URL:", text);
            if (g == null) {
                return null;
            }
            String str = g[0];
            if (URIResultParser.g(str)) {
                return new URIParsedResult(str, h);
            }
            return null;
        }
        return null;
    }
}
