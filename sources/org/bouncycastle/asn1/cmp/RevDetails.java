package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertTemplate;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.X509Extensions;
/* loaded from: classes12.dex */
public class RevDetails extends ASN1Object {
    public CertTemplate h;
    public Extensions i;

    public RevDetails(ASN1Sequence aSN1Sequence) {
        this.h = CertTemplate.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.i = Extensions.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public RevDetails(CertTemplate certTemplate) {
        this.h = certTemplate;
    }

    public RevDetails(CertTemplate certTemplate, Extensions extensions) {
        this.h = certTemplate;
        this.i = extensions;
    }

    public RevDetails(CertTemplate certTemplate, X509Extensions x509Extensions) {
        this.h = certTemplate;
        this.i = Extensions.getInstance(x509Extensions.toASN1Primitive());
    }

    public static RevDetails getInstance(Object obj) {
        if (obj instanceof RevDetails) {
            return (RevDetails) obj;
        }
        if (obj != null) {
            return new RevDetails(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertTemplate getCertDetails() {
        return this.h;
    }

    public Extensions getCrlEntryDetails() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        Extensions extensions = this.i;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
