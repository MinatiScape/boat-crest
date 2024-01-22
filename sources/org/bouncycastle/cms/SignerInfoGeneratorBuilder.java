package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes12.dex */
public class SignerInfoGeneratorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public DigestCalculatorProvider f14547a;
    public boolean b;
    public CMSAttributeTableGenerator c;
    public CMSAttributeTableGenerator d;
    public CMSSignatureEncryptionAlgorithmFinder e;

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider) {
        this(digestCalculatorProvider, new DefaultCMSSignatureEncryptionAlgorithmFinder());
    }

    public SignerInfoGeneratorBuilder(DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder) {
        this.f14547a = digestCalculatorProvider;
        this.e = cMSSignatureEncryptionAlgorithmFinder;
    }

    public final SignerInfoGenerator a(ContentSigner contentSigner, SignerIdentifier signerIdentifier) throws OperatorCreationException {
        if (this.b) {
            return new SignerInfoGenerator(signerIdentifier, contentSigner, this.f14547a, this.e, true);
        }
        CMSAttributeTableGenerator cMSAttributeTableGenerator = this.c;
        if (cMSAttributeTableGenerator == null && this.d == null) {
            return new SignerInfoGenerator(signerIdentifier, contentSigner, this.f14547a, this.e);
        }
        if (cMSAttributeTableGenerator == null) {
            this.c = new DefaultSignedAttributeTableGenerator();
        }
        return new SignerInfoGenerator(signerIdentifier, contentSigner, this.f14547a, this.e, this.c, this.d);
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, X509CertificateHolder x509CertificateHolder) throws OperatorCreationException {
        SignerInfoGenerator a2 = a(contentSigner, new SignerIdentifier(new IssuerAndSerialNumber(x509CertificateHolder.toASN1Structure())));
        a2.c(x509CertificateHolder);
        return a2;
    }

    public SignerInfoGenerator build(ContentSigner contentSigner, byte[] bArr) throws OperatorCreationException {
        return a(contentSigner, new SignerIdentifier((ASN1OctetString) new DEROctetString(bArr)));
    }

    public SignerInfoGeneratorBuilder setDirectSignature(boolean z) {
        this.b = z;
        return this;
    }

    public SignerInfoGeneratorBuilder setSignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.c = cMSAttributeTableGenerator;
        return this;
    }

    public SignerInfoGeneratorBuilder setUnsignedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
        this.d = cMSAttributeTableGenerator;
        return this;
    }
}
