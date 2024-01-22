package org.bouncycastle.asn1.pkcs;

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
public class RSAPrivateKey extends ASN1Object {
    public BigInteger h;
    public BigInteger i;
    public BigInteger j;
    public BigInteger k;
    public BigInteger l;
    public BigInteger m;
    public BigInteger n;
    public BigInteger o;
    public BigInteger p;
    public ASN1Sequence q;

    public RSAPrivateKey(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        this.q = null;
        this.h = BigInteger.valueOf(0L);
        this.i = bigInteger;
        this.j = bigInteger2;
        this.k = bigInteger3;
        this.l = bigInteger4;
        this.m = bigInteger5;
        this.n = bigInteger6;
        this.o = bigInteger7;
        this.p = bigInteger8;
    }

    public RSAPrivateKey(ASN1Sequence aSN1Sequence) {
        this.q = null;
        Enumeration objects = aSN1Sequence.getObjects();
        BigInteger value = ((ASN1Integer) objects.nextElement()).getValue();
        if (value.intValue() != 0 && value.intValue() != 1) {
            throw new IllegalArgumentException("wrong version for RSA private key");
        }
        this.h = value;
        this.i = ((ASN1Integer) objects.nextElement()).getValue();
        this.j = ((ASN1Integer) objects.nextElement()).getValue();
        this.k = ((ASN1Integer) objects.nextElement()).getValue();
        this.l = ((ASN1Integer) objects.nextElement()).getValue();
        this.m = ((ASN1Integer) objects.nextElement()).getValue();
        this.n = ((ASN1Integer) objects.nextElement()).getValue();
        this.o = ((ASN1Integer) objects.nextElement()).getValue();
        this.p = ((ASN1Integer) objects.nextElement()).getValue();
        if (objects.hasMoreElements()) {
            this.q = (ASN1Sequence) objects.nextElement();
        }
    }

    public static RSAPrivateKey getInstance(Object obj) {
        if (obj instanceof RSAPrivateKey) {
            return (RSAPrivateKey) obj;
        }
        if (obj != null) {
            return new RSAPrivateKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static RSAPrivateKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getCoefficient() {
        return this.p;
    }

    public BigInteger getExponent1() {
        return this.n;
    }

    public BigInteger getExponent2() {
        return this.o;
    }

    public BigInteger getModulus() {
        return this.i;
    }

    public BigInteger getPrime1() {
        return this.l;
    }

    public BigInteger getPrime2() {
        return this.m;
    }

    public BigInteger getPrivateExponent() {
        return this.k;
    }

    public BigInteger getPublicExponent() {
        return this.j;
    }

    public BigInteger getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.h));
        aSN1EncodableVector.add(new ASN1Integer(getModulus()));
        aSN1EncodableVector.add(new ASN1Integer(getPublicExponent()));
        aSN1EncodableVector.add(new ASN1Integer(getPrivateExponent()));
        aSN1EncodableVector.add(new ASN1Integer(getPrime1()));
        aSN1EncodableVector.add(new ASN1Integer(getPrime2()));
        aSN1EncodableVector.add(new ASN1Integer(getExponent1()));
        aSN1EncodableVector.add(new ASN1Integer(getExponent2()));
        aSN1EncodableVector.add(new ASN1Integer(getCoefficient()));
        ASN1Sequence aSN1Sequence = this.q;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
