package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class WifiParsedResult extends ParsedResult {
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;

    public WifiParsedResult(String str, String str2, String str3) {
        this(str, str2, str3, false);
    }

    public String getAnonymousIdentity() {
        return this.g;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(80);
        ParsedResult.maybeAppend(this.b, sb);
        ParsedResult.maybeAppend(this.c, sb);
        ParsedResult.maybeAppend(this.d, sb);
        ParsedResult.maybeAppend(Boolean.toString(this.e), sb);
        return sb.toString();
    }

    public String getEapMethod() {
        return this.h;
    }

    public String getIdentity() {
        return this.f;
    }

    public String getNetworkEncryption() {
        return this.c;
    }

    public String getPassword() {
        return this.d;
    }

    public String getPhase2Method() {
        return this.i;
    }

    public String getSsid() {
        return this.b;
    }

    public boolean isHidden() {
        return this.e;
    }

    public WifiParsedResult(String str, String str2, String str3, boolean z) {
        this(str, str2, str3, z, null, null, null, null);
    }

    public WifiParsedResult(String str, String str2, String str3, boolean z, String str4, String str5, String str6, String str7) {
        super(ParsedResultType.WIFI);
        this.b = str2;
        this.c = str;
        this.d = str3;
        this.e = z;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
    }
}
