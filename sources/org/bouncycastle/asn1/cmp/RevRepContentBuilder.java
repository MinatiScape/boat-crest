package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.CertId;
import org.bouncycastle.asn1.x509.CertificateList;
/* loaded from: classes12.dex */
public class RevRepContentBuilder {

    /* renamed from: a  reason: collision with root package name */
    public ASN1EncodableVector f14397a = new ASN1EncodableVector();
    public ASN1EncodableVector b = new ASN1EncodableVector();
    public ASN1EncodableVector c = new ASN1EncodableVector();

    public RevRepContentBuilder add(PKIStatusInfo pKIStatusInfo) {
        this.f14397a.add(pKIStatusInfo);
        return this;
    }

    public RevRepContentBuilder add(PKIStatusInfo pKIStatusInfo, CertId certId) {
        if (this.f14397a.size() == this.b.size()) {
            this.f14397a.add(pKIStatusInfo);
            this.b.add(certId);
            return this;
        }
        throw new IllegalStateException("status and revCerts sequence must be in common order");
    }

    public RevRepContentBuilder addCrl(CertificateList certificateList) {
        this.c.add(certificateList);
        return this;
    }

    public RevRepContent build() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DERSequence(this.f14397a));
        if (this.b.size() != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DERSequence(this.b)));
        }
        if (this.c.size() != 0) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, new DERSequence(this.c)));
        }
        return RevRepContent.getInstance(new DERSequence(aSN1EncodableVector));
    }
}
