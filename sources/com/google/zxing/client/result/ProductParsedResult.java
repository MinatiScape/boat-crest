package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class ProductParsedResult extends ParsedResult {
    public final String b;
    public final String c;

    public ProductParsedResult(String str, String str2) {
        super(ParsedResultType.PRODUCT);
        this.b = str;
        this.c = str2;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        return this.b;
    }

    public String getNormalizedProductID() {
        return this.c;
    }

    public String getProductID() {
        return this.b;
    }
}
