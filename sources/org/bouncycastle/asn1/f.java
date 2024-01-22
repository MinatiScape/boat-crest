package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
/* loaded from: classes12.dex */
public class f implements Enumeration {

    /* renamed from: a  reason: collision with root package name */
    public ASN1InputStream f14416a;
    public Object b = a();

    public f(byte[] bArr) {
        this.f14416a = new ASN1InputStream(bArr, true);
    }

    public final Object a() {
        try {
            return this.f14416a.readObject();
        } catch (IOException e) {
            throw new ASN1ParsingException("malformed DER construction: " + e, e);
        }
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.b != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Object obj = this.b;
        this.b = a();
        return obj;
    }
}
