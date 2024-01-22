package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.cmp.RevDetails;
import org.bouncycastle.asn1.crmf.CertTemplateBuilder;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
/* loaded from: classes12.dex */
public class RevocationDetailsBuilder {

    /* renamed from: a  reason: collision with root package name */
    public CertTemplateBuilder f14456a = new CertTemplateBuilder();

    public RevocationDetails build() {
        return new RevocationDetails(new RevDetails(this.f14456a.build()));
    }

    public RevocationDetailsBuilder setIssuer(X500Name x500Name) {
        if (x500Name != null) {
            this.f14456a.setIssuer(x500Name);
        }
        return this;
    }

    public RevocationDetailsBuilder setPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        if (subjectPublicKeyInfo != null) {
            this.f14456a.setPublicKey(subjectPublicKeyInfo);
        }
        return this;
    }

    public RevocationDetailsBuilder setSerialNumber(BigInteger bigInteger) {
        if (bigInteger != null) {
            this.f14456a.setSerialNumber(new ASN1Integer(bigInteger));
        }
        return this;
    }

    public RevocationDetailsBuilder setSubject(X500Name x500Name) {
        if (x500Name != null) {
            this.f14456a.setSubject(x500Name);
        }
        return this;
    }
}
