package org.bouncycastle.cms;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder;
/* loaded from: classes12.dex */
public class SignerInformationVerifier {

    /* renamed from: a  reason: collision with root package name */
    public ContentVerifierProvider f14549a;
    public DigestCalculatorProvider b;
    public SignatureAlgorithmIdentifierFinder c;
    public CMSSignatureAlgorithmNameGenerator d;

    public SignerInformationVerifier(CMSSignatureAlgorithmNameGenerator cMSSignatureAlgorithmNameGenerator, SignatureAlgorithmIdentifierFinder signatureAlgorithmIdentifierFinder, ContentVerifierProvider contentVerifierProvider, DigestCalculatorProvider digestCalculatorProvider) {
        this.d = cMSSignatureAlgorithmNameGenerator;
        this.c = signatureAlgorithmIdentifierFinder;
        this.f14549a = contentVerifierProvider;
        this.b = digestCalculatorProvider;
    }

    public X509CertificateHolder getAssociatedCertificate() {
        return this.f14549a.getAssociatedCertificate();
    }

    public ContentVerifier getContentVerifier(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws OperatorCreationException {
        return this.f14549a.get(new AlgorithmIdentifier(this.c.find(this.d.getSignatureName(algorithmIdentifier2, algorithmIdentifier)).getAlgorithm(), algorithmIdentifier.getParameters()));
    }

    public DigestCalculator getDigestCalculator(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        return this.b.get(algorithmIdentifier);
    }

    public boolean hasAssociatedCertificate() {
        return this.f14549a.hasAssociatedCertificate();
    }
}
