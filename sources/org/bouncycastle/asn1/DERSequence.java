package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
/* loaded from: classes12.dex */
public class DERSequence extends ASN1Sequence {
    public int h;

    public DERSequence() {
        this.h = -1;
    }

    public DERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
        this.h = -1;
    }

    public DERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
        this.h = -1;
    }

    public DERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
        this.h = -1;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        int e = e();
        return i.a(e) + 1 + e;
    }

    public final int e() throws IOException {
        if (this.h < 0) {
            int i = 0;
            Enumeration objects = getObjects();
            while (objects.hasMoreElements()) {
                i += ((ASN1Encodable) objects.nextElement()).toASN1Primitive().b().a();
            }
            this.h = i;
        }
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream a2 = aSN1OutputStream.a();
        int e = e();
        aSN1OutputStream.c(48);
        aSN1OutputStream.i(e);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            a2.writeObject((ASN1Encodable) objects.nextElement());
        }
    }
}
