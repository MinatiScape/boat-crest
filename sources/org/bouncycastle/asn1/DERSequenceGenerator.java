package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class DERSequenceGenerator extends DERGenerator {
    public final ByteArrayOutputStream d;

    public DERSequenceGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        this.d = new ByteArrayOutputStream();
    }

    public DERSequenceGenerator(OutputStream outputStream, int i, boolean z) throws IOException {
        super(outputStream, i, z);
        this.d = new ByteArrayOutputStream();
    }

    public void addObject(ASN1Encodable aSN1Encodable) throws IOException {
        aSN1Encodable.toASN1Primitive().encode(new DEROutputStream(this.d));
    }

    public void close() throws IOException {
        a(48, this.d.toByteArray());
    }

    @Override // org.bouncycastle.asn1.ASN1Generator
    public OutputStream getRawOutputStream() {
        return this.d;
    }
}
