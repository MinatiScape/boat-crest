package org.bouncycastle.cert.path.validations;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.PolicyConstraints;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class CertificatePoliciesValidation implements CertPathValidation {

    /* renamed from: a  reason: collision with root package name */
    public int f14511a;
    public int b;
    public int c;

    public CertificatePoliciesValidation(int i) {
        this(i, false, false, false);
    }

    public CertificatePoliciesValidation(int i, boolean z, boolean z2, boolean z3) {
        if (z) {
            this.f14511a = 0;
        } else {
            this.f14511a = i + 1;
        }
        if (z2) {
            this.c = 0;
        } else {
            this.c = i + 1;
        }
        if (z3) {
            this.b = 0;
        } else {
            this.b = i + 1;
        }
    }

    public final int a(int i) {
        if (i != 0) {
            return i - 1;
        }
        return 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new CertificatePoliciesValidation(0);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        CertificatePoliciesValidation certificatePoliciesValidation = (CertificatePoliciesValidation) memoable;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        int intValue;
        certPathValidationContext.addHandledExtension(Extension.policyConstraints);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = Extension.inhibitAnyPolicy;
        certPathValidationContext.addHandledExtension(aSN1ObjectIdentifier);
        if (certPathValidationContext.isEndEntity() || a.a(x509CertificateHolder)) {
            return;
        }
        this.f14511a = a(this.f14511a);
        this.b = a(this.b);
        this.c = a(this.c);
        PolicyConstraints fromExtensions = PolicyConstraints.fromExtensions(x509CertificateHolder.getExtensions());
        if (fromExtensions != null) {
            BigInteger requireExplicitPolicyMapping = fromExtensions.getRequireExplicitPolicyMapping();
            if (requireExplicitPolicyMapping != null && requireExplicitPolicyMapping.intValue() < this.f14511a) {
                this.f14511a = requireExplicitPolicyMapping.intValue();
            }
            BigInteger inhibitPolicyMapping = fromExtensions.getInhibitPolicyMapping();
            if (inhibitPolicyMapping != null && inhibitPolicyMapping.intValue() < this.b) {
                this.b = inhibitPolicyMapping.intValue();
            }
        }
        Extension extension = x509CertificateHolder.getExtension(aSN1ObjectIdentifier);
        if (extension == null || (intValue = ASN1Integer.getInstance(extension.getParsedValue()).getValue().intValue()) >= this.c) {
            return;
        }
        this.c = intValue;
    }
}
