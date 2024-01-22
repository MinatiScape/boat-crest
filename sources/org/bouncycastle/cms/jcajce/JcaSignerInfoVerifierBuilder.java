package org.bouncycastle.cms.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
/* loaded from: classes12.dex */
public class JcaSignerInfoVerifierBuilder {
    public DigestCalculatorProvider b;

    /* renamed from: a  reason: collision with root package name */
    public b f14571a = new b();
    public CMSSignatureAlgorithmNameGenerator c = new DefaultCMSSignatureAlgorithmNameGenerator();
    public SignatureAlgorithmIdentifierFinder d = new DefaultSignatureAlgorithmIdentifierFinder();

    /* loaded from: classes12.dex */
    public class b {
        public b(JcaSignerInfoVerifierBuilder jcaSignerInfoVerifierBuilder) {
        }

        public ContentVerifierProvider a(PublicKey publicKey) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().build(publicKey);
        }

        public ContentVerifierProvider b(X509Certificate x509Certificate) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().build(x509Certificate);
        }

        public ContentVerifierProvider c(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
            return new JcaContentVerifierProviderBuilder().build(x509CertificateHolder);
        }
    }

    /* loaded from: classes12.dex */
    public class c extends b {

        /* renamed from: a  reason: collision with root package name */
        public final String f14572a;

        public c(JcaSignerInfoVerifierBuilder jcaSignerInfoVerifierBuilder, String str) {
            super();
            this.f14572a = str;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider a(PublicKey publicKey) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14572a).build(publicKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider b(X509Certificate x509Certificate) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14572a).build(x509Certificate);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider c(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14572a).build(x509CertificateHolder);
        }
    }

    /* loaded from: classes12.dex */
    public class d extends b {

        /* renamed from: a  reason: collision with root package name */
        public final Provider f14573a;

        public d(JcaSignerInfoVerifierBuilder jcaSignerInfoVerifierBuilder, Provider provider) {
            super();
            this.f14573a = provider;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider a(PublicKey publicKey) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14573a).build(publicKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider b(X509Certificate x509Certificate) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14573a).build(x509Certificate);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSignerInfoVerifierBuilder.b
        public ContentVerifierProvider c(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14573a).build(x509CertificateHolder);
        }
    }

    public JcaSignerInfoVerifierBuilder(DigestCalculatorProvider digestCalculatorProvider) {
        this.b = digestCalculatorProvider;
    }

    public SignerInformationVerifier build(PublicKey publicKey) throws OperatorCreationException {
        return new SignerInformationVerifier(this.c, this.d, this.f14571a.a(publicKey), this.b);
    }

    public SignerInformationVerifier build(X509Certificate x509Certificate) throws OperatorCreationException {
        return new SignerInformationVerifier(this.c, this.d, this.f14571a.b(x509Certificate), this.b);
    }

    public SignerInformationVerifier build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return new SignerInformationVerifier(this.c, this.d, this.f14571a.c(x509CertificateHolder), this.b);
    }

    public JcaSignerInfoVerifierBuilder setProvider(String str) {
        this.f14571a = new c(this, str);
        return this;
    }

    public JcaSignerInfoVerifierBuilder setProvider(Provider provider) {
        this.f14571a = new d(this, provider);
        return this;
    }

    public JcaSignerInfoVerifierBuilder setSignatureAlgorithmFinder(SignatureAlgorithmIdentifierFinder signatureAlgorithmIdentifierFinder) {
        this.d = signatureAlgorithmIdentifierFinder;
        return this;
    }

    public JcaSignerInfoVerifierBuilder setSignatureAlgorithmNameGenerator(CMSSignatureAlgorithmNameGenerator cMSSignatureAlgorithmNameGenerator) {
        this.c = cMSSignatureAlgorithmNameGenerator;
        return this;
    }
}
