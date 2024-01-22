package org.bouncycastle.cert.path.validations;

import java.math.BigInteger;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class BasicConstraintsValidation implements CertPathValidation {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14509a;
    public BasicConstraints b;
    public int c;
    public BigInteger d;

    public BasicConstraintsValidation() {
        this(true);
    }

    public BasicConstraintsValidation(boolean z) {
        this.f14509a = z;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        BasicConstraintsValidation basicConstraintsValidation = new BasicConstraintsValidation(this.f14509a);
        basicConstraintsValidation.b = this.b;
        basicConstraintsValidation.c = this.c;
        return basicConstraintsValidation;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        BasicConstraintsValidation basicConstraintsValidation = (BasicConstraintsValidation) memoable;
        this.f14509a = basicConstraintsValidation.f14509a;
        this.b = basicConstraintsValidation.b;
        this.c = basicConstraintsValidation.c;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        int i;
        BigInteger pathLenConstraint;
        int intValue;
        if (this.d != null && this.c < 0) {
            throw new CertPathValidationException("BasicConstraints path length exceeded");
        }
        certPathValidationContext.addHandledExtension(Extension.basicConstraints);
        BasicConstraints fromExtensions = BasicConstraints.fromExtensions(x509CertificateHolder.getExtensions());
        if (fromExtensions != null) {
            if (this.b == null) {
                this.b = fromExtensions;
                if (fromExtensions.isCA()) {
                    BigInteger pathLenConstraint2 = fromExtensions.getPathLenConstraint();
                    this.d = pathLenConstraint2;
                    if (pathLenConstraint2 != null) {
                        i = pathLenConstraint2.intValue();
                        this.c = i;
                    }
                }
            } else if (fromExtensions.isCA() && (pathLenConstraint = fromExtensions.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < this.c) {
                this.c = intValue;
                this.b = fromExtensions;
            }
        } else if (this.b != null) {
            i = this.c - 1;
            this.c = i;
        }
        if (this.f14509a && this.b == null) {
            throw new CertPathValidationException("BasicConstraints not present in path");
        }
    }
}
