package org.bouncycastle.cert.path.validations;

import java.util.Collection;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class CRLValidation implements CertPathValidation {

    /* renamed from: a  reason: collision with root package name */
    public Store f14510a;
    public X500Name b;

    /* loaded from: classes12.dex */
    public class a implements Selector {
        public a() {
        }

        @Override // org.bouncycastle.util.Selector
        public Object clone() {
            return this;
        }

        @Override // org.bouncycastle.util.Selector
        public boolean match(Object obj) {
            return ((X509CRLHolder) obj).getIssuer().equals(CRLValidation.this.b);
        }
    }

    public CRLValidation(X500Name x500Name, Store store) {
        this.b = x500Name;
        this.f14510a = store;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new CRLValidation(this.b, this.f14510a);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        CRLValidation cRLValidation = (CRLValidation) memoable;
        this.b = cRLValidation.b;
        this.f14510a = cRLValidation.f14510a;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        Collection<X509CRLHolder> matches = this.f14510a.getMatches(new a());
        if (matches.isEmpty()) {
            throw new CertPathValidationException("CRL for " + this.b + " not found");
        }
        for (X509CRLHolder x509CRLHolder : matches) {
            if (x509CRLHolder.getRevokedCertificate(x509CertificateHolder.getSerialNumber()) != null) {
                throw new CertPathValidationException("Certificate revoked");
            }
        }
        this.b = x509CertificateHolder.getSubject();
    }
}
