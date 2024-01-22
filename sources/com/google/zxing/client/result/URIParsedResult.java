package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class URIParsedResult extends ParsedResult {
    public final String b;
    public final String c;

    public URIParsedResult(String str, String str2) {
        super(ParsedResultType.URI);
        this.b = b(str);
        this.c = str2;
    }

    public static boolean a(String str, int i) {
        int i2 = i + 1;
        int indexOf = str.indexOf(47, i2);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return ResultParser.isSubstringOfDigits(str, i2, indexOf - i2);
    }

    public static String b(String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(58);
        return (indexOf < 0 || a(trim, indexOf)) ? "http://".concat(trim) : trim;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(30);
        ParsedResult.maybeAppend(this.c, sb);
        ParsedResult.maybeAppend(this.b, sb);
        return sb.toString();
    }

    public String getTitle() {
        return this.c;
    }

    public String getURI() {
        return this.b;
    }

    @Deprecated
    public boolean isPossiblyMaliciousURI() {
        return URIResultParser.h(this.b);
    }
}
