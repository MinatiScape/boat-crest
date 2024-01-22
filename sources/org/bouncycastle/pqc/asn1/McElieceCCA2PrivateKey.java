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
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
/* loaded from: classes13.dex */
public class McElieceCCA2PrivateKey extends ASN1Object {
    public int h;
    public int i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public AlgorithmIdentifier m;

    public McElieceCCA2PrivateKey(int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, AlgorithmIdentifier algorithmIdentifier) {
        this.h = i;
        this.i = i2;
        this.j = gF2mField.getEncoded();
        this.k = polynomialGF2mSmallM.getEncoded();
        this.l = permutation.getEncoded();
        this.m = algorithmIdentifier;
    }

    public McElieceCCA2PrivateKey(ASN1Sequence aSN1Sequence) {
        this.h = ((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue().intValue();
        this.i = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue().intValue();
        this.j = ((ASN1OctetString) aSN1Sequence.getObjectAt(2)).getOctets();
        this.k = ((ASN1OctetString) aSN1Sequence.getObjectAt(3)).getOctets();
        this.l = ((ASN1OctetString) aSN1Sequence.getObjectAt(4)).getOctets();
        this.m = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(5));
    }

    public static McElieceCCA2PrivateKey getInstance(Object obj) {
        if (obj instanceof McElieceCCA2PrivateKey) {
            return (McElieceCCA2PrivateKey) obj;
        }
        if (obj != null) {
            return new McElieceCCA2PrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getDigest() {
        return this.m;
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

    public Permutation getP() {
        return new Permutation(this.l);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(new ASN1Integer(this.i));
        aSN1EncodableVector.add(new DEROctetString(this.j));
        aSN1EncodableVector.add(new DEROctetString(this.k));
        aSN1EncodableVector.add(new DEROctetString(this.l));
        aSN1EncodableVector.add(this.m);
        return new DERSequence(aSN1EncodableVector);
    }
}
