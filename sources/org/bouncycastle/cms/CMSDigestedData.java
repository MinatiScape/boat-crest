package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.DigestedData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;
/* loaded from: classes12.dex */
public class CMSDigestedData implements Encodable {
    public ContentInfo h;
    public DigestedData i;

    public CMSDigestedData(InputStream inputStream) throws CMSException {
        this(g.o(inputStream));
    }

    public CMSDigestedData(ContentInfo contentInfo) throws CMSException {
        this.h = contentInfo;
        try {
            this.i = DigestedData.getInstance(contentInfo.getContent());
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }

    public CMSDigestedData(byte[] bArr) throws CMSException {
        this(g.q(bArr));
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.h.getContentType();
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.i.getDigestAlgorithm();
    }

    public CMSProcessable getDigestedContent() throws CMSException {
        ContentInfo encapContentInfo = this.i.getEncapContentInfo();
        try {
            return new CMSProcessableByteArray(encapContentInfo.getContentType(), ((ASN1OctetString) encapContentInfo.getContent()).getOctets());
        } catch (Exception e) {
            throw new CMSException("exception reading digested stream.", e);
        }
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.h.getEncoded();
    }

    public ContentInfo toASN1Structure() {
        return this.h;
    }

    public boolean verify(DigestCalculatorProvider digestCalculatorProvider) throws CMSException {
        try {
            ContentInfo encapContentInfo = this.i.getEncapContentInfo();
            DigestCalculator digestCalculator = digestCalculatorProvider.get(this.i.getDigestAlgorithm());
            digestCalculator.getOutputStream().write(((ASN1OctetString) encapContentInfo.getContent()).getOctets());
            return Arrays.areEqual(this.i.getDigest(), digestCalculator.getDigest());
        } catch (IOException e) {
            throw new CMSException("unable process content: " + e.getMessage(), e);
        } catch (OperatorCreationException e2) {
            throw new CMSException("unable to create digest calculator: " + e2.getMessage(), e2);
        }
    }
}
