package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PBEParameter extends ASN1Object {
    public ASN1Integer h;
    public ASN1OctetString i;

    public PBEParameter(ASN1Sequence aSN1Sequence) {
        this.i = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(1);
    }

    public PBEParameter(byte[] bArr, int i) {
        if (bArr.length != 8) {
            throw new IllegalArgumentException("salt length must be 8");
        }
        this.i = new DEROctetString(bArr);
        this.h = new ASN1Integer(i);
    }

    public static PBEParameter getInstance(Object obj) {
        if (obj instanceof PBEParameter) {
            return (PBEParameter) obj;
        }
        if (obj != null) {
            return new PBEParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getIterationCount() {
        return this.h.getValue();
    }

    public byte[] getSalt() {
        return this.i.getOctets();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.h);
        return new DERSequence(aSN1EncodableVector);
    }
}
