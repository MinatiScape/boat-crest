package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class ProtectedPart extends ASN1Object {
    public PKIHeader h;
    public PKIBody i;

    public ProtectedPart(ASN1Sequence aSN1Sequence) {
        this.h = PKIHeader.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = PKIBody.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public ProtectedPart(PKIHeader pKIHeader, PKIBody pKIBody) {
        this.h = pKIHeader;
        this.i = pKIBody;
    }

    public static ProtectedPart getInstance(Object obj) {
        if (obj instanceof ProtectedPart) {
            return (ProtectedPart) obj;
        }
        if (obj != null) {
            return new ProtectedPart(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public PKIBody getBody() {
        return this.i;
    }

    public PKIHeader getHeader() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        return new DERSequence(aSN1EncodableVector);
    }
}
