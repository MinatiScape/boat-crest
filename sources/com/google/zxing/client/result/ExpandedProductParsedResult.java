package com.google.zxing.client.result;

import java.util.Map;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class ExpandedProductParsedResult extends ParsedResult {
    public static final String KILOGRAM = "KG";
    public static final String POUND = "LB";
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final Map<String, String> p;

    public ExpandedProductParsedResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, Map<String, String> map) {
        super(ParsedResultType.PRODUCT);
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = str8;
        this.j = str9;
        this.k = str10;
        this.l = str11;
        this.m = str12;
        this.n = str13;
        this.o = str14;
        this.p = map;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExpandedProductParsedResult) {
            ExpandedProductParsedResult expandedProductParsedResult = (ExpandedProductParsedResult) obj;
            return Objects.equals(this.c, expandedProductParsedResult.c) && Objects.equals(this.d, expandedProductParsedResult.d) && Objects.equals(this.e, expandedProductParsedResult.e) && Objects.equals(this.f, expandedProductParsedResult.f) && Objects.equals(this.h, expandedProductParsedResult.h) && Objects.equals(this.i, expandedProductParsedResult.i) && Objects.equals(this.j, expandedProductParsedResult.j) && Objects.equals(this.k, expandedProductParsedResult.k) && Objects.equals(this.l, expandedProductParsedResult.l) && Objects.equals(this.m, expandedProductParsedResult.m) && Objects.equals(this.n, expandedProductParsedResult.n) && Objects.equals(this.o, expandedProductParsedResult.o) && Objects.equals(this.p, expandedProductParsedResult.p);
        }
        return false;
    }

    public String getBestBeforeDate() {
        return this.h;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        return String.valueOf(this.b);
    }

    public String getExpirationDate() {
        return this.i;
    }

    public String getLotNumber() {
        return this.e;
    }

    public String getPackagingDate() {
        return this.g;
    }

    public String getPrice() {
        return this.m;
    }

    public String getPriceCurrency() {
        return this.o;
    }

    public String getPriceIncrement() {
        return this.n;
    }

    public String getProductID() {
        return this.c;
    }

    public String getProductionDate() {
        return this.f;
    }

    public String getRawText() {
        return this.b;
    }

    public String getSscc() {
        return this.d;
    }

    public Map<String, String> getUncommonAIs() {
        return this.p;
    }

    public String getWeight() {
        return this.j;
    }

    public String getWeightIncrement() {
        return this.l;
    }

    public String getWeightType() {
        return this.k;
    }

    public int hashCode() {
        return (((((((((((Objects.hashCode(this.c) ^ Objects.hashCode(this.d)) ^ Objects.hashCode(this.e)) ^ Objects.hashCode(this.f)) ^ Objects.hashCode(this.h)) ^ Objects.hashCode(this.i)) ^ Objects.hashCode(this.j)) ^ Objects.hashCode(this.k)) ^ Objects.hashCode(this.l)) ^ Objects.hashCode(this.m)) ^ Objects.hashCode(this.n)) ^ Objects.hashCode(this.o)) ^ Objects.hashCode(this.p);
    }
}
