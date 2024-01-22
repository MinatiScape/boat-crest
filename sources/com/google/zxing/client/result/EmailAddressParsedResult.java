package com.google.zxing.client.result;

import androidx.core.net.MailTo;
/* loaded from: classes11.dex */
public final class EmailAddressParsedResult extends ParsedResult {
    public final String[] b;
    public final String[] c;
    public final String[] d;
    public final String e;
    public final String f;

    public EmailAddressParsedResult(String str) {
        this(new String[]{str}, null, null, null, null);
    }

    public String[] getBCCs() {
        return this.d;
    }

    public String getBody() {
        return this.f;
    }

    public String[] getCCs() {
        return this.c;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(30);
        ParsedResult.maybeAppend(this.b, sb);
        ParsedResult.maybeAppend(this.c, sb);
        ParsedResult.maybeAppend(this.d, sb);
        ParsedResult.maybeAppend(this.e, sb);
        ParsedResult.maybeAppend(this.f, sb);
        return sb.toString();
    }

    @Deprecated
    public String getEmailAddress() {
        String[] strArr = this.b;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return strArr[0];
    }

    @Deprecated
    public String getMailtoURI() {
        return MailTo.MAILTO_SCHEME;
    }

    public String getSubject() {
        return this.e;
    }

    public String[] getTos() {
        return this.b;
    }

    public EmailAddressParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String str, String str2) {
        super(ParsedResultType.EMAIL_ADDRESS);
        this.b = strArr;
        this.c = strArr2;
        this.d = strArr3;
        this.e = str;
        this.f = str2;
    }
}
