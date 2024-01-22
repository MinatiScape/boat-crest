package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
/* loaded from: classes12.dex */
public class AuthEnvelopedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1SequenceParser f14399a;
    public ASN1Integer b;
    public ASN1Encodable c;
    public boolean d;
    public EncryptedContentInfoParser e;

    public AuthEnvelopedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14399a = aSN1SequenceParser;
        ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
        this.b = aSN1Integer;
        if (aSN1Integer.getValue().intValue() != 0) {
            throw new ASN1ParsingException("AuthEnvelopedData version number must be 0");
        }
    }

    public ASN1SetParser getAuthAttrs() throws IOException {
        if (this.c == null) {
            this.c = this.f14399a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable instanceof ASN1TaggedObjectParser) {
            this.c = null;
            return (ASN1SetParser) ((ASN1TaggedObjectParser) aSN1Encodable).getObjectParser(17, false);
        } else if (this.e.getContentType().equals(CMSObjectIdentifiers.data)) {
            return null;
        } else {
            throw new ASN1ParsingException("authAttrs must be present with non-data content");
        }
    }

    public EncryptedContentInfoParser getAuthEncryptedContentInfo() throws IOException {
        if (this.c == null) {
            this.c = this.f14399a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable != null) {
            this.c = null;
            EncryptedContentInfoParser encryptedContentInfoParser = new EncryptedContentInfoParser((ASN1SequenceParser) aSN1Encodable);
            this.e = encryptedContentInfoParser;
            return encryptedContentInfoParser;
        }
        return null;
    }

    public ASN1OctetString getMac() throws IOException {
        if (this.c == null) {
            this.c = this.f14399a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        this.c = null;
        return ASN1OctetString.getInstance(aSN1Encodable.toASN1Primitive());
    }

    public OriginatorInfo getOriginatorInfo() throws IOException {
        this.d = true;
        if (this.c == null) {
            this.c = this.f14399a.readObject();
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
            this.c = this.f14399a.readObject();
        }
        ASN1SetParser aSN1SetParser = (ASN1SetParser) this.c;
        this.c = null;
        return aSN1SetParser;
    }

    public ASN1SetParser getUnauthAttrs() throws IOException {
        if (this.c == null) {
            this.c = this.f14399a.readObject();
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
