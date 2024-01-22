package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
/* loaded from: classes12.dex */
public class CertConfirmContent extends ASN1Object {
    public ASN1Sequence h;

    public CertConfirmContent(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
    }

    public static CertConfirmContent getInstance(Object obj) {
        if (obj instanceof CertConfirmContent) {
            return (CertConfirmContent) obj;
        }
        if (obj != null) {
            return new CertConfirmContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }

    public CertStatus[] toCertStatusArray() {
        int size = this.h.size();
        CertStatus[] certStatusArr = new CertStatus[size];
        for (int i = 0; i != size; i++) {
            certStatusArr[i] = CertStatus.getInstance(this.h.getObjectAt(i));
        }
        return certStatusArr;
    }
}
