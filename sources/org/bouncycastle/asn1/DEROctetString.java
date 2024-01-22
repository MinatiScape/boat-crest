package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class DEROctetString extends ASN1OctetString {
    public DEROctetString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER));
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    public static void d(DEROutputStream dEROutputStream, byte[] bArr) throws IOException {
        dEROutputStream.g(4, bArr);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() {
        return i.a(this.h.length) + 1 + this.h.length;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.g(4, this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }
}
