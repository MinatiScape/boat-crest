package org.bouncycastle.asn1.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1SequenceParser;
import org.bouncycastle.asn1.DERIA5String;
/* loaded from: classes12.dex */
public class TimeStampedDataParser {

    /* renamed from: a  reason: collision with root package name */
    public DERIA5String f14406a;
    public MetaData b;
    public ASN1OctetStringParser c;
    public Evidence d;
    public ASN1SequenceParser e;

    public TimeStampedDataParser(ASN1SequenceParser aSN1SequenceParser) throws IOException {
        this.e = aSN1SequenceParser;
        ASN1Integer.getInstance(aSN1SequenceParser.readObject());
        ASN1Encodable readObject = aSN1SequenceParser.readObject();
        if (readObject instanceof DERIA5String) {
            this.f14406a = DERIA5String.getInstance(readObject);
            readObject = aSN1SequenceParser.readObject();
        }
        if ((readObject instanceof MetaData) || (readObject instanceof ASN1SequenceParser)) {
            this.b = MetaData.getInstance(readObject.toASN1Primitive());
            readObject = aSN1SequenceParser.readObject();
        }
        if (readObject instanceof ASN1OctetStringParser) {
            this.c = (ASN1OctetStringParser) readObject;
        }
    }

    public static TimeStampedDataParser getInstance(Object obj) throws IOException {
        if (obj instanceof ASN1Sequence) {
            return new TimeStampedDataParser(((ASN1Sequence) obj).parser());
        }
        if (obj instanceof ASN1SequenceParser) {
            return new TimeStampedDataParser((ASN1SequenceParser) obj);
        }
        return null;
    }

    public ASN1OctetStringParser getContent() {
        return this.c;
    }

    public DERIA5String getDataUri() {
        return this.f14406a;
    }

    public MetaData getMetaData() {
        return this.b;
    }

    public Evidence getTemporalEvidence() throws IOException {
        if (this.d == null) {
            this.d = Evidence.getInstance(this.e.readObject().toASN1Primitive());
        }
        return this.d;
    }
}
