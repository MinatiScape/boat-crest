package org.bouncycastle.cert.path.validations;

import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class KeyUsageValidation implements CertPathValidation {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14513a;

    public KeyUsageValidation() {
        this(true);
    }

    public KeyUsageValidation(boolean z) {
        this.f14513a = z;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new KeyUsageValidation(this.f14513a);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        this.f14513a = ((KeyUsageValidation) memoable).f14513a;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        certPathValidationContext.addHandledExtension(Extension.keyUsage);
        if (certPathValidationContext.isEndEntity()) {
            return;
        }
        KeyUsage fromExtensions = KeyUsage.fromExtensions(x509CertificateHolder.getExtensions());
        if (fromExtensions != null) {
            if (!fromExtensions.hasUsages(4)) {
                throw new CertPathValidationException("Issuer certificate KeyUsage extension does not permit key signing");
            }
        } else if (this.f14513a) {
            throw new CertPathValidationException("KeyUsage extension not present in CA certificate");
        }
    }
}
