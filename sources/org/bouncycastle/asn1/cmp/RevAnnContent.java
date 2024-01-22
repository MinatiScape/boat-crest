package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertId;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes12.dex */
public class RevAnnContent extends ASN1Object {
    public PKIStatus h;
    public CertId i;
    public ASN1GeneralizedTime j;
    public ASN1GeneralizedTime k;
    public Extensions l;

    public RevAnnContent(ASN1Sequence aSN1Sequence) {
        this.h = PKIStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = CertId.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(2));
        this.k = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(3));
        if (aSN1Sequence.size() > 4) {
            this.l = Extensions.getInstance(aSN1Sequence.getObjectAt(4));
        }
    }

    public static RevAnnContent getInstance(Object obj) {
        if (obj instanceof RevAnnContent) {
            return (RevAnnContent) obj;
        }
        if (obj != null) {
            return new RevAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1GeneralizedTime getBadSinceDate() {
        return this.k;
    }

    public CertId getCertId() {
        return this.i;
    }

    public Extensions getCrlDetails() {
        return this.l;
    }

    public PKIStatus getStatus() {
        return this.h;
    }

    public ASN1GeneralizedTime getWillBeRevokedAt() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        Extensions extensions = this.l;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
