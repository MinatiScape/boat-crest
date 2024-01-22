package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public class BERTaggedObjectParser implements ASN1TaggedObjectParser {
    public boolean h;
    public int i;
    public ASN1StreamParser j;

    public BERTaggedObjectParser(boolean z, int i, ASN1StreamParser aSN1StreamParser) {
        this.h = z;
        this.i = i;
        this.j = aSN1StreamParser;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() throws IOException {
        return this.j.c(this.h, this.i);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i, boolean z) throws IOException {
        if (z) {
            if (this.h) {
                return this.j.readObject();
            }
            throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
        }
        return this.j.a(this.h, i);
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.i;
    }

    public boolean isConstructed() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }
}
