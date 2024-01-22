package org.bouncycastle.asn1.x509;
/* loaded from: classes12.dex */
public class X509NameTokenizer {

    /* renamed from: a  reason: collision with root package name */
    public String f14438a;
    public int b;
    public char c;
    public StringBuffer d;

    public X509NameTokenizer(String str) {
        this(str, ',');
    }

    public X509NameTokenizer(String str, char c) {
        this.d = new StringBuffer();
        this.f14438a = str;
        this.b = -1;
        this.c = c;
    }

    public boolean hasMoreTokens() {
        return this.b != this.f14438a.length();
    }

    public String nextToken() {
        if (this.b == this.f14438a.length()) {
            return null;
        }
        int i = this.b + 1;
        this.d.setLength(0);
        boolean z = false;
        boolean z2 = false;
        while (i != this.f14438a.length()) {
            char charAt = this.f14438a.charAt(i);
            if (charAt == '\"') {
                if (!z) {
                    z2 = !z2;
                }
            } else if (!z && !z2) {
                if (charAt == '\\') {
                    this.d.append(charAt);
                    z = true;
                } else if (charAt == this.c) {
                    break;
                } else {
                    this.d.append(charAt);
                }
                i++;
            }
            this.d.append(charAt);
            z = false;
            i++;
        }
        this.b = i;
        return this.d.toString();
    }
}
