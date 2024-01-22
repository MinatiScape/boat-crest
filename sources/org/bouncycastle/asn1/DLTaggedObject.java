package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class DLTaggedObject extends ASN1TaggedObject {
    public static final byte[] l = new byte[0];

    public DLTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        int b;
        if (this.i) {
            return i.b(this.h) + 1;
        }
        int a2 = this.k.toASN1Primitive().c().a();
        if (this.j) {
            b = i.b(this.h) + i.a(a2);
        } else {
            a2--;
            b = i.b(this.h);
        }
        return b + a2;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        if (this.i) {
            aSN1OutputStream.f(160, this.h, l);
            return;
        }
        ASN1Primitive c = this.k.toASN1Primitive().c();
        if (!this.j) {
            aSN1OutputStream.j(c.isConstructed() ? 160 : 128, this.h);
            aSN1OutputStream.h(c);
            return;
        }
        aSN1OutputStream.j(160, this.h);
        aSN1OutputStream.i(c.a());
        aSN1OutputStream.writeObject(c);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        if (this.i || this.j) {
            return true;
        }
        return this.k.toASN1Primitive().c().isConstructed();
    }
}
