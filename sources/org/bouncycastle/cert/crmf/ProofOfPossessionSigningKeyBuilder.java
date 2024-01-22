package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.crmf.POPOSigningKey;
import org.bouncycastle.asn1.crmf.POPOSigningKeyInput;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentSigner;
/* loaded from: classes12.dex */
public class ProofOfPossessionSigningKeyBuilder {

    /* renamed from: a  reason: collision with root package name */
    public CertRequest f14465a;
    public SubjectPublicKeyInfo b;
    public GeneralName c;
    public PKMACValue d;

    public ProofOfPossessionSigningKeyBuilder(CertRequest certRequest) {
        this.f14465a = certRequest;
    }

    public ProofOfPossessionSigningKeyBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.b = subjectPublicKeyInfo;
    }

    public POPOSigningKey build(ContentSigner contentSigner) {
        POPOSigningKeyInput pOPOSigningKeyInput;
        GeneralName generalName = this.c;
        if (generalName == null || this.d == null) {
            CertRequest certRequest = this.f14465a;
            if (certRequest != null) {
                pOPOSigningKeyInput = null;
                a.b(certRequest, contentSigner.getOutputStream());
            } else if (generalName != null) {
                POPOSigningKeyInput pOPOSigningKeyInput2 = new POPOSigningKeyInput(generalName, this.b);
                a.b(pOPOSigningKeyInput2, contentSigner.getOutputStream());
                pOPOSigningKeyInput = pOPOSigningKeyInput2;
            } else {
                pOPOSigningKeyInput = new POPOSigningKeyInput(this.d, this.b);
                a.b(pOPOSigningKeyInput, contentSigner.getOutputStream());
            }
            return new POPOSigningKey(pOPOSigningKeyInput, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature()));
        }
        throw new IllegalStateException("name and publicKeyMAC cannot both be set.");
    }

    public ProofOfPossessionSigningKeyBuilder setPublicKeyMac(b bVar, char[] cArr) throws CRMFException {
        this.d = bVar.a(cArr, this.b);
        return this;
    }

    public ProofOfPossessionSigningKeyBuilder setSender(GeneralName generalName) {
        this.c = generalName;
        return this;
    }
}
