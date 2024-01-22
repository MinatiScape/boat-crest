package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class CertificatePair extends ASN1Object {
    public Certificate h;
    public Certificate i;

    public CertificatePair(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 1 && aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.h = Certificate.getInstance(aSN1TaggedObject, true);
            } else if (aSN1TaggedObject.getTagNo() != 1) {
                throw new IllegalArgumentException("Bad tag number: " + aSN1TaggedObject.getTagNo());
            } else {
                this.i = Certificate.getInstance(aSN1TaggedObject, true);
            }
        }
    }

    public CertificatePair(Certificate certificate, Certificate certificate2) {
        this.h = certificate;
        this.i = certificate2;
    }

    public static CertificatePair getInstance(Object obj) {
        if (obj == null || (obj instanceof CertificatePair)) {
            return (CertificatePair) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new CertificatePair((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public Certificate getForward() {
        return this.h;
    }

    public Certificate getReverse() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.h));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(1, this.i));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
