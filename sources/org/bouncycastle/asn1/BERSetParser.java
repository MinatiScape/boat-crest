package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class BERSetParser implements ASN1SetParser {
    public ASN1StreamParser h;

    public BERSetParser(ASN1StreamParser aSN1StreamParser) {
        this.h = aSN1StreamParser;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return new BERSet(this.h.d());
    }

    @Override // org.bouncycastle.asn1.ASN1SetParser
    public ASN1Encodable readObject() throws IOException {
        return this.h.readObject();
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
