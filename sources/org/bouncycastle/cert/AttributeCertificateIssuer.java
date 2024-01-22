package org.bouncycastle.cert;

import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AttCertIssuer;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.V2Form;
import org.bouncycastle.util.Selector;
/* loaded from: classes12.dex */
public class AttributeCertificateIssuer implements Selector {
    public final ASN1Encodable h;

    public AttributeCertificateIssuer(X500Name x500Name) {
        this.h = new V2Form(new GeneralNames(new GeneralName(x500Name)));
    }

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.h = attCertIssuer.getIssuer();
    }

    public final boolean a(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            GeneralName generalName = names[i];
            if (generalName.getTagNo() == 4 && X500Name.getInstance(generalName.getName()).equals(x500Name)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.bouncycastle.util.Selector
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

    public X500Name[] getNames() {
        ASN1Encodable aSN1Encodable = this.h;
        GeneralName[] names = (aSN1Encodable instanceof V2Form ? ((V2Form) aSN1Encodable).getIssuerName() : (GeneralNames) aSN1Encodable).getNames();
        ArrayList arrayList = new ArrayList(names.length);
        for (int i = 0; i != names.length; i++) {
            if (names[i].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(names[i].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    public int hashCode() {
        return this.h.hashCode();
    }

    @Override // org.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof X509CertificateHolder) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
            ASN1Encodable aSN1Encodable = this.h;
            if (aSN1Encodable instanceof V2Form) {
                V2Form v2Form = (V2Form) aSN1Encodable;
                if (v2Form.getBaseCertificateID() != null) {
                    return v2Form.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && a(x509CertificateHolder.getIssuer(), v2Form.getBaseCertificateID().getIssuer());
                }
                if (a(x509CertificateHolder.getSubject(), v2Form.getIssuerName())) {
                    return true;
                }
            } else {
                if (a(x509CertificateHolder.getSubject(), (GeneralNames) aSN1Encodable)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
