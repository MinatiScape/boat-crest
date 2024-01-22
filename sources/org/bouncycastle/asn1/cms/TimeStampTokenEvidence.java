package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class TimeStampTokenEvidence extends ASN1Object {
    public TimeStampAndCRL[] h;

    public TimeStampTokenEvidence(ASN1Sequence aSN1Sequence) {
        this.h = new TimeStampAndCRL[aSN1Sequence.size()];
        Enumeration objects = aSN1Sequence.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            this.h[i] = TimeStampAndCRL.getInstance(objects.nextElement());
            i++;
        }
    }

    public TimeStampTokenEvidence(TimeStampAndCRL timeStampAndCRL) {
        this.h = r0;
        TimeStampAndCRL[] timeStampAndCRLArr = {timeStampAndCRL};
    }

    public TimeStampTokenEvidence(TimeStampAndCRL[] timeStampAndCRLArr) {
        this.h = timeStampAndCRLArr;
    }

    public static TimeStampTokenEvidence getInstance(Object obj) {
        if (obj instanceof TimeStampTokenEvidence) {
            return (TimeStampTokenEvidence) obj;
        }
        if (obj != null) {
            return new TimeStampTokenEvidence(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TimeStampTokenEvidence getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.h;
            if (i == timeStampAndCRLArr.length) {
                return new DERSequence(aSN1EncodableVector);
            }
            aSN1EncodableVector.add(timeStampAndCRLArr[i]);
            i++;
        }
    }

    public TimeStampAndCRL[] toTimeStampAndCRLArray() {
        return this.h;
    }
}
