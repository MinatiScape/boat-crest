package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
/* loaded from: classes12.dex */
public class EnvelopedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1SequenceParser f14404a;
    public ASN1Integer b;
    public ASN1Encodable c;
    public boolean d;

    public EnvelopedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14404a = aSN1SequenceParser;
        this.b = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
    }

    public EncryptedContentInfoParser getEncryptedContentInfo() throws IOException {
        if (this.c == null) {
            this.c = this.f14404a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable != null) {
            this.c = null;
            return new EncryptedContentInfoParser((ASN1SequenceParser) aSN1Encodable);
        }
        return null;
    }

    public OriginatorInfo getOriginatorInfo() throws IOException {
        this.d = true;
        if (this.c == null) {
            this.c = this.f14404a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if ((aSN1Encodable instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser) aSN1Encodable).getTagNo() == 0) {
            this.c = null;
            return OriginatorInfo.getInstance(((ASN1SequenceParser) ((ASN1TaggedObjectParser) this.c).getObjectParser(16, false)).toASN1Primitive());
        }
        return null;
    }

    public ASN1SetParser getRecipientInfos() throws IOException {
        if (!this.d) {
            getOriginatorInfo();
        }
        if (this.c == null) {
            this.c = this.f14404a.readObject();
        }
        ASN1SetParser aSN1SetParser = (ASN1SetParser) this.c;
        this.c = null;
        return aSN1SetParser;
    }

    public ASN1SetParser getUnprotectedAttrs() throws IOException {
        if (this.c == null) {
            this.c = this.f14404a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable != null) {
            this.c = null;
            return (ASN1SetParser) ((ASN1TaggedObjectParser) aSN1Encodable).getObjectParser(17, false);
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.b;
    }
}
