package org.bouncycastle.cert.ocsp;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ocsp.CertStatus;
import org.bouncycastle.asn1.ocsp.RevokedInfo;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes12.dex */
public class SingleResp {

    /* renamed from: a  reason: collision with root package name */
    public SingleResponse f14503a;
    public Extensions b;

    public SingleResp(SingleResponse singleResponse) {
        this.f14503a = singleResponse;
        this.b = singleResponse.getSingleExtensions();
    }

    public CertificateID getCertID() {
        return new CertificateID(this.f14503a.getCertID());
    }

    public CertificateStatus getCertStatus() {
        CertStatus certStatus = this.f14503a.getCertStatus();
        if (certStatus.getTagNo() == 0) {
            return null;
        }
        return certStatus.getTagNo() == 1 ? new RevokedStatus(RevokedInfo.getInstance(certStatus.getStatus())) : new UnknownStatus();
    }

    public Set getCriticalExtensionOIDs() {
        return a.b(this.b);
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.b;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return a.c(this.b);
    }

    public Date getNextUpdate() {
        if (this.f14503a.getNextUpdate() == null) {
            return null;
        }
        return a.a(this.f14503a.getNextUpdate());
    }

    public Set getNonCriticalExtensionOIDs() {
        return a.d(this.b);
    }

    public Date getThisUpdate() {
        return a.a(this.f14503a.getThisUpdate());
    }

    public boolean hasExtensions() {
        return this.b != null;
    }
}
