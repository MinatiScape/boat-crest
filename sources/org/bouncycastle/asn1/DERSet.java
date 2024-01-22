package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
/* loaded from: classes12.dex */
public class DERSet extends ASN1Set {
    public int j;

    public DERSet() {
        this.j = -1;
    }

    public DERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
        this.j = -1;
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, true);
        this.j = -1;
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        super(aSN1EncodableVector, z);
        this.j = -1;
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, true);
        this.j = -1;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        int g = g();
        return i.a(g) + 1 + g;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream a2 = aSN1OutputStream.a();
        int g = g();
        aSN1OutputStream.c(49);
        aSN1OutputStream.i(g);
        Enumeration objects = getObjects();
        while (objects.hasMoreElements()) {
            a2.writeObject((ASN1Encodable) objects.nextElement());
        }
    }

    public final int g() throws IOException {
        if (this.j < 0) {
            int i = 0;
            Enumeration objects = getObjects();
            while (objects.hasMoreElements()) {
                i += ((ASN1Encodable) objects.nextElement()).toASN1Primitive().b().a();
            }
            this.j = i;
        }
        return this.j;
    }
}
