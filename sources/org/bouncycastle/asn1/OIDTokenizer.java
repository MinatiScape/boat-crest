package org.bouncycastle.asn1;
/* loaded from: classes12.dex */
public class OIDTokenizer {

    /* renamed from: a  reason: collision with root package name */
    public String f14390a;
    public int b = 0;

    public OIDTokenizer(String str) {
        this.f14390a = str;
    }

    public boolean hasMoreTokens() {
        return this.b != -1;
    }

    public String nextToken() {
        int i = this.b;
        if (i == -1) {
            return null;
        }
        int indexOf = this.f14390a.indexOf(46, i);
        if (indexOf == -1) {
            String substring = this.f14390a.substring(this.b);
            this.b = -1;
            return substring;
        }
        String substring2 = this.f14390a.substring(this.b, indexOf);
        this.b = indexOf + 1;
        return substring2;
    }
}
