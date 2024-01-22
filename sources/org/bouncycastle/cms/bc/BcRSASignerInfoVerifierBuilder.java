package org.bouncycastle.cms.bc;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcRSAContentVerifierProviderBuilder;
/* loaded from: classes12.dex */
public class BcRSASignerInfoVerifierBuilder {

    /* renamed from: a  reason: collision with root package name */
    public BcRSAContentVerifierProviderBuilder f14562a;
    public DigestCalculatorProvider b;
    public CMSSignatureAlgorithmNameGenerator c;
    public SignatureAlgorithmIdentifierFinder d;

    public BcRSASignerInfoVerifierBuilder(CMSSignatureAlgorithmNameGenerator cMSSignatureAlgorithmNameGenerator, SignatureAlgorithmIdentifierFinder signatureAlgorithmIdentifierFinder, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder, DigestCalculatorProvider digestCalculatorProvider) {
        this.c = cMSSignatureAlgorithmNameGenerator;
        this.d = signatureAlgorithmIdentifierFinder;
        this.f14562a = new BcRSAContentVerifierProviderBuilder(digestAlgorithmIdentifierFinder);
        this.b = digestCalculatorProvider;
    }

    public SignerInformationVerifier build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        return new SignerInformationVerifier(this.c, this.d, this.f14562a.build(x509CertificateHolder), this.b);
    }

    public SignerInformationVerifier build(AsymmetricKeyParameter asymmetricKeyParameter) throws OperatorCreationException {
        return new SignerInformationVerifier(this.c, this.d, this.f14562a.build(asymmetricKeyParameter), this.b);
    }
}
