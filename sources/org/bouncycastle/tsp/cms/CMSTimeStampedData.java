package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampTokenEvidence;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TimeStampToken;
/* loaded from: classes13.dex */
public class CMSTimeStampedData {

    /* renamed from: a  reason: collision with root package name */
    public TimeStampedData f15391a;
    public ContentInfo b;
    public b c;

    public CMSTimeStampedData(InputStream inputStream) throws IOException {
        try {
            a(ContentInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
        } catch (ClassCastException e) {
            throw new IOException("Malformed content: " + e);
        } catch (IllegalArgumentException e2) {
            throw new IOException("Malformed content: " + e2);
        }
    }

    public CMSTimeStampedData(ContentInfo contentInfo) {
        a(contentInfo);
    }

    public CMSTimeStampedData(byte[] bArr) throws IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public final void a(ContentInfo contentInfo) {
        this.b = contentInfo;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSObjectIdentifiers.timestampedData;
        if (aSN1ObjectIdentifier.equals(contentInfo.getContentType())) {
            TimeStampedData timeStampedData = TimeStampedData.getInstance(contentInfo.getContent());
            this.f15391a = timeStampedData;
            this.c = new b(timeStampedData);
            return;
        }
        throw new IllegalArgumentException("Malformed content - type must be " + aSN1ObjectIdentifier.getId());
    }

    public CMSTimeStampedData addTimeStamp(TimeStampToken timeStampToken) throws CMSException {
        TimeStampAndCRL[] i = this.c.i();
        TimeStampAndCRL[] timeStampAndCRLArr = new TimeStampAndCRL[i.length + 1];
        System.arraycopy(i, 0, timeStampAndCRLArr, 0, i.length);
        timeStampAndCRLArr[i.length] = new TimeStampAndCRL(timeStampToken.toCMSSignedData().toASN1Structure());
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(this.f15391a.getDataUri(), this.f15391a.getMetaData(), this.f15391a.getContent(), new Evidence(new TimeStampTokenEvidence(timeStampAndCRLArr)))));
    }

    public byte[] calculateNextHash(DigestCalculator digestCalculator) throws CMSException {
        return this.c.a(digestCalculator);
    }

    public byte[] getContent() {
        if (this.f15391a.getContent() != null) {
            return this.f15391a.getContent().getOctets();
        }
        return null;
    }

    public URI getDataUri() throws URISyntaxException {
        DERIA5String dataUri = this.f15391a.getDataUri();
        if (dataUri != null) {
            return new URI(dataUri.getString());
        }
        return null;
    }

    public byte[] getEncoded() throws IOException {
        return this.b.getEncoded();
    }

    public String getFileName() {
        return this.c.c();
    }

    public String getMediaType() {
        return this.c.d();
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        return this.c.e(digestCalculatorProvider);
    }

    public AttributeTable getOtherMetaData() {
        return this.c.f();
    }

    public TimeStampToken[] getTimeStampTokens() throws CMSException {
        return this.c.h();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        this.c.j(digestCalculator);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) throws ImprintDigestInvalidException, CMSException {
        this.c.k(digestCalculatorProvider, bArr);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        this.c.l(digestCalculatorProvider, bArr, timeStampToken);
    }
}
