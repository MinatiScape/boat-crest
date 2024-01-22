package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes13.dex */
public class McElieceCCA2PublicKey extends ASN1Object {
    public final int h;
    public final int i;
    public final GF2Matrix j;
    public final AlgorithmIdentifier k;

    public McElieceCCA2PublicKey(int i, int i2, GF2Matrix gF2Matrix, AlgorithmIdentifier algorithmIdentifier) {
        this.h = i;
        this.i = i2;
        this.j = new GF2Matrix(gF2Matrix.getEncoded());
        this.k = algorithmIdentifier;
    }

    public McElieceCCA2PublicKey(ASN1Sequence aSN1Sequence) {
        this.h = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.i = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = new GF2Matrix(((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets());
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(3));
    }

    public static McElieceCCA2PublicKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getDigest() {
        return this.k;
    }

    public GF2Matrix getG() {
        return this.j;
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
        aSN1EncodableVector.add(this.k);
        return new DERSequence(aSN1EncodableVector);
    }
}
