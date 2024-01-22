package org.bouncycastle.cms;

import java.math.BigInteger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class i implements Selector {
    public byte[] h;
    public X500Name i;
    public BigInteger j;

    public i(X500Name x500Name, BigInteger bigInteger) {
        b(x500Name, bigInteger);
    }

    public i(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        b(x500Name, bigInteger);
        c(bArr);
    }

    public i(byte[] bArr) {
        c(bArr);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj != null ? obj.equals(obj2) : obj2 == null;
    }

    public final void b(X500Name x500Name, BigInteger bigInteger) {
        this.i = x500Name;
        this.j = bigInteger;
    }

    public final void c(byte[] bArr) {
        this.h = bArr;
    }

    @Override // org.bouncycastle.util.Selector
    public Object clone() {
        return new i(this.i, this.j, this.h);
    }

    public boolean equals(Object obj) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            return Arrays.areEqual(this.h, iVar.h) && a(this.j, iVar.j) && a(this.i, iVar.i);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Arrays.hashCode(this.h);
        BigInteger bigInteger = this.j;
        if (bigInteger != null) {
            hashCode ^= bigInteger.hashCode();
        }
        X500Name x500Name = this.i;
        return x500Name != null ? hashCode ^ x500Name.hashCode() : hashCode;
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        return false;
    }
}
