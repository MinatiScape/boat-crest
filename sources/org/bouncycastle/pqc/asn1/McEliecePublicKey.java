package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes13.dex */
public class McEliecePublicKey extends ASN1Object {
    public final int h;
    public final int i;
    public final GF2Matrix j;

    public McEliecePublicKey(int i, int i2, GF2Matrix gF2Matrix) {
        this.h = i;
        this.i = i2;
        this.j = new GF2Matrix(gF2Matrix);
    }

    public McEliecePublicKey(ASN1Sequence aSN1Sequence) {
        this.h = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.i = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
    }

    public static McEliecePublicKey getInstance(Object obj) {
        if (obj instanceof McEliecePublicKey) {
            return (McEliecePublicKey) obj;
        }
        if (obj != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.j);
    }

    public int getN() {
        return this.h;
    }

    public int getT() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(new DEROctetString(this.j.getEncoded()));
        return new DERSequence(aSN1EncodableVector);
    }
}
