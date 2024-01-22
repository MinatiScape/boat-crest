package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
/* loaded from: classes12.dex */
public class JcaSimpleSignerInfoGeneratorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public b f14574a = new b();
    public boolean b;
    public CMSAttributeTableGenerator c;
    public CMSAttributeTableGenerator d;

    /* loaded from: classes12.dex */
    public class b {
        public b(JcaSimpleSignerInfoGeneratorBuilder jcaSimpleSignerInfoGeneratorBuilder) {
        }

        public ContentSigner a(String str, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(str).build(privateKey);
        }

        public DigestCalculatorProvider b() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().build();
        }
    }

    /* loaded from: classes12.dex */
    public class c extends b {

        /* renamed from: a  reason: collision with root package name */
        public final String f14575a;

        public c(JcaSimpleSignerInfoGeneratorBuilder jcaSimpleSignerInfoGeneratorBuilder, String str) {
            super();
            this.f14575a = str;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder.b
        public ContentSigner a(String str, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(str).setProvider(this.f14575a).build(privateKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder.b
        public DigestCalculatorProvider b() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.f14575a).build();
        }
    }

    /* loaded from: classes12.dex */
    public class d extends b {

        /* renamed from: a  reason: collision with root package name */
        public final Provider f14576a;

        public d(JcaSimpleSignerInfoGeneratorBuilder jcaSimpleSignerInfoGeneratorBuilder, Provider provider) {
            super();
            this.f14576a = provider;
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder.b
        public ContentSigner a(String str, PrivateKey privateKey) throws OperatorCreationException {
            return new JcaContentSignerBuilder(str).setProvider(this.f14576a).build(privateKey);
        }

        @Override // org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder.b
        public DigestCalculatorProvider b() throws OperatorCreationException {
            return new JcaDigestCalculatorProviderBuilder().setProvider(this.f14576a).build();
        }
    }

    public final SignerInfoGeneratorBuilder a() throws OperatorCreationException {
        SignerInfoGeneratorBuilder signerInfoGeneratorBuilder = new SignerInfoGeneratorBuilder(this.f14574a.b());
        signerInfoGeneratorBuilder.setDirectSignature(this.b);
        signerInfoGeneratorBuilder.setSignedAttributeGenerator(this.c);
        signerInfoGeneratorBuilder.setUnsignedAttributeGenerator(this.d);
        return signerInfoGeneratorBuilder;
    }

    public SignerInfoGenerator build(String str, PrivateKey privateKey, X509Certificate x509Certificate) throws OperatorCreationException, CertificateEncodingException {
        return a().build(this.f14574a.a(str, privateKey), new JcaX509CertificateHolder(x509Certificate));
    }

    public SignerInfoGenerator build(String str, PrivateKey privateKey, byte[] bArr) throws OperatorCreationException, CertificateEncodingException {
        return a().build(this.f14574a.a(str, privateKey), bArr);
    }

    public JcaSimpleSignerInfoGeneratorBuilder setDirectSignature(boolean z) {
        this.b = z;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(String str) throws OperatorCreationException {
        this.f14574a = new c(this, str);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setProvider(Provider provider) throws OperatorCreationException {
        this.f14574a = new d(this, provider);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(AttributeTable attributeTable) {
        this.c = new DefaultSignedAttributeTableGenerator(attributeTable);
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.c = cMSAttributeTableGenerator;
        return this;
    }

    public JcaSimpleSignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.d = cMSAttributeTableGenerator;
        return this;
    }
}
