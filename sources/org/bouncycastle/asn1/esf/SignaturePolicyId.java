package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class SignaturePolicyId extends ASN1Object {
    public ASN1ObjectIdentifier h;
    public OtherHashAlgAndValue i;
    public SigPolicyQualifiers j;

    public SignaturePolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier, OtherHashAlgAndValue otherHashAlgAndValue) {
        this(aSN1ObjectIdentifier, otherHashAlgAndValue, null);
    }

    public SignaturePolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier, OtherHashAlgAndValue otherHashAlgAndValue, SigPolicyQualifiers sigPolicyQualifiers) {
        this.h = aSN1ObjectIdentifier;
        this.i = otherHashAlgAndValue;
        this.j = sigPolicyQualifiers;
    }

    public SignaturePolicyId(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.h = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = OtherHashAlgAndValue.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 3) {
            this.j = SigPolicyQualifiers.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static SignaturePolicyId getInstance(Object obj) {
        if (obj instanceof SignaturePolicyId) {
            return (SignaturePolicyId) obj;
        }
        if (obj != null) {
            return new SignaturePolicyId(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public OtherHashAlgAndValue getSigPolicyHash() {
        return this.i;
    }

    public ASN1ObjectIdentifier getSigPolicyId() {
        return new ASN1ObjectIdentifier(this.h.getId());
    }

    public SigPolicyQualifiers getSigPolicyQualifiers() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        SigPolicyQualifiers sigPolicyQualifiers = this.j;
        if (sigPolicyQualifiers != null) {
            aSN1EncodableVector.add(sigPolicyQualifiers);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
