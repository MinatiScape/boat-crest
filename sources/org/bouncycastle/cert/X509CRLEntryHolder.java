package org.bouncycastle.cert;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.TBSCertList;
/* loaded from: classes12.dex */
public class X509CRLEntryHolder {

    /* renamed from: a  reason: collision with root package name */
    public TBSCertList.CRLEntry f14441a;
    public GeneralNames b;

    public X509CRLEntryHolder(TBSCertList.CRLEntry cRLEntry, boolean z, GeneralNames generalNames) {
        Extension extension;
        this.f14441a = cRLEntry;
        this.b = generalNames;
        if (z && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
            this.b = GeneralNames.getInstance(extension.getParsedValue());
        }
    }

    public GeneralNames getCertificateIssuer() {
        return this.b;
    }

    public Set getCriticalExtensionOIDs() {
        return a.k(this.f14441a.getExtensions());
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.f14441a.getExtensions();
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return a.l(this.f14441a.getExtensions());
    }

    public Extensions getExtensions() {
        return this.f14441a.getExtensions();
    }

    public Set getNonCriticalExtensionOIDs() {
        return a.m(this.f14441a.getExtensions());
    }

    public Date getRevocationDate() {
        return this.f14441a.getRevocationDate().getDate();
    }

    public BigInteger getSerialNumber() {
        return this.f14441a.getUserCertificate().getValue();
    }

    public boolean hasExtensions() {
        return this.f14441a.hasExtensions();
    }
}
