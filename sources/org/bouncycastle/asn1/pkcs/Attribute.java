package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class Attribute extends ASN1Object {
    public ASN1ObjectIdentifier h;
    public ASN1Set i;

    public Attribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set) {
        this.h = aSN1ObjectIdentifier;
        this.i = aSN1Set;
    }

    public Attribute(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
        this.i = (ASN1Set) aSN1Sequence.getObjectAt(1);
    }

    public static Attribute getInstance(Object obj) {
        if (obj == null || (obj instanceof Attribute)) {
            return (Attribute) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new Attribute((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public ASN1ObjectIdentifier getAttrType() {
        return this.h;
    }

    public ASN1Set getAttrValues() {
        return this.i;
    }

    public ASN1Encodable[] getAttributeValues() {
        return this.i.toArray();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
