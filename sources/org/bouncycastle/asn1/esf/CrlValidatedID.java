package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class CrlValidatedID extends ASN1Object {
    public OtherHash h;
    public CrlIdentifier i;

    public CrlValidatedID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.h = OtherHash.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.i = CrlIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public CrlValidatedID(OtherHash otherHash) {
        this(otherHash, null);
    }

    public CrlValidatedID(OtherHash otherHash, CrlIdentifier crlIdentifier) {
        this.h = otherHash;
        this.i = crlIdentifier;
    }

    public static CrlValidatedID getInstance(Object obj) {
        if (obj instanceof CrlValidatedID) {
            return (CrlValidatedID) obj;
        }
        if (obj != null) {
            return new CrlValidatedID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public OtherHash getCrlHash() {
        return this.h;
    }

    public CrlIdentifier getCrlIdentifier() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h.toASN1Primitive());
        CrlIdentifier crlIdentifier = this.i;
        if (crlIdentifier != null) {
            aSN1EncodableVector.add(crlIdentifier.toASN1Primitive());
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
