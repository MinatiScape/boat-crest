package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PKIMessages extends ASN1Object {
    public ASN1Sequence h;

    public PKIMessages(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
    }

    public PKIMessages(PKIMessage pKIMessage) {
        this.h = new DERSequence(pKIMessage);
    }

    public PKIMessages(PKIMessage[] pKIMessageArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (PKIMessage pKIMessage : pKIMessageArr) {
            aSN1EncodableVector.add(pKIMessage);
        }
        this.h = new DERSequence(aSN1EncodableVector);
    }

    public static PKIMessages getInstance(Object obj) {
        if (obj instanceof PKIMessages) {
            return (PKIMessages) obj;
        }
        if (obj != null) {
            return new PKIMessages(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public PKIMessage[] toPKIMessageArray() {
        int size = this.h.size();
        PKIMessage[] pKIMessageArr = new PKIMessage[size];
        for (int i = 0; i != size; i++) {
            pKIMessageArr[i] = PKIMessage.getInstance(this.h.getObjectAt(i));
        }
        return pKIMessageArr;
    }
}
