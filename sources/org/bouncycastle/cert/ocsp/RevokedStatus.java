package org.bouncycastle.cert.ocsp;

import java.util.Date;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ocsp.RevokedInfo;
import org.bouncycastle.asn1.x509.CRLReason;
/* loaded from: classes12.dex */
public class RevokedStatus implements CertificateStatus {

    /* renamed from: a  reason: collision with root package name */
    public RevokedInfo f14502a;

    public RevokedStatus(Date date, int i) {
        this.f14502a = new RevokedInfo(new ASN1GeneralizedTime(date), CRLReason.lookup(i));
    }

    public RevokedStatus(RevokedInfo revokedInfo) {
        this.f14502a = revokedInfo;
    }

    public int getRevocationReason() {
        if (this.f14502a.getRevocationReason() != null) {
            return this.f14502a.getRevocationReason().getValue().intValue();
        }
        throw new IllegalStateException("attempt to get a reason where none is available");
    }

    public Date getRevocationTime() {
        return a.a(this.f14502a.getRevocationTime());
    }

    public boolean hasRevocationReason() {
        return this.f14502a.getRevocationReason() != null;
    }
}
