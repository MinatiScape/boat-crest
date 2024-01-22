package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class AuthenticatedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1SequenceParser f14400a;
    public ASN1Integer b;
    public ASN1Encodable c;
    public boolean d;

    public AuthenticatedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14400a = aSN1SequenceParser;
        this.b = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
    }

    public ASN1SetParser getAuthAttrs() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable instanceof ASN1TaggedObjectParser) {
            this.c = null;
            return (ASN1SetParser) ((ASN1TaggedObjectParser) aSN1Encodable).getObjectParser(17, false);
        }
        return null;
    }

    public AlgorithmIdentifier getDigestAlgorithm() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable instanceof ASN1TaggedObjectParser) {
            AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance((ASN1TaggedObject) aSN1Encodable.toASN1Primitive(), false);
            this.c = null;
            return algorithmIdentifier;
        }
        return null;
    }

    public ContentInfoParser getEnapsulatedContentInfo() throws IOException {
        return getEncapsulatedContentInfo();
    }

    public ContentInfoParser getEncapsulatedContentInfo() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable != null) {
            this.c = null;
            return new ContentInfoParser((ASN1SequenceParser) aSN1Encodable);
        }
        return null;
    }

    public ASN1OctetString getMac() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        this.c = null;
        return ASN1OctetString.getInstance(aSN1Encodable.toASN1Primitive());
    }

    public AlgorithmIdentifier getMacAlgorithm() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
        }
        ASN1Encodable aSN1Encodable = this.c;
        if (aSN1Encodable != null) {
            this.c = null;
            return AlgorithmIdentifier.getInstance(((ASN1SequenceParser) aSN1Encodable).toASN1Primitive());
        }
        return null;
    }

    public OriginatorInfo getOriginatorInfo() throws IOException {
        this.d = true;
        if (this.c == null) {
            this.c = this.f14400a.readObject();
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
            this.c = this.f14400a.readObject();
        }
        ASN1SetParser aSN1SetParser = (ASN1SetParser) this.c;
        this.c = null;
        return aSN1SetParser;
    }

    public ASN1SetParser getUnauthAttrs() throws IOException {
        if (this.c == null) {
            this.c = this.f14400a.readObject();
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
