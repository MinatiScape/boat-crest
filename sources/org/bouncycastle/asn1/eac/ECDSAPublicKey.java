package org.bouncycastle.asn1.eac;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class ECDSAPublicKey extends PublicKeyDataObject {
    public ASN1ObjectIdentifier h;
    public BigInteger i;
    public BigInteger j;
    public BigInteger k;
    public byte[] l;
    public BigInteger m;
    public byte[] n;
    public BigInteger o;
    public int p;

    public ECDSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte[] bArr, BigInteger bigInteger4, byte[] bArr2, int i) {
        this.h = aSN1ObjectIdentifier;
        e(bigInteger);
        c(bigInteger2);
        g(bigInteger3);
        a(new DEROctetString(bArr));
        d(bigInteger4);
        f(new DEROctetString(bArr2));
        b(BigInteger.valueOf(i));
    }

    public ECDSAPublicKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) throws IllegalArgumentException {
        this.h = aSN1ObjectIdentifier;
        f(new DEROctetString(bArr));
    }

    public ECDSAPublicKey(ASN1Sequence aSN1Sequence) throws IllegalArgumentException {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1ObjectIdentifier.getInstance(objects.nextElement());
        this.p = 0;
        while (objects.hasMoreElements()) {
            Object nextElement = objects.nextElement();
            if (!(nextElement instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("Unknown Object Identifier!");
            }
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) nextElement;
            switch (aSN1TaggedObject.getTagNo()) {
                case 1:
                    e(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                    break;
                case 2:
                    c(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                    break;
                case 3:
                    g(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                    break;
                case 4:
                    a(ASN1OctetString.getInstance(aSN1TaggedObject, false));
                    break;
                case 5:
                    d(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                    break;
                case 6:
                    f(ASN1OctetString.getInstance(aSN1TaggedObject, false));
                    break;
                case 7:
                    b(UnsignedInteger.getInstance(aSN1TaggedObject).getValue());
                    break;
                default:
                    this.p = 0;
                    throw new IllegalArgumentException("Unknown Object Identifier!");
            }
        }
        int i = this.p;
        if (i != 32 && i != 127) {
            throw new IllegalArgumentException("All options must be either present or absent!");
        }
    }

    public final void a(ASN1OctetString aSN1OctetString) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 8) != 0) {
            throw new IllegalArgumentException("Base Point G already set");
        }
        this.p = i | 8;
        this.l = aSN1OctetString.getOctets();
    }

    public final void b(BigInteger bigInteger) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 64) != 0) {
            throw new IllegalArgumentException("Cofactor F already set");
        }
        this.p = i | 64;
        this.o = bigInteger;
    }

    public final void c(BigInteger bigInteger) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 2) != 0) {
            throw new IllegalArgumentException("First Coef A already set");
        }
        this.p = i | 2;
        this.j = bigInteger;
    }

    public final void d(BigInteger bigInteger) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 16) != 0) {
            throw new IllegalArgumentException("Order of base point R already set");
        }
        this.p = i | 16;
        this.m = bigInteger;
    }

    public final void e(BigInteger bigInteger) {
        int i = this.p;
        if ((i & 1) != 0) {
            throw new IllegalArgumentException("Prime Modulus P already set");
        }
        this.p = i | 1;
        this.i = bigInteger;
    }

    public final void f(ASN1OctetString aSN1OctetString) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 32) != 0) {
            throw new IllegalArgumentException("Public Point Y already set");
        }
        this.p = i | 32;
        this.n = aSN1OctetString.getOctets();
    }

    public final void g(BigInteger bigInteger) throws IllegalArgumentException {
        int i = this.p;
        if ((i & 4) != 0) {
            throw new IllegalArgumentException("Second Coef B already set");
        }
        this.p = i | 4;
        this.k = bigInteger;
    }

    public ASN1EncodableVector getASN1EncodableVector(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        if (!z) {
            aSN1EncodableVector.add(new UnsignedInteger(1, getPrimeModulusP()));
            aSN1EncodableVector.add(new UnsignedInteger(2, getFirstCoefA()));
            aSN1EncodableVector.add(new UnsignedInteger(3, getSecondCoefB()));
            aSN1EncodableVector.add(new DERTaggedObject(false, 4, new DEROctetString(getBasePointG())));
            aSN1EncodableVector.add(new UnsignedInteger(5, getOrderOfBasePointR()));
        }
        aSN1EncodableVector.add(new DERTaggedObject(false, 6, new DEROctetString(getPublicPointY())));
        if (!z) {
            aSN1EncodableVector.add(new UnsignedInteger(7, getCofactorF()));
        }
        return aSN1EncodableVector;
    }

    public byte[] getBasePointG() {
        if ((this.p & 8) != 0) {
            return Arrays.clone(this.l);
        }
        return null;
    }

    public BigInteger getCofactorF() {
        if ((this.p & 64) != 0) {
            return this.o;
        }
        return null;
    }

    public BigInteger getFirstCoefA() {
        if ((this.p & 2) != 0) {
            return this.j;
        }
        return null;
    }

    public BigInteger getOrderOfBasePointR() {
        if ((this.p & 16) != 0) {
            return this.m;
        }
        return null;
    }

    public BigInteger getPrimeModulusP() {
        if ((this.p & 1) != 0) {
            return this.i;
        }
        return null;
    }

    public byte[] getPublicPointY() {
        if ((this.p & 32) != 0) {
            return Arrays.clone(this.n);
        }
        return null;
    }

    public BigInteger getSecondCoefB() {
        if ((this.p & 4) != 0) {
            return this.k;
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.eac.PublicKeyDataObject
    public ASN1ObjectIdentifier getUsage() {
        return this.h;
    }

    public boolean hasParameters() {
        return this.i != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(getASN1EncodableVector(this.h, !hasParameters()));
    }
}
