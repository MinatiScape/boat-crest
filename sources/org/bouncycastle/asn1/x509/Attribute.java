package org.bouncycastle.asn1.x509;

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
        if (aSN1Sequence.size() == 2) {
            this.h = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.i = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
    }

    public static Attribute getInstance(Object obj) {
        if (obj instanceof Attribute) {
            return (Attribute) obj;
        }
        if (obj != null) {
            return new Attribute(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getAttrType() {
        return new ASN1ObjectIdentifier(this.h.getId());
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
