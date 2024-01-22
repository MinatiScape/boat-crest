package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1SetParser;
import org.bouncycastle.asn1.ASN1TaggedObjectParser;
/* loaded from: classes12.dex */
public class SignedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public ASN1SequenceParser f14405a;
    public ASN1Integer b;
    public Object c;
    public boolean d;
    public boolean e;

    public SignedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.f14405a = aSN1SequenceParser;
        this.b = (ASN1Integer) aSN1SequenceParser.readObject();
    }

    public static SignedDataParser getInstance(Object obj) throws IOException {
        if (obj instanceof ASN1Sequence) {
            return new SignedDataParser(((ASN1Sequence) obj).parser());
        }
        if (obj instanceof ASN1SequenceParser) {
            return new SignedDataParser((ASN1SequenceParser) obj);
        }
        throw new IOException("unknown object encountered: " + obj.getClass().getName());
    }

    public ASN1SetParser getCertificates() throws IOException {
        this.d = true;
        ASN1Encodable readObject = this.f14405a.readObject();
        this.c = readObject;
        if ((readObject instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser) readObject).getTagNo() == 0) {
            ASN1SetParser aSN1SetParser = (ASN1SetParser) ((ASN1TaggedObjectParser) this.c).getObjectParser(17, false);
            this.c = null;
            return aSN1SetParser;
        }
        return null;
    }

    public ASN1SetParser getCrls() throws IOException {
        if (this.d) {
            this.e = true;
            if (this.c == null) {
                this.c = this.f14405a.readObject();
            }
            Object obj = this.c;
            if ((obj instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser) obj).getTagNo() == 1) {
                ASN1SetParser aSN1SetParser = (ASN1SetParser) ((ASN1TaggedObjectParser) this.c).getObjectParser(17, false);
                this.c = null;
                return aSN1SetParser;
            }
            return null;
        }
        throw new IOException("getCerts() has not been called.");
    }

    public ASN1SetParser getDigestAlgorithms() throws IOException {
        ASN1Encodable readObject = this.f14405a.readObject();
        return readObject instanceof ASN1Set ? ((ASN1Set) readObject).parser() : (ASN1SetParser) readObject;
    }

    public ContentInfoParser getEncapContentInfo() throws IOException {
        return new ContentInfoParser((ASN1SequenceParser) this.f14405a.readObject());
    }

    public ASN1SetParser getSignerInfos() throws IOException {
        if (this.d && this.e) {
            if (this.c == null) {
                this.c = this.f14405a.readObject();
            }
            return (ASN1SetParser) this.c;
        }
        throw new IOException("getCerts() and/or getCrls() has not been called.");
    }

    public ASN1Integer getVersion() {
        return this.b;
    }
}
