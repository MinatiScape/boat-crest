package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.SignerIdentifier;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.TeeOutputStream;
/* loaded from: classes12.dex */
public class SignerInfoGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final SignerIdentifier f14546a;
    public final CMSAttributeTableGenerator b;
    public final CMSAttributeTableGenerator c;
    public final ContentSigner d;
    public final DigestCalculator e;
    public final DigestAlgorithmIdentifierFinder f;
    public final CMSSignatureEncryptionAlgorithmFinder g;
    public byte[] h;
    public X509CertificateHolder i;

    public SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder) throws OperatorCreationException {
        this(signerIdentifier, contentSigner, digestCalculatorProvider, cMSSignatureEncryptionAlgorithmFinder, false);
    }

    public SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder, CMSAttributeTableGenerator cMSAttributeTableGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator2) throws OperatorCreationException {
        DefaultDigestAlgorithmIdentifierFinder defaultDigestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
        this.f = defaultDigestAlgorithmIdentifierFinder;
        this.h = null;
        this.f14546a = signerIdentifier;
        this.d = contentSigner;
        if (digestCalculatorProvider != null) {
            this.e = digestCalculatorProvider.get(defaultDigestAlgorithmIdentifierFinder.find(contentSigner.getAlgorithmIdentifier()));
        } else {
            this.e = null;
        }
        this.b = cMSAttributeTableGenerator;
        this.c = cMSAttributeTableGenerator2;
        this.g = cMSSignatureEncryptionAlgorithmFinder;
    }

    public SignerInfoGenerator(SignerIdentifier signerIdentifier, ContentSigner contentSigner, DigestCalculatorProvider digestCalculatorProvider, CMSSignatureEncryptionAlgorithmFinder cMSSignatureEncryptionAlgorithmFinder, boolean z) throws OperatorCreationException {
        DefaultDigestAlgorithmIdentifierFinder defaultDigestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
        this.f = defaultDigestAlgorithmIdentifierFinder;
        this.h = null;
        this.f14546a = signerIdentifier;
        this.d = contentSigner;
        if (digestCalculatorProvider != null) {
            this.e = digestCalculatorProvider.get(defaultDigestAlgorithmIdentifierFinder.find(contentSigner.getAlgorithmIdentifier()));
        } else {
            this.e = null;
        }
        if (z) {
            this.b = null;
        } else {
            this.b = new DefaultSignedAttributeTableGenerator();
        }
        this.c = null;
        this.g = cMSSignatureEncryptionAlgorithmFinder;
    }

    public SignerInfoGenerator(SignerInfoGenerator signerInfoGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator, CMSAttributeTableGenerator cMSAttributeTableGenerator2) {
        this.f = new DefaultDigestAlgorithmIdentifierFinder();
        this.h = null;
        this.f14546a = signerInfoGenerator.f14546a;
        this.d = signerInfoGenerator.d;
        this.e = signerInfoGenerator.e;
        this.g = signerInfoGenerator.g;
        this.b = cMSAttributeTableGenerator;
        this.c = cMSAttributeTableGenerator2;
    }

    public final ASN1Set a(AttributeTable attributeTable) {
        if (attributeTable != null) {
            return new DERSet(attributeTable.toASN1EncodableVector());
        }
        return null;
    }

    public final Map b(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        HashMap hashMap = new HashMap();
        if (aSN1ObjectIdentifier != null) {
            hashMap.put(CMSAttributeTableGenerator.CONTENT_TYPE, aSN1ObjectIdentifier);
        }
        hashMap.put(CMSAttributeTableGenerator.DIGEST_ALGORITHM_IDENTIFIER, algorithmIdentifier);
        hashMap.put(CMSAttributeTableGenerator.SIGNATURE_ALGORITHM_IDENTIFIER, algorithmIdentifier2);
        hashMap.put(CMSAttributeTableGenerator.DIGEST, Arrays.clone(bArr));
        return hashMap;
    }

    public void c(X509CertificateHolder x509CertificateHolder) {
        this.i = x509CertificateHolder;
    }

    public SignerInfo generate(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        AlgorithmIdentifier find;
        AlgorithmIdentifier algorithmIdentifier;
        ASN1Set aSN1Set;
        ASN1Set aSN1Set2;
        try {
            AlgorithmIdentifier findEncryptionAlgorithm = this.g.findEncryptionAlgorithm(this.d.getAlgorithmIdentifier());
            if (this.b != null) {
                AlgorithmIdentifier algorithmIdentifier2 = this.e.getAlgorithmIdentifier();
                this.h = this.e.getDigest();
                ASN1Set a2 = a(this.b.getAttributes(Collections.unmodifiableMap(b(aSN1ObjectIdentifier, this.e.getAlgorithmIdentifier(), findEncryptionAlgorithm, this.h))));
                OutputStream outputStream = this.d.getOutputStream();
                outputStream.write(a2.getEncoded(ASN1Encoding.DER));
                outputStream.close();
                algorithmIdentifier = algorithmIdentifier2;
                aSN1Set = a2;
            } else {
                DigestCalculator digestCalculator = this.e;
                if (digestCalculator != null) {
                    find = digestCalculator.getAlgorithmIdentifier();
                    this.h = this.e.getDigest();
                } else {
                    find = this.f.find(this.d.getAlgorithmIdentifier());
                    this.h = null;
                }
                algorithmIdentifier = find;
                aSN1Set = null;
            }
            byte[] signature = this.d.getSignature();
            if (this.c != null) {
                Map b = b(aSN1ObjectIdentifier, algorithmIdentifier, findEncryptionAlgorithm, this.h);
                b.put(CMSAttributeTableGenerator.SIGNATURE, Arrays.clone(signature));
                aSN1Set2 = a(this.c.getAttributes(Collections.unmodifiableMap(b)));
            } else {
                aSN1Set2 = null;
            }
            return new SignerInfo(this.f14546a, algorithmIdentifier, aSN1Set, findEncryptionAlgorithm, new DEROctetString(signature), aSN1Set2);
        } catch (IOException e) {
            throw new CMSException("encoding error.", e);
        }
    }

    public X509CertificateHolder getAssociatedCertificate() {
        return this.i;
    }

    public byte[] getCalculatedDigest() {
        byte[] bArr = this.h;
        if (bArr != null) {
            return Arrays.clone(bArr);
        }
        return null;
    }

    public OutputStream getCalculatingOutputStream() {
        DigestCalculator digestCalculator = this.e;
        return digestCalculator != null ? this.b == null ? new TeeOutputStream(this.e.getOutputStream(), this.d.getOutputStream()) : digestCalculator.getOutputStream() : this.d.getOutputStream();
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        DigestCalculator digestCalculator = this.e;
        return digestCalculator != null ? digestCalculator.getAlgorithmIdentifier() : this.f.find(this.d.getAlgorithmIdentifier());
    }

    public int getGeneratedVersion() {
        return this.f14546a.isTagged() ? 3 : 1;
    }

    public SignerIdentifier getSID() {
        return this.f14546a;
    }

    public CMSAttributeTableGenerator getSignedAttributeTableGenerator() {
        return this.b;
    }

    public CMSAttributeTableGenerator getUnsignedAttributeTableGenerator() {
        return this.c;
    }

    public boolean hasAssociatedCertificate() {
        return this.i != null;
    }
}
