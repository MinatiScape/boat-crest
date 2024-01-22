package org.bouncycastle.cms.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
/* loaded from: classes12.dex */
public class JcaSimpleSignerInfoVerifierBuilder {

    /* renamed from: a  reason: collision with root package name */
    public b f14577a = new b();

    /* loaded from: classes12.dex */
    public class b {
        public b(JcaSimpleSignerInfoVerifierBuilder jcaSimpleSignerInfoVerifierBuilder) {
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

        public DigestCalculatorProvider d() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().build();
        }
    }

    /* loaded from: classes12.dex */
    public class c extends b {

        /* renamed from: a  reason: collision with root package name */
        public final String f14578a;

        public c(JcaSimpleSignerInfoVerifierBuilder jcaSimpleSignerInfoVerifierBuilder, String str) {
            super();
            this.f14578a = str;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider a(PublicKey publicKey) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14578a).build(publicKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider b(X509Certificate x509Certificate) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14578a).build(x509Certificate);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider c(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14578a).build(x509CertificateHolder);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public DigestCalculatorProvider d() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.f14578a).build();
        }
    }

    /* loaded from: classes12.dex */
    public class d extends b {

        /* renamed from: a  reason: collision with root package name */
        public final Provider f14579a;

        public d(JcaSimpleSignerInfoVerifierBuilder jcaSimpleSignerInfoVerifierBuilder, Provider provider) {
            super();
            this.f14579a = provider;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider a(PublicKey publicKey) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14579a).build(publicKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider b(X509Certificate x509Certificate) throws OperatorCreationException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14579a).build(x509Certificate);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public ContentVerifierProvider c(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
            return new JcaContentVerifierProviderBuilder().setProvider(this.f14579a).build(x509CertificateHolder);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder.b
        public DigestCalculatorProvider d() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.f14579a).build();
        }
    }

    public SignerInformationVerifier build(PublicKey publicKey) throws OperatorCreationException {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), this.f14577a.a(publicKey), this.f14577a.d());
    }

    public SignerInformationVerifier build(X509Certificate x509Certificate) throws OperatorCreationException {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), this.f14577a.b(x509Certificate), this.f14577a.d());
    }

    public SignerInformationVerifier build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException, CertificateException {
        return new SignerInformationVerifier(new DefaultCMSSignatureAlgorithmNameGenerator(), new DefaultSignatureAlgorithmIdentifierFinder(), this.f14577a.c(x509CertificateHolder), this.f14577a.d());
    }

    public JcaSimpleSignerInfoVerifierBuilder setProvider(String str) {
        this.f14577a = new c(this, str);
        return this;
    }

    public JcaSimpleSignerInfoVerifierBuilder setProvider(Provider provider) {
        this.f14577a = new d(this, provider);
        return this;
    }
}
