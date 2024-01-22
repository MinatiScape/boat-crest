package org.bouncycastle.x509;

import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.V2Form;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.util.Selector;
/* loaded from: classes13.dex */
public class AttributeCertificateIssuer implements CertSelector, Selector {
    public final ASN1Encodable h;

    public AttributeCertificateIssuer(X500Principal x500Principal) throws IOException {
        this(new X509Principal(x500Principal.getEncoded()));
    }

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.h = attCertIssuer.getIssuer();
    }

    public AttributeCertificateIssuer(X509Principal x509Principal) {
        this.h = new V2Form(GeneralNames.getInstance(new DERSequence(new GeneralName(x509Principal))));
    }

    public final Object[] a() {
        ASN1Encodable aSN1Encodable = this.h;
        GeneralName[] names = (aSN1Encodable instanceof V2Form ? ((V2Form) aSN1Encodable).getIssuerName() : (GeneralNames) aSN1Encodable).getNames();
        ArrayList arrayList = new ArrayList(names.length);
        for (int i = 0; i != names.length; i++) {
            if (names[i].getTagNo() == 4) {
                try {
                    arrayList.add(new X500Principal(names[i].getName().toASN1Primitive().getEncoded()));
                } catch (IOException unused) {
                    throw new RuntimeException("badly formed Name object");
                }
            }
        }
        return arrayList.toArray(new Object[arrayList.size()]);
    }

    public final boolean b(X500Principal x500Principal, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            GeneralName generalName = names[i];
            if (generalName.getTagNo() == 4) {
                try {
                    if (new X500Principal(generalName.getName().toASN1Primitive().getEncoded()).equals(x500Principal)) {
                        return true;
                    }
                } catch (IOException unused) {
                    continue;
                }
            }
        }
        return false;
    }

    @Override // java.security.cert.CertSelector, org.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateIssuer(AttCertIssuer.getInstance(this.h));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateIssuer) {
            return this.h.equals(((AttributeCertificateIssuer) obj).h);
        }
        return false;
    }

    public Principal[] getPrincipals() {
        Object[] a2 = a();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != a2.length; i++) {
            if (a2[i] instanceof Principal) {
                arrayList.add(a2[i]);
            }
        }
        return (Principal[]) arrayList.toArray(new Principal[arrayList.size()]);
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509Certificate) {
            return match((Certificate) obj);
        }
        return false;
    }

    @Override // java.security.cert.CertSelector
    public boolean match(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            ASN1Encodable aSN1Encodable = this.h;
            if (aSN1Encodable instanceof V2Form) {
                V2Form v2Form = (V2Form) aSN1Encodable;
                if (v2Form.getBaseCertificateID() != null) {
                    return v2Form.getBaseCertificateID().getSerial().getValue().equals(x509Certificate.getSerialNumber()) && b(x509Certificate.getIssuerX500Principal(), v2Form.getBaseCertificateID().getIssuer());
                }
                if (b(x509Certificate.getSubjectX500Principal(), v2Form.getIssuerName())) {
                    return true;
                }
            } else {
                if (b(x509Certificate.getSubjectX500Principal(), (GeneralNames) aSN1Encodable)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
