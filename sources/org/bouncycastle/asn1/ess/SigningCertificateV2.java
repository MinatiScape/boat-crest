package org.bouncycastle.asn1.ess;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes12.dex */
public class SigningCertificateV2 extends ASN1Object {
    public ASN1Sequence h;
    public ASN1Sequence i;

    public SigningCertificateV2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.h = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.i = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public SigningCertificateV2(ESSCertIDv2 eSSCertIDv2) {
        this.h = new DERSequence(eSSCertIDv2);
    }

    public SigningCertificateV2(ESSCertIDv2[] eSSCertIDv2Arr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (ESSCertIDv2 eSSCertIDv2 : eSSCertIDv2Arr) {
            aSN1EncodableVector.add(eSSCertIDv2);
        }
        this.h = new DERSequence(aSN1EncodableVector);
    }

    public SigningCertificateV2(ESSCertIDv2[] eSSCertIDv2Arr, PolicyInformation[] policyInformationArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (ESSCertIDv2 eSSCertIDv2 : eSSCertIDv2Arr) {
            aSN1EncodableVector.add(eSSCertIDv2);
        }
        this.h = new DERSequence(aSN1EncodableVector);
        if (policyInformationArr != null) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            for (PolicyInformation policyInformation : policyInformationArr) {
                aSN1EncodableVector2.add(policyInformation);
            }
            this.i = new DERSequence(aSN1EncodableVector2);
        }
    }

    public static SigningCertificateV2 getInstance(Object obj) {
        if (obj == null || (obj instanceof SigningCertificateV2)) {
            return (SigningCertificateV2) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new SigningCertificateV2((ASN1Sequence) obj);
        }
        return null;
    }

    public ESSCertIDv2[] getCerts() {
        ESSCertIDv2[] eSSCertIDv2Arr = new ESSCertIDv2[this.h.size()];
        for (int i = 0; i != this.h.size(); i++) {
            eSSCertIDv2Arr[i] = ESSCertIDv2.getInstance(this.h.getObjectAt(i));
        }
        return eSSCertIDv2Arr;
    }

    public PolicyInformation[] getPolicies() {
        ASN1Sequence aSN1Sequence = this.i;
        if (aSN1Sequence == null) {
            return null;
        }
        PolicyInformation[] policyInformationArr = new PolicyInformation[aSN1Sequence.size()];
        for (int i = 0; i != this.i.size(); i++) {
            policyInformationArr[i] = PolicyInformation.getInstance(this.i.getObjectAt(i));
        }
        return policyInformationArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        ASN1Sequence aSN1Sequence = this.i;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
