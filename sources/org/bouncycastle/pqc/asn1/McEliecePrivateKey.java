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
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
/* loaded from: classes13.dex */
public class McEliecePrivateKey extends ASN1Object {
    public int h;
    public int i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public byte[] n;

    public McEliecePrivateKey(int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix) {
        this.h = i;
        this.i = i2;
        this.j = gF2mField.getEncoded();
        this.k = polynomialGF2mSmallM.getEncoded();
        this.l = gF2Matrix.getEncoded();
        this.m = permutation.getEncoded();
        this.n = permutation2.getEncoded();
    }

    public McEliecePrivateKey(ASN1Sequence aSN1Sequence) {
        this.h = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.i = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = ((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets();
        this.k = ((ASN1OctetString) aSN1Sequence.getObjectAt(3)).getOctets();
        this.m = ((ASN1OctetString) aSN1Sequence.getObjectAt(4)).getOctets();
        this.n = ((ASN1OctetString) aSN1Sequence.getObjectAt(5)).getOctets();
        this.l = ((ASN1OctetString) aSN1Sequence.getObjectAt(6)).getOctets();
    }

    public static McEliecePrivateKey getInstance(Object obj) {
        if (obj instanceof McEliecePrivateKey) {
            return (McEliecePrivateKey) obj;
        }
        if (obj != null) {
            return new McEliecePrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GF2mField getField() {
        return new GF2mField(this.j);
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return new PolynomialGF2mSmallM(getField(), this.k);
    }

    public int getK() {
        return this.i;
    }

    public int getN() {
        return this.h;
    }

    public Permutation getP1() {
        return new Permutation(this.m);
    }

    public Permutation getP2() {
        return new Permutation(this.n);
    }

    public GF2Matrix getSInv() {
        return new GF2Matrix(this.l);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(new DEROctetString(this.j));
        aSN1EncodableVector.add(new DEROctetString(this.k));
        aSN1EncodableVector.add(new DEROctetString(this.m));
        aSN1EncodableVector.add(new DEROctetString(this.n));
        aSN1EncodableVector.add(new DEROctetString(this.l));
        return new DERSequence(aSN1EncodableVector);
    }
}
