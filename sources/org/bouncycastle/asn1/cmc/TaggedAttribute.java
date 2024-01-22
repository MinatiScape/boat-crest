package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class TaggedAttribute extends ASN1Object {
    public final BodyPartID h;
    public final ASN1ObjectIdentifier i;
    public final ASN1Set j;

    public TaggedAttribute(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = BodyPartID.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ASN1Set.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public TaggedAttribute(BodyPartID bodyPartID, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set) {
        this.h = bodyPartID;
        this.i = aSN1ObjectIdentifier;
        this.j = aSN1Set;
    }

    public static TaggedAttribute getInstance(Object obj) {
        if (obj instanceof TaggedAttribute) {
            return (TaggedAttribute) obj;
        }
        if (obj != null) {
            return new TaggedAttribute(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getAttrType() {
        return this.i;
    }

    public ASN1Set getAttrValues() {
        return this.j;
    }

    public BodyPartID getBodyPartID() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.h, this.i, this.j});
    }
}
