package org.bouncycastle.asn1.isismtt.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class MonetaryLimit extends ASN1Object {
    public DERPrintableString h;
    public ASN1Integer i;
    public ASN1Integer j;

    public MonetaryLimit(String str, int i, int i2) {
        this.h = new DERPrintableString(str, true);
        this.i = new ASN1Integer(i);
        this.j = new ASN1Integer(i2);
    }

    public MonetaryLimit(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = DERPrintableString.getInstance(objects.nextElement());
        this.i = ASN1Integer.getInstance(objects.nextElement());
        this.j = ASN1Integer.getInstance(objects.nextElement());
    }

    public static MonetaryLimit getInstance(Object obj) {
        if (obj == null || (obj instanceof MonetaryLimit)) {
            return (MonetaryLimit) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new MonetaryLimit(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    public BigInteger getAmount() {
        return this.i.getValue();
    }

    public String getCurrency() {
        return this.h.getString();
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
