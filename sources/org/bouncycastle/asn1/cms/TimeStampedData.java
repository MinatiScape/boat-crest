package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERIA5String;
/* loaded from: classes12.dex */
public class TimeStampedData extends ASN1Object {
    public ASN1Integer h;
    public DERIA5String i;
    public MetaData j;
    public ASN1OctetString k;
    public Evidence l;

    public TimeStampedData(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        int i = 1;
        if (aSN1Sequence.getObjectAt(1) instanceof DERIA5String) {
            this.i = DERIA5String.getInstance(aSN1Sequence.getObjectAt(1));
            i = 2;
        }
        if ((aSN1Sequence.getObjectAt(i) instanceof MetaData) || (aSN1Sequence.getObjectAt(i) instanceof ASN1Sequence)) {
            this.j = MetaData.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
        if (aSN1Sequence.getObjectAt(i) instanceof ASN1OctetString) {
            this.k = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
        this.l = Evidence.getInstance(aSN1Sequence.getObjectAt(i));
    }

    public TimeStampedData(DERIA5String dERIA5String, MetaData metaData, ASN1OctetString aSN1OctetString, Evidence evidence) {
        this.h = new ASN1Integer(1L);
        this.i = dERIA5String;
        this.j = metaData;
        this.k = aSN1OctetString;
        this.l = evidence;
    }

    public static TimeStampedData getInstance(Object obj) {
        return (obj == null || (obj instanceof TimeStampedData)) ? (TimeStampedData) obj : new TimeStampedData(ASN1Sequence.getInstance(obj));
    }

    public ASN1OctetString getContent() {
        return this.k;
    }

    public DERIA5String getDataUri() {
        return this.i;
    }

    public MetaData getMetaData() {
        return this.j;
    }

    public Evidence getTemporalEvidence() {
        return this.l;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        DERIA5String dERIA5String = this.i;
        if (dERIA5String != null) {
            aSN1EncodableVector.add(dERIA5String);
        }
        MetaData metaData = this.j;
        if (metaData != null) {
            aSN1EncodableVector.add(metaData);
        }
        ASN1OctetString aSN1OctetString = this.k;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(aSN1OctetString);
        }
        aSN1EncodableVector.add(this.l);
        return new BERSequence(aSN1EncodableVector);
    }
}
