package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class TextParsedResult extends ParsedResult {
    public final String b;
    public final String c;

    public TextParsedResult(String str, String str2) {
        super(ParsedResultType.TEXT);
        this.b = str;
        this.c = str2;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        return this.b;
    }

    public String getLanguage() {
        return this.c;
    }

    public String getText() {
        return this.b;
    }
}
