package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class ECGOST3410ParamSetParameters extends ASN1Object {
    public ASN1Integer h;
    public ASN1Integer i;
    public ASN1Integer j;
    public ASN1Integer k;
    public ASN1Integer l;
    public ASN1Integer m;

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i, BigInteger bigInteger5) {
        this.j = new ASN1Integer(bigInteger);
        this.k = new ASN1Integer(bigInteger2);
        this.h = new ASN1Integer(bigInteger3);
        this.i = new ASN1Integer(bigInteger4);
        this.l = new ASN1Integer(i);
        this.m = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.j = (ASN1Integer) objects.nextElement();
        this.k = (ASN1Integer) objects.nextElement();
        this.h = (ASN1Integer) objects.nextElement();
        this.i = (ASN1Integer) objects.nextElement();
        this.l = (ASN1Integer) objects.nextElement();
        this.m = (ASN1Integer) objects.nextElement();
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.j.getPositiveValue();
    }

    public BigInteger getP() {
        return this.h.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.i.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.l);
        aSN1EncodableVector.add(this.m);
        return new DERSequence(aSN1EncodableVector);
    }
}
