package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
/* loaded from: classes12.dex */
public class ContentInfoParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f14402a;
    public ASN1TaggedObjectParser b;

    public ContentInfoParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14402a = (ASN1ObjectIdentifier) aSN1SequenceParser.readObject();
        this.b = (ASN1TaggedObjectParser) aSN1SequenceParser.readObject();
    }

    public ASN1Encodable getContent(int i) throws IOException {
        ASN1TaggedObjectParser aSN1TaggedObjectParser = this.b;
        if (aSN1TaggedObjectParser != null) {
            return aSN1TaggedObjectParser.getObjectParser(i, true);
        }
        return null;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.f14402a;
    }
}
