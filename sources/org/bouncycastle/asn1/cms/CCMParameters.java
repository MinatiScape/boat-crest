package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CCMParameters extends ASN1Object {
    public byte[] h;
    public int i;

    public CCMParameters(ASN1Sequence aSN1Sequence) {
        this.h = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets();
        this.i = aSN1Sequence.size() == 2 ? ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue().intValue() : 12;
    }

    public CCMParameters(byte[] bArr, int i) {
        this.h = Arrays.clone(bArr);
        this.i = i;
    }

    public static CCMParameters getInstance(Object obj) {
        if (obj instanceof CCMParameters) {
            return (CCMParameters) obj;
        }
        if (obj != null) {
            return new CCMParameters(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getIcvLen() {
        return this.i;
    }

    public byte[] getNonce() {
        return Arrays.clone(this.h);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.h));
        int i = this.i;
        if (i != 12) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
