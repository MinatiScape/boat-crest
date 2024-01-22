package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class CompressedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1Integer f14401a;
    public AlgorithmIdentifier b;
    public ContentInfoParser c;

    public CompressedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14401a = (ASN1Integer) aSN1SequenceParser.readObject();
        this.b = AlgorithmIdentifier.getInstance(aSN1SequenceParser.readObject().toASN1Primitive());
        this.c = new ContentInfoParser((ASN1SequenceParser) aSN1SequenceParser.readObject());
    }

    public AlgorithmIdentifier getCompressionAlgorithmIdentifier() {
        return this.b;
    }

    public ContentInfoParser getEncapContentInfo() {
        return this.c;
    }

    public ASN1Integer getVersion() {
        return this.f14401a;
    }
}
