package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class EncryptedContentInfoParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1ObjectIdentifier f14403a;
    public AlgorithmIdentifier b;
    public ASN1TaggedObjectParser c;

    public EncryptedContentInfoParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14403a = (ASN1ObjectIdentifier) aSN1SequenceParser.readObject();
        this.b = AlgorithmIdentifier.getInstance(aSN1SequenceParser.readObject().toASN1Primitive());
        this.c = (ASN1TaggedObjectParser) aSN1SequenceParser.readObject();
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this.b;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.f14403a;
    }

    public ASN1Encodable getEncryptedContent(int i) throws IOException {
        return this.c.getObjectParser(i, false);
    }
}
