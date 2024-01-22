package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class BERApplicationSpecificParser implements ASN1ApplicationSpecificParser {
    public final int h;
    public final ASN1StreamParser i;

    public BERApplicationSpecificParser(int i, ASN1StreamParser aSN1StreamParser) {
        this.h = i;
        this.i = aSN1StreamParser;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return new BERApplicationSpecific(this.h, this.i.d());
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecificParser
    public ASN1Encodable readObject() throws IOException {
        return this.i.readObject();
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
