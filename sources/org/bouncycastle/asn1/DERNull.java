package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class DERNull extends ASN1Null {
    public static final DERNull INSTANCE = new DERNull();
    public static final byte[] h = new byte[0];

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() {
        return 2;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.g(5, h);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }
}
