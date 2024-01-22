package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class TelParsedResult extends ParsedResult {
    public final String b;
    public final String c;
    public final String d;

    public TelParsedResult(String str, String str2, String str3) {
        super(ParsedResultType.TEL);
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        ParsedResult.maybeAppend(this.b, sb);
        ParsedResult.maybeAppend(this.d, sb);
        return sb.toString();
    }

    public String getNumber() {
        return this.b;
    }

    public String getTelURI() {
        return this.c;
    }

    public String getTitle() {
        return this.d;
    }
}
