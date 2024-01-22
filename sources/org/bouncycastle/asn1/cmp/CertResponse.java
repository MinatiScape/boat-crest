package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class CertResponse extends ASN1Object {
    public ASN1Integer h;
    public PKIStatusInfo i;
    public CertifiedKeyPair j;
    public ASN1OctetString k;

    public CertResponse(ASN1Integer aSN1Integer, PKIStatusInfo pKIStatusInfo) {
        this(aSN1Integer, pKIStatusInfo, null, null);
    }

    public CertResponse(ASN1Integer aSN1Integer, PKIStatusInfo pKIStatusInfo, CertifiedKeyPair certifiedKeyPair, ASN1OctetString aSN1OctetString) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'certReqId' cannot be null");
        }
        if (pKIStatusInfo == null) {
            throw new IllegalArgumentException("'status' cannot be null");
        }
        this.h = aSN1Integer;
        this.i = pKIStatusInfo;
        this.j = certifiedKeyPair;
        this.k = aSN1OctetString;
    }

    public CertResponse(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = PKIStatusInfo.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() >= 3) {
            if (aSN1Sequence.size() == 3) {
                objectAt = aSN1Sequence.getObjectAt(2);
                if (!(objectAt instanceof ASN1OctetString)) {
                    this.j = CertifiedKeyPair.getInstance(objectAt);
                    return;
                }
            } else {
                this.j = CertifiedKeyPair.getInstance(aSN1Sequence.getObjectAt(2));
                objectAt = aSN1Sequence.getObjectAt(3);
            }
            this.k = ASN1OctetString.getInstance(objectAt);
        }
    }

    public static CertResponse getInstance(Object obj) {
        if (obj instanceof CertResponse) {
            return (CertResponse) obj;
        }
        if (obj != null) {
            return new CertResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getCertReqId() {
        return this.h;
    }

    public CertifiedKeyPair getCertifiedKeyPair() {
        return this.j;
    }

    public PKIStatusInfo getStatus() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        CertifiedKeyPair certifiedKeyPair = this.j;
        if (certifiedKeyPair != null) {
            aSN1EncodableVector.add(certifiedKeyPair);
        }
        ASN1OctetString aSN1OctetString = this.k;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
