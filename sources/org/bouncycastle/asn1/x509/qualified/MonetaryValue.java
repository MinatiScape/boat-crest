package org.bouncycastle.asn1.x509.qualified;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class MonetaryValue extends ASN1Object {
    public Iso4217CurrencyCode h;
    public ASN1Integer i;
    public ASN1Integer j;

    public MonetaryValue(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = Iso4217CurrencyCode.getInstance(objects.nextElement());
        this.i = ASN1Integer.getInstance(objects.nextElement());
        this.j = ASN1Integer.getInstance(objects.nextElement());
    }

    public MonetaryValue(Iso4217CurrencyCode iso4217CurrencyCode, int i, int i2) {
        this.h = iso4217CurrencyCode;
        this.i = new ASN1Integer(i);
        this.j = new ASN1Integer(i2);
    }

    public static MonetaryValue getInstance(Object obj) {
        if (obj instanceof MonetaryValue) {
            return (MonetaryValue) obj;
        }
        if (obj != null) {
            return new MonetaryValue(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getAmount() {
        return this.i.getValue();
    }

    public Iso4217CurrencyCode getCurrency() {
        return this.h;
    }

    public BigInteger getExponent() {
        return this.j.getValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        return new DERSequence(aSN1EncodableVector);
    }
}
