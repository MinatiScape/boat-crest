package com.google.zxing.client.result;

import kotlin.text.Typography;
/* loaded from: classes11.dex */
public final class SMSParsedResult extends ParsedResult {
    public final String[] b;
    public final String[] c;
    public final String d;
    public final String e;

    public SMSParsedResult(String str, String str2, String str3, String str4) {
        super(ParsedResultType.SMS);
        this.b = new String[]{str};
        this.c = new String[]{str2};
        this.d = str3;
        this.e = str4;
    }

    public String getBody() {
        return this.e;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.b, sb);
        ParsedResult.maybeAppend(this.d, sb);
        ParsedResult.maybeAppend(this.e, sb);
        return sb.toString();
    }

    public String[] getNumbers() {
        return this.b;
    }

    public String getSMSURI() {
        StringBuilder sb = new StringBuilder();
        sb.append("sms:");
        boolean z = true;
        for (int i = 0; i < this.b.length; i++) {
            if (z) {
                z = false;
            } else {
                sb.append(',');
            }
            sb.append(this.b[i]);
            String[] strArr = this.c;
            if (strArr != null && strArr[i] != null) {
                sb.append(";via=");
                sb.append(this.c[i]);
            }
        }
        boolean z2 = this.e != null;
        boolean z3 = this.d != null;
        if (z2 || z3) {
            sb.append(org.apache.commons.codec.net.a.SEP);
            if (z2) {
                sb.append("body=");
                sb.append(this.e);
            }
            if (z3) {
                if (z2) {
                    sb.append(Typography.amp);
                }
                sb.append("subject=");
                sb.append(this.d);
            }
        }
        return sb.toString();
    }

    public String getSubject() {
        return this.d;
    }

    public String[] getVias() {
        return this.c;
    }

    public SMSParsedResult(String[] strArr, String[] strArr2, String str, String str2) {
        super(ParsedResultType.SMS);
        this.b = strArr;
        this.c = strArr2;
        this.d = str;
        this.e = str2;
    }
}
