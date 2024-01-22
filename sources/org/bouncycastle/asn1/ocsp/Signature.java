package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class Signature extends ASN1Object {
    public AlgorithmIdentifier h;
    public DERBitString i;
    public ASN1Sequence j;

    public Signature(ASN1Sequence aSN1Sequence) {
        this.h = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = (DERBitString) aSN1Sequence.getObjectAt(1);
        if (aSN1Sequence.size() == 3) {
            this.j = ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), true);
        }
    }

    public Signature(AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString) {
        this.h = algorithmIdentifier;
        this.i = dERBitString;
    }

    public Signature(AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Sequence aSN1Sequence) {
        this.h = algorithmIdentifier;
        this.i = dERBitString;
        this.j = aSN1Sequence;
    }

    public static Signature getInstance(Object obj) {
        if (obj instanceof Signature) {
            return (Signature) obj;
        }
        if (obj != null) {
            return new Signature(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Signature getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Sequence getCerts() {
        return this.j;
    }

    public DERBitString getSignature() {
        return this.i;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
