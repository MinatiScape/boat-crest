package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.CertConfirmContent;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes12.dex */
public class CertificateConfirmationContentBuilder {

    /* renamed from: a  reason: collision with root package name */
    public DigestAlgorithmIdentifierFinder f14450a;
    public List b;
    public List c;

    public CertificateConfirmationContentBuilder() {
        this(new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContentBuilder(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.f14450a = digestAlgorithmIdentifierFinder;
    }

    public CertificateConfirmationContentBuilder addAcceptedCertificate(X509CertificateHolder x509CertificateHolder, BigInteger bigInteger) {
        this.b.add(x509CertificateHolder);
        this.c.add(bigInteger);
        return this;
    }

    public CertificateConfirmationContent build(DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.b.size(); i++) {
            X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) this.b.get(i);
            BigInteger bigInteger = (BigInteger) this.c.get(i);
            AlgorithmIdentifier find = this.f14450a.find(x509CertificateHolder.toASN1Structure().getSignatureAlgorithm());
            if (find == null) {
                throw new CMPException("cannot find algorithm for digest from signature");
            }
            try {
                DigestCalculator digestCalculator = digestCalculatorProvider.get(find);
                a.a(x509CertificateHolder.toASN1Structure(), digestCalculator.getOutputStream());
                aSN1EncodableVector.add(new CertStatus(digestCalculator.getDigest(), bigInteger));
            } catch (OperatorCreationException e) {
                throw new CMPException("unable to create digest: " + e.getMessage(), e);
            }
        }
        return new CertificateConfirmationContent(CertConfirmContent.getInstance(new DERSequence(aSN1EncodableVector)), this.f14450a);
    }
}
