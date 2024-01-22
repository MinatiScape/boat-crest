package org.bouncycastle.util.io.pem;
/* loaded from: classes13.dex */
public class PemHeader {

    /* renamed from: a  reason: collision with root package name */
    public String f15405a;
    public String b;

    public PemHeader(String str, String str2) {
        this.f15405a = str;
        this.b = str2;
    }

    public final int a(String str) {
        if (str == null) {
            return 1;
        }
        return str.hashCode();
    }

    public final boolean b(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PemHeader) {
            PemHeader pemHeader = (PemHeader) obj;
            return pemHeader == this || (b(this.f15405a, pemHeader.f15405a) && b(this.b, pemHeader.b));
        }
        return false;
    }

    public String getName() {
        return this.f15405a;
    }

    public String getValue() {
        return this.b;
    }

    public int hashCode() {
        return a(this.f15405a) + (a(this.b) * 31);
    }
}
