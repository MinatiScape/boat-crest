package org.bouncycastle.asn1.tsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cms.ContentInfo;
/* loaded from: classes12.dex */
public class TimeStampResp extends ASN1Object {
    public PKIStatusInfo h;
    public ContentInfo i;

    public TimeStampResp(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = PKIStatusInfo.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.i = ContentInfo.getInstance(objects.nextElement());
        }
    }

    public TimeStampResp(PKIStatusInfo pKIStatusInfo, ContentInfo contentInfo) {
        this.h = pKIStatusInfo;
        this.i = contentInfo;
    }

    public static TimeStampResp getInstance(Object obj) {
        if (obj instanceof TimeStampResp) {
            return (TimeStampResp) obj;
        }
        if (obj != null) {
            return new TimeStampResp(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PKIStatusInfo getStatus() {
        return this.h;
    }

    public ContentInfo getTimeStampToken() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        ContentInfo contentInfo = this.i;
        if (contentInfo != null) {
            aSN1EncodableVector.add(contentInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
