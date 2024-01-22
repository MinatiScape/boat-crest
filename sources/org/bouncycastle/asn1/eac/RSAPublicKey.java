package org.bouncycastle.asn1.eac;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class RSAPublicKey extends PublicKeyDataObject {
    public static int l = 1;
    public static int m = 2;
    public ASN1ObjectIdentifier h;
    public BigInteger i;
    public BigInteger j;
    public int k = 0;

    public RSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger, BigInteger bigInteger2) {
        this.h = aSN1ObjectIdentifier;
        this.i = bigInteger;
        this.j = bigInteger2;
    }

    public RSAPublicKey(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1ObjectIdentifier.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            UnsignedInteger unsignedInteger = UnsignedInteger.getInstance(objects.nextElement());
            int tagNo = unsignedInteger.getTagNo();
            if (tagNo == 1) {
                b(unsignedInteger);
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("Unknown DERTaggedObject :" + unsignedInteger.getTagNo() + "-> not an Iso7816RSAPublicKeyStructure");
            } else {
                a(unsignedInteger);
            }
        }
        if (this.k != 3) {
            throw new IllegalArgumentException("missing argument -> not an Iso7816RSAPublicKeyStructure");
        }
    }

    public final void a(UnsignedInteger unsignedInteger) {
        int i = this.k;
        int i2 = m;
        if ((i & i2) != 0) {
            throw new IllegalArgumentException("Exponent already set");
        }
        this.k = i | i2;
        this.j = unsignedInteger.getValue();
    }

    public final void b(UnsignedInteger unsignedInteger) {
        int i = this.k;
        int i2 = l;
        if ((i & i2) != 0) {
            throw new IllegalArgumentException("Modulus already set");
        }
        this.k = i | i2;
        this.i = unsignedInteger.getValue();
    }

    public BigInteger getModulus() {
        return this.i;
    }

    public BigInteger getPublicExponent() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.eac.PublicKeyDataObject
    public ASN1ObjectIdentifier getUsage() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(new UnsignedInteger(1, getModulus()));
        aSN1EncodableVector.add(new UnsignedInteger(2, getPublicExponent()));
        return new DERSequence(aSN1EncodableVector);
    }
}
