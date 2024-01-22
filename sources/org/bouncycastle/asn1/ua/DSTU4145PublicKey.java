package org.bouncycastle.asn1.ua;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes12.dex */
public class DSTU4145PublicKey extends ASN1Object {
    public ASN1OctetString h;

    public DSTU4145PublicKey(ASN1OctetString aSN1OctetString) {
        this.h = aSN1OctetString;
    }

    public DSTU4145PublicKey(ECPoint eCPoint) {
        this.h = new DEROctetString(DSTU4145PointEncoder.encodePoint(eCPoint));
    }

    public static DSTU4145PublicKey getInstance(Object obj) {
        if (obj instanceof DSTU4145PublicKey) {
            return (DSTU4145PublicKey) obj;
        }
        if (obj != null) {
            return new DSTU4145PublicKey(ASN1OctetString.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
