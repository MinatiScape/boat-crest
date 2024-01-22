package org.bouncycastle.cert.path.validations;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509ContentVerifierProviderBuilder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class ParentCertIssuedValidation implements CertPathValidation {

    /* renamed from: a  reason: collision with root package name */
    public X509ContentVerifierProviderBuilder f14514a;
    public X500Name b;
    public SubjectPublicKeyInfo c;
    public AlgorithmIdentifier d;

    public ParentCertIssuedValidation(X509ContentVerifierProviderBuilder x509ContentVerifierProviderBuilder) {
        this.f14514a = x509ContentVerifierProviderBuilder;
    }

    public final boolean a(ASN1Encodable aSN1Encodable) {
        return aSN1Encodable == null || (aSN1Encodable instanceof ASN1Null);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        ParentCertIssuedValidation parentCertIssuedValidation = new ParentCertIssuedValidation(this.f14514a);
        parentCertIssuedValidation.d = this.d;
        parentCertIssuedValidation.b = this.b;
        parentCertIssuedValidation.c = this.c;
        return parentCertIssuedValidation;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        ParentCertIssuedValidation parentCertIssuedValidation = (ParentCertIssuedValidation) memoable;
        this.f14514a = parentCertIssuedValidation.f14514a;
        this.d = parentCertIssuedValidation.d;
        this.b = parentCertIssuedValidation.b;
        this.c = parentCertIssuedValidation.c;
    }

    @Override // org.bouncycastle.cert.path.CertPathValidation
    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        X500Name x500Name = this.b;
        if (x500Name != null && !x500Name.equals(x509CertificateHolder.getIssuer())) {
            throw new CertPathValidationException("Certificate issue does not match parent");
        }
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.c;
        if (subjectPublicKeyInfo != null) {
            try {
                if (!x509CertificateHolder.isSignatureValid(this.f14514a.build(subjectPublicKeyInfo.getAlgorithm().equals(this.d) ? this.c : new SubjectPublicKeyInfo(this.d, this.c.parsePublicKey())))) {
                    throw new CertPathValidationException("Certificate signature not for public key in parent");
                }
            } catch (IOException e) {
                throw new CertPathValidationException("Unable to build public key: " + e.getMessage(), e);
            } catch (CertException e2) {
                throw new CertPathValidationException("Unable to validate signature: " + e2.getMessage(), e2);
            } catch (OperatorCreationException e3) {
                throw new CertPathValidationException("Unable to create verifier: " + e3.getMessage(), e3);
            }
        }
        this.b = x509CertificateHolder.getSubject();
        SubjectPublicKeyInfo subjectPublicKeyInfo2 = x509CertificateHolder.getSubjectPublicKeyInfo();
        this.c = subjectPublicKeyInfo2;
        AlgorithmIdentifier algorithmIdentifier = this.d;
        AlgorithmIdentifier algorithm = subjectPublicKeyInfo2.getAlgorithm();
        if (algorithmIdentifier != null) {
            if (algorithm.getAlgorithm().equals(this.d.getAlgorithm()) && a(this.c.getAlgorithm().getParameters())) {
                return;
            }
            algorithm = this.c.getAlgorithm();
        }
        this.d = algorithm;
    }
}
