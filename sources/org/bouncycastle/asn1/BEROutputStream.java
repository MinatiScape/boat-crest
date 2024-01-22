package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class BEROutputStream extends DEROutputStream {
    public BEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeObject(Object obj) throws IOException {
        ASN1Primitive aSN1Primitive;
        if (obj == null) {
            writeNull();
            return;
        }
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else if (!(obj instanceof ASN1Encodable)) {
            throw new IOException("object not BEREncodable");
        } else {
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        }
        aSN1Primitive.encode(this);
    }
}
