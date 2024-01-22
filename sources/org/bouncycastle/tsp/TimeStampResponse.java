package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TimeStampResponse {

    /* renamed from: a  reason: collision with root package name */
    public TimeStampResp f15383a;
    public TimeStampToken b;

    public TimeStampResponse(InputStream inputStream) throws TSPException, IOException {
        this(a(inputStream));
    }

    public TimeStampResponse(DLSequence dLSequence) throws TSPException, IOException {
        try {
            this.f15383a = TimeStampResp.getInstance(dLSequence);
            this.b = new TimeStampToken(ContentInfo.getInstance(dLSequence.getObjectAt(1)));
        } catch (ClassCastException e) {
            throw new TSPException("malformed timestamp response: " + e, e);
        } catch (IllegalArgumentException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        }
    }

    public TimeStampResponse(TimeStampResp timeStampResp) throws TSPException, IOException {
        this.f15383a = timeStampResp;
        if (timeStampResp.getTimeStampToken() != null) {
            this.b = new TimeStampToken(timeStampResp.getTimeStampToken());
        }
    }

    public TimeStampResponse(byte[] bArr) throws TSPException, IOException {
        this(new ByteArrayInputStream(bArr));
    }

    public static TimeStampResp a(InputStream inputStream) throws IOException, TSPException {
        try {
            return TimeStampResp.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (ClassCastException e) {
            throw new TSPException("malformed timestamp response: " + e, e);
        } catch (IllegalArgumentException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.f15383a.getEncoded();
    }

    public byte[] getEncoded(String str) throws IOException {
        return (ASN1Encoding.DL.equals(str) ? new DLSequence(new ASN1Encodable[]{this.f15383a.getStatus(), this.b.toCMSSignedData().toASN1Structure()}) : this.f15383a).getEncoded(str);
    }

    public PKIFailureInfo getFailInfo() {
        if (this.f15383a.getStatus().getFailInfo() != null) {
            return new PKIFailureInfo(this.f15383a.getStatus().getFailInfo());
        }
        return null;
    }

    public int getStatus() {
        return this.f15383a.getStatus().getStatus().intValue();
    }

    public String getStatusString() {
        if (this.f15383a.getStatus().getStatusString() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            PKIFreeText statusString = this.f15383a.getStatus().getStatusString();
            for (int i = 0; i != statusString.size(); i++) {
                stringBuffer.append(statusString.getStringAt(i).getString());
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public TimeStampToken getTimeStampToken() {
        return this.b;
    }

    public void validate(TimeStampRequest timeStampRequest) throws TSPException {
        TimeStampToken timeStampToken = getTimeStampToken();
        if (timeStampToken == null) {
            if (getStatus() == 0 || getStatus() == 1) {
                throw new TSPValidationException("no time stamp token found and one expected.");
            }
            return;
        }
        TimeStampTokenInfo timeStampInfo = timeStampToken.getTimeStampInfo();
        if (timeStampRequest.getNonce() != null && !timeStampRequest.getNonce().equals(timeStampInfo.getNonce())) {
            throw new TSPValidationException("response contains wrong nonce value.");
        }
        if (getStatus() != 0 && getStatus() != 1) {
            throw new TSPValidationException("time stamp token found in failed request.");
        }
        if (!Arrays.constantTimeAreEqual(timeStampRequest.getMessageImprintDigest(), timeStampInfo.getMessageImprintDigest())) {
            throw new TSPValidationException("response for different message imprint digest.");
        }
        if (!timeStampInfo.getMessageImprintAlgOID().equals(timeStampRequest.getMessageImprintAlgOID())) {
            throw new TSPValidationException("response for different message imprint algorithm.");
        }
        Attribute attribute = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
        Attribute attribute2 = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
        if (attribute == null && attribute2 == null) {
            throw new TSPValidationException("no signing certificate attribute present.");
        }
        if (timeStampRequest.getReqPolicy() != null && !timeStampRequest.getReqPolicy().equals(timeStampInfo.getPolicy())) {
            throw new TSPValidationException("TSA policy wrong for request.");
        }
    }
}
