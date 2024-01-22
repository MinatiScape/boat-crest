package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.X509Extensions;
/* loaded from: classes12.dex */
public class SingleResponse extends ASN1Object {
    public CertID h;
    public CertStatus i;
    public ASN1GeneralizedTime j;
    public ASN1GeneralizedTime k;
    public Extensions l;

    public SingleResponse(ASN1Sequence aSN1Sequence) {
        ASN1TaggedObject aSN1TaggedObject;
        this.h = CertID.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = CertStatus.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        if (aSN1Sequence.size() > 4) {
            this.k = ASN1GeneralizedTime.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(3), true);
            aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(4);
        } else if (aSN1Sequence.size() <= 3) {
            return;
        } else {
            aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(3);
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.k = ASN1GeneralizedTime.getInstance(aSN1TaggedObject, true);
                return;
            }
        }
        this.l = Extensions.getInstance(aSN1TaggedObject, true);
    }

    public SingleResponse(CertID certID, CertStatus certStatus, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1GeneralizedTime aSN1GeneralizedTime2, Extensions extensions) {
        this.h = certID;
        this.i = certStatus;
        this.j = aSN1GeneralizedTime;
        this.k = aSN1GeneralizedTime2;
        this.l = extensions;
    }

    public SingleResponse(CertID certID, CertStatus certStatus, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1GeneralizedTime aSN1GeneralizedTime2, X509Extensions x509Extensions) {
        this(certID, certStatus, aSN1GeneralizedTime, aSN1GeneralizedTime2, Extensions.getInstance(x509Extensions));
    }

    public static SingleResponse getInstance(Object obj) {
        if (obj instanceof SingleResponse) {
            return (SingleResponse) obj;
        }
        if (obj != null) {
            return new SingleResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static SingleResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public CertID getCertID() {
        return this.h;
    }

    public CertStatus getCertStatus() {
        return this.i;
    }

    public ASN1GeneralizedTime getNextUpdate() {
        return this.k;
    }

    public Extensions getSingleExtensions() {
        return this.l;
    }

    public ASN1GeneralizedTime getThisUpdate() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        if (this.k != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.k));
        }
        if (this.l != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.l));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
