package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CertificateStatus {

    /* renamed from: a  reason: collision with root package name */
    public DigestAlgorithmIdentifierFinder f14451a;
    public CertStatus b;

    public CertificateStatus(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder, CertStatus certStatus) {
        this.f14451a = digestAlgorithmIdentifierFinder;
        this.b = certStatus;
    }

    public BigInteger getCertRequestID() {
        return this.b.getCertReqId().getValue();
    }

    public PKIStatusInfo getStatusInfo() {
        return this.b.getStatusInfo();
    }

    public boolean isVerified(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        AlgorithmIdentifier find = this.f14451a.find(x509CertificateHolder.toASN1Structure().getSignatureAlgorithm());
        if (find != null) {
            try {
                DigestCalculator digestCalculator = digestCalculatorProvider.get(find);
                a.a(x509CertificateHolder.toASN1Structure(), digestCalculator.getOutputStream());
                return Arrays.areEqual(this.b.getCertHash().getOctets(), digestCalculator.getDigest());
            } catch (OperatorCreationException e) {
                throw new CMPException("unable to create digester: " + e.getMessage(), e);
            }
        }
        throw new CMPException("cannot find algorithm for digest from signature");
    }
}
