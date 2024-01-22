package com.google.zxing.client.result;
/* loaded from: classes11.dex */
public final class AddressBookParsedResult extends ParsedResult {
    public final String[] b;
    public final String[] c;
    public final String d;
    public final String[] e;
    public final String[] f;
    public final String[] g;
    public final String[] h;
    public final String i;
    public final String j;
    public final String[] k;
    public final String[] l;
    public final String m;
    public final String n;
    public final String o;
    public final String[] p;
    public final String[] q;

    public AddressBookParsedResult(String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String[] strArr7) {
        this(strArr, null, null, strArr2, strArr3, strArr4, strArr5, null, null, strArr6, strArr7, null, null, null, null, null);
    }

    public String[] getAddressTypes() {
        return this.l;
    }

    public String[] getAddresses() {
        return this.k;
    }

    public String getBirthday() {
        return this.n;
    }

    @Override // com.google.zxing.client.result.ParsedResult
    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        ParsedResult.maybeAppend(this.b, sb);
        ParsedResult.maybeAppend(this.c, sb);
        ParsedResult.maybeAppend(this.d, sb);
        ParsedResult.maybeAppend(this.o, sb);
        ParsedResult.maybeAppend(this.m, sb);
        ParsedResult.maybeAppend(this.k, sb);
        ParsedResult.maybeAppend(this.e, sb);
        ParsedResult.maybeAppend(this.g, sb);
        ParsedResult.maybeAppend(this.i, sb);
        ParsedResult.maybeAppend(this.p, sb);
        ParsedResult.maybeAppend(this.n, sb);
        ParsedResult.maybeAppend(this.q, sb);
        ParsedResult.maybeAppend(this.j, sb);
        return sb.toString();
    }

    public String[] getEmailTypes() {
        return this.h;
    }

    public String[] getEmails() {
        return this.g;
    }

    public String[] getGeo() {
        return this.q;
    }

    public String getInstantMessenger() {
        return this.i;
    }

    public String[] getNames() {
        return this.b;
    }

    public String[] getNicknames() {
        return this.c;
    }

    public String getNote() {
        return this.j;
    }

    public String getOrg() {
        return this.m;
    }

    public String[] getPhoneNumbers() {
        return this.e;
    }

    public String[] getPhoneTypes() {
        return this.f;
    }

    public String getPronunciation() {
        return this.d;
    }

    public String getTitle() {
        return this.o;
    }

    public String[] getURLs() {
        return this.p;
    }

    public AddressBookParsedResult(String[] strArr, String[] strArr2, String str, String[] strArr3, String[] strArr4, String[] strArr5, String[] strArr6, String str2, String str3, String[] strArr7, String[] strArr8, String str4, String str5, String str6, String[] strArr9, String[] strArr10) {
        super(ParsedResultType.ADDRESSBOOK);
        if (strArr3 != null && strArr4 != null && strArr3.length != strArr4.length) {
            throw new IllegalArgumentException("Phone numbers and types lengths differ");
        }
        if (strArr5 != null && strArr6 != null && strArr5.length != strArr6.length) {
            throw new IllegalArgumentException("Emails and types lengths differ");
        }
        if (strArr7 != null && strArr8 != null && strArr7.length != strArr8.length) {
            throw new IllegalArgumentException("Addresses and types lengths differ");
        }
        this.b = strArr;
        this.c = strArr2;
        this.d = str;
        this.e = strArr3;
        this.f = strArr4;
        this.g = strArr5;
        this.h = strArr6;
        this.i = str2;
        this.j = str3;
        this.k = strArr7;
        this.l = strArr8;
        this.m = str4;
        this.n = str5;
        this.o = str6;
        this.p = strArr9;
        this.q = strArr10;
    }
}
