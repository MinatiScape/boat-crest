package org.bouncycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateException;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509ContentVerifierProviderBuilder;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
/* loaded from: classes12.dex */
public class JcaX509ContentVerifierProviderBuilder implements X509ContentVerifierProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaContentVerifierProviderBuilder f14488a = new JcaContentVerifierProviderBuilder();

    @Override // org.bouncycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        return this.f14488a.build(subjectPublicKeyInfo);
    }

    @Override // org.bouncycastle.cert.X509ContentVerifierProviderBuilder
    public ContentVerifierProvider build(X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        try {
            return this.f14488a.build(x509CertificateHolder);
        } catch (CertificateException e) {
            throw new OperatorCreationException("Unable to process certificate: " + e.getMessage(), e);
        }
    }

    public JcaX509ContentVerifierProviderBuilder setProvider(String str) {
        this.f14488a.setProvider(str);
        return this;
    }

    public JcaX509ContentVerifierProviderBuilder setProvider(Provider provider) {
        this.f14488a.setProvider(provider);
        return this;
    }
}
