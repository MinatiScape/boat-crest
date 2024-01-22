package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
/* loaded from: classes12.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i) {
        super(false, i, new BERSequence());
    }

    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        int b;
        if (this.i) {
            return i.b(this.h) + 1;
        }
        int a2 = this.k.toASN1Primitive().a();
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
        Enumeration objects;
        aSN1OutputStream.j(160, this.h);
        aSN1OutputStream.c(128);
        if (!this.i) {
            if (this.j) {
                aSN1OutputStream.writeObject(this.k);
            } else {
                ASN1Encodable aSN1Encodable = this.k;
                if (aSN1Encodable instanceof ASN1OctetString) {
                    objects = aSN1Encodable instanceof BEROctetString ? ((BEROctetString) aSN1Encodable).getObjects() : new BEROctetString(((ASN1OctetString) aSN1Encodable).getOctets()).getObjects();
                } else if (aSN1Encodable instanceof ASN1Sequence) {
                    objects = ((ASN1Sequence) aSN1Encodable).getObjects();
                } else if (!(aSN1Encodable instanceof ASN1Set)) {
                    throw new ASN1Exception("not implemented: " + this.k.getClass().getName());
                } else {
                    objects = ((ASN1Set) aSN1Encodable).getObjects();
                }
                while (objects.hasMoreElements()) {
                    aSN1OutputStream.writeObject((ASN1Encodable) objects.nextElement());
                }
            }
        }
        aSN1OutputStream.c(0);
        aSN1OutputStream.c(0);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        if (this.i || this.j) {
            return true;
        }
        return this.k.toASN1Primitive().b().isConstructed();
    }
}
