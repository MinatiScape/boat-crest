package org.bouncycastle.cmc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;
/* loaded from: classes12.dex */
public class SimplePKIResponse implements Encodable {
    public final CMSSignedData h;

    public SimplePKIResponse(ContentInfo contentInfo) throws CMCException {
        try {
            CMSSignedData cMSSignedData = new CMSSignedData(contentInfo);
            this.h = cMSSignedData;
            if (cMSSignedData.getSignerInfos().size() != 0) {
                throw new CMCException("malformed response: SignerInfo structures found");
            }
            if (cMSSignedData.getSignedContent() != null) {
                throw new CMCException("malformed response: Signed Content found");
            }
        } catch (CMSException e) {
            throw new CMCException("malformed response: " + e.getMessage(), e);
        }
    }

    public SimplePKIResponse(byte[] bArr) throws CMCException {
        this(a(bArr));
    }

    public static ContentInfo a(byte[] bArr) throws CMCException {
        try {
            return ContentInfo.getInstance(ASN1Primitive.fromByteArray(bArr));
        } catch (Exception e) {
            throw new CMCException("malformed data: " + e.getMessage(), e);
        }
    }

    public Store<X509CRLHolder> getCRLs() {
        return this.h.getCRLs();
    }

    public Store<X509CertificateHolder> getCertificates() {
        return this.h.getCertificates();
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.h.getEncoded();
    }
}
