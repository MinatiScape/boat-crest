package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class RevReqContent extends ASN1Object {
    public ASN1Sequence h;

    public RevReqContent(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
    }

    public RevReqContent(RevDetails revDetails) {
        this.h = new DERSequence(revDetails);
    }

    public RevReqContent(RevDetails[] revDetailsArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != revDetailsArr.length; i++) {
            aSN1EncodableVector.add(revDetailsArr[i]);
        }
        this.h = new DERSequence(aSN1EncodableVector);
    }

    public static RevReqContent getInstance(Object obj) {
        if (obj instanceof RevReqContent) {
            return (RevReqContent) obj;
        }
        if (obj != null) {
            return new RevReqContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public RevDetails[] toRevDetailsArray() {
        int size = this.h.size();
        RevDetails[] revDetailsArr = new RevDetails[size];
        for (int i = 0; i != size; i++) {
            revDetailsArr[i] = RevDetails.getInstance(this.h.getObjectAt(i));
        }
        return revDetailsArr;
    }
}
