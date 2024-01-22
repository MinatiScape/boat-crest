package org.bouncycastle.cert.path;

import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes12.dex */
public class CertPath {

    /* renamed from: a  reason: collision with root package name */
    public final X509CertificateHolder[] f14505a;

    public CertPath(X509CertificateHolder[] x509CertificateHolderArr) {
        this.f14505a = a(x509CertificateHolderArr);
    }

    public final X509CertificateHolder[] a(X509CertificateHolder[] x509CertificateHolderArr) {
        int length = x509CertificateHolderArr.length;
        X509CertificateHolder[] x509CertificateHolderArr2 = new X509CertificateHolder[length];
        System.arraycopy(x509CertificateHolderArr, 0, x509CertificateHolderArr2, 0, length);
        return x509CertificateHolderArr2;
    }

    public CertPathValidationResult evaluate(CertPathValidation[] certPathValidationArr) {
        CertPathValidationContext certPathValidationContext = new CertPathValidationContext(a.a(this.f14505a));
        b bVar = new b(certPathValidationContext);
        for (int i = 0; i != certPathValidationArr.length; i++) {
            int length = this.f14505a.length - 1;
            while (length >= 0) {
                try {
                    certPathValidationContext.setIsEndEntity(length == 0);
                    certPathValidationArr[i].validate(certPathValidationContext, this.f14505a[length]);
                } catch (CertPathValidationException e) {
                    bVar.a(length, i, e);
                }
                length--;
            }
        }
        return bVar.b();
    }

    public X509CertificateHolder[] getCertificates() {
        return a(this.f14505a);
    }

    public int length() {
        return this.f14505a.length;
    }

    public CertPathValidationResult validate(CertPathValidation[] certPathValidationArr) {
        CertPathValidationContext certPathValidationContext = new CertPathValidationContext(a.a(this.f14505a));
        for (int i = 0; i != certPathValidationArr.length; i++) {
            int length = this.f14505a.length - 1;
            while (length >= 0) {
                try {
                    certPathValidationContext.setIsEndEntity(length == 0);
                    certPathValidationArr[i].validate(certPathValidationContext, this.f14505a[length]);
                    length--;
                } catch (CertPathValidationException e) {
                    return new CertPathValidationResult(certPathValidationContext, length, i, e);
                }
            }
        }
        return new CertPathValidationResult(certPathValidationContext);
    }
}
