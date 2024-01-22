package org.bouncycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes12.dex */
public class PKCS7ProcessableObject implements CMSTypedData {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14541a;
    public final ASN1Encodable b;

    public PKCS7ProcessableObject(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.f14541a = aSN1ObjectIdentifier;
        this.b = aSN1Encodable;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return this.b;
    }

    @Override // org.bouncycastle.cms.CMSTypedData
    public ASN1ObjectIdentifier getContentType() {
        return this.f14541a;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        ASN1Encodable aSN1Encodable = this.b;
        if (aSN1Encodable instanceof ASN1Sequence) {
            Iterator<ASN1Encodable> it = ASN1Sequence.getInstance(aSN1Encodable).iterator();
            while (it.hasNext()) {
                outputStream.write(it.next().toASN1Primitive().getEncoded(ASN1Encoding.DER));
            }
            return;
        }
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        int i = 1;
        while ((encoded[i] & 255) > 127) {
            i++;
        }
        int i2 = i + 1;
        outputStream.write(encoded, i2, encoded.length - i2);
    }
}
