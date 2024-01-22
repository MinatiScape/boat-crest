package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.CompressedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.operator.InputExpanderProvider;
import org.bouncycastle.util.Encodable;
/* loaded from: classes12.dex */
public class CMSCompressedData implements Encodable {
    public ContentInfo h;
    public CompressedData i;

    public CMSCompressedData(InputStream inputStream) throws CMSException {
        this(g.o(inputStream));
    }

    public CMSCompressedData(ContentInfo contentInfo) throws CMSException {
        this.h = contentInfo;
        try {
            this.i = CompressedData.getInstance(contentInfo.getContent());
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }

    public CMSCompressedData(byte[] bArr) throws CMSException {
        this(g.q(bArr));
    }

    public byte[] getContent(InputExpanderProvider inputExpanderProvider) throws CMSException {
        try {
            return g.r(inputExpanderProvider.get(this.i.getCompressionAlgorithmIdentifier()).getInputStream(((ASN1OctetString) this.i.getEncapContentInfo().getContent()).getOctetStream()));
        } catch (IOException e) {
            throw new CMSException("exception reading compressed stream.", e);
        }
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.h.getContentType();
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.h.getEncoded();
    }

    public ContentInfo toASN1Structure() {
        return this.h;
    }
}
