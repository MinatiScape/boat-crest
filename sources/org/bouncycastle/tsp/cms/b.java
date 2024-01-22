package org.bouncycastle.tsp.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final TimeStampAndCRL[] f15394a;
    public final a b;

    public b(TimeStampedData timeStampedData) {
        this.b = new a(timeStampedData.getMetaData());
        this.f15394a = timeStampedData.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    public b(TimeStampedDataParser timeStampedDataParser) throws IOException {
        this.b = new a(timeStampedDataParser.getMetaData());
        this.f15394a = timeStampedDataParser.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    public byte[] a(DigestCalculator digestCalculator) throws CMSException {
        TimeStampAndCRL[] timeStampAndCRLArr = this.f15394a;
        TimeStampAndCRL timeStampAndCRL = timeStampAndCRLArr[timeStampAndCRLArr.length - 1];
        OutputStream outputStream = digestCalculator.getOutputStream();
        try {
            outputStream.write(timeStampAndCRL.getEncoded(ASN1Encoding.DER));
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e) {
            throw new CMSException("exception calculating hash: " + e.getMessage(), e);
        }
    }

    public final void b(TimeStampToken timeStampToken, byte[] bArr) throws ImprintDigestInvalidException {
        if (!Arrays.areEqual(bArr, timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
        }
    }

    public String c() {
        return this.b.b();
    }

    public String d() {
        return this.b.c();
    }

    public DigestCalculator e(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        try {
            DigestCalculator digestCalculator = digestCalculatorProvider.get(new AlgorithmIdentifier(g(this.f15394a[0]).getTimeStampInfo().getMessageImprintAlgOID()));
            j(digestCalculator);
            return digestCalculator;
        } catch (CMSException e) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + e.getMessage(), e);
        }
    }

    public AttributeTable f() {
        return new AttributeTable(this.b.d());
    }

    public TimeStampToken g(TimeStampAndCRL timeStampAndCRL) throws CMSException {
        try {
            return new TimeStampToken(timeStampAndCRL.getTimeStampToken());
        } catch (IOException e) {
            throw new CMSException("unable to parse token data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("token data invalid: " + e2.getMessage(), e2);
        } catch (TSPException e3) {
            if (e3.getCause() instanceof CMSException) {
                throw ((CMSException) e3.getCause());
            }
            throw new CMSException("token data invalid: " + e3.getMessage(), e3);
        }
    }

    public TimeStampToken[] h() throws CMSException {
        TimeStampToken[] timeStampTokenArr = new TimeStampToken[this.f15394a.length];
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.f15394a;
            if (i >= timeStampAndCRLArr.length) {
                return timeStampTokenArr;
            }
            timeStampTokenArr[i] = g(timeStampAndCRLArr[i]);
            i++;
        }
    }

    public TimeStampAndCRL[] i() {
        return this.f15394a;
    }

    public void j(DigestCalculator digestCalculator) throws CMSException {
        this.b.e(digestCalculator);
    }

    public void k(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) throws ImprintDigestInvalidException, CMSException {
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.f15394a;
            if (i >= timeStampAndCRLArr.length) {
                return;
            }
            try {
                TimeStampToken g = g(timeStampAndCRLArr[i]);
                if (i > 0) {
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(g.getTimeStampInfo().getHashAlgorithm());
                    digestCalculator.getOutputStream().write(this.f15394a[i - 1].getEncoded(ASN1Encoding.DER));
                    bArr = digestCalculator.getDigest();
                }
                b(g, bArr);
                i++;
            } catch (IOException e) {
                throw new CMSException("exception calculating hash: " + e.getMessage(), e);
            } catch (OperatorCreationException e2) {
                throw new CMSException("cannot create digest: " + e2.getMessage(), e2);
            }
        }
    }

    public void l(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        try {
            byte[] encoded = timeStampToken.getEncoded();
            int i = 0;
            while (true) {
                TimeStampAndCRL[] timeStampAndCRLArr = this.f15394a;
                if (i >= timeStampAndCRLArr.length) {
                    throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
                }
                try {
                    TimeStampToken g = g(timeStampAndCRLArr[i]);
                    if (i > 0) {
                        DigestCalculator digestCalculator = digestCalculatorProvider.get(g.getTimeStampInfo().getHashAlgorithm());
                        digestCalculator.getOutputStream().write(this.f15394a[i - 1].getEncoded(ASN1Encoding.DER));
                        bArr = digestCalculator.getDigest();
                    }
                    b(g, bArr);
                    if (Arrays.areEqual(g.getEncoded(), encoded)) {
                        return;
                    }
                    i++;
                } catch (IOException e) {
                    throw new CMSException("exception calculating hash: " + e.getMessage(), e);
                } catch (OperatorCreationException e2) {
                    throw new CMSException("cannot create digest: " + e2.getMessage(), e2);
                }
            }
        } catch (IOException e3) {
            throw new CMSException("exception encoding timeStampToken: " + e3.getMessage(), e3);
        }
    }
}
