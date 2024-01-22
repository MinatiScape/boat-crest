package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class DHParameter extends ASN1Object {
    public ASN1Integer h;
    public ASN1Integer i;
    public ASN1Integer j;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.h = new ASN1Integer(bigInteger);
        this.i = new ASN1Integer(bigInteger2);
        this.j = i != 0 ? new ASN1Integer(i) : null;
    }

    public DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = ASN1Integer.getInstance(objects.nextElement());
        this.j = objects.hasMoreElements() ? (ASN1Integer) objects.nextElement() : null;
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.i.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.j;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.h.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        if (getL() != null) {
            aSN1EncodableVector.add(this.j);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
