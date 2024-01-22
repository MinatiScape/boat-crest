package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class CRLBag extends ASN1Object {
    public ASN1ObjectIdentifier h;
    public ASN1Encodable i;

    public CRLBag(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.h = aSN1ObjectIdentifier;
        this.i = aSN1Encodable;
    }

    public CRLBag(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.i = ((ASN1TaggedObject) aSN1Sequence.getObjectAt(1)).getObject();
    }

    public static CRLBag getInstance(Object obj) {
        if (obj instanceof CRLBag) {
            return (CRLBag) obj;
        }
        if (obj != null) {
            return new CRLBag(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getCrlId() {
        return this.h;
    }

    public ASN1Encodable getCrlValue() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new DERTaggedObject(0, this.i));
        return new DERSequence(aSN1EncodableVector);
    }
}
