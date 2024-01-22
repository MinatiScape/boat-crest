package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class CertReqMessages extends ASN1Object {
    public ASN1Sequence h;

    public CertReqMessages(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
    }

    public CertReqMessages(CertReqMsg certReqMsg) {
        this.h = new DERSequence(certReqMsg);
    }

    public CertReqMessages(CertReqMsg[] certReqMsgArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (CertReqMsg certReqMsg : certReqMsgArr) {
            aSN1EncodableVector.add(certReqMsg);
        }
        this.h = new DERSequence(aSN1EncodableVector);
    }

    public static CertReqMessages getInstance(Object obj) {
        if (obj instanceof CertReqMessages) {
            return (CertReqMessages) obj;
        }
        if (obj != null) {
            return new CertReqMessages(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public CertReqMsg[] toCertReqMsgArray() {
        int size = this.h.size();
        CertReqMsg[] certReqMsgArr = new CertReqMsg[size];
        for (int i = 0; i != size; i++) {
            certReqMsgArr[i] = CertReqMsg.getInstance(this.h.getObjectAt(i));
        }
        return certReqMsgArr;
    }
}
