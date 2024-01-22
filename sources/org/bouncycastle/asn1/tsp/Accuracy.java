package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class Accuracy extends ASN1Object {
    public static final int MAX_MICROS = 999;
    public static final int MAX_MILLIS = 999;
    public static final int MIN_MICROS = 1;
    public static final int MIN_MILLIS = 1;
    public ASN1Integer h;
    public ASN1Integer i;
    public ASN1Integer j;

    public Accuracy() {
    }

    public Accuracy(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, ASN1Integer aSN1Integer3) {
        this.h = aSN1Integer;
        if (aSN1Integer2 != null && (aSN1Integer2.getValue().intValue() < 1 || aSN1Integer2.getValue().intValue() > 999)) {
            throw new IllegalArgumentException("Invalid millis field : not in (1..999)");
        }
        this.i = aSN1Integer2;
        if (aSN1Integer3 != null && (aSN1Integer3.getValue().intValue() < 1 || aSN1Integer3.getValue().intValue() > 999)) {
            throw new IllegalArgumentException("Invalid micros field : not in (1..999)");
        }
        this.j = aSN1Integer3;
    }

    public Accuracy(ASN1Sequence aSN1Sequence) {
        this.h = null;
        this.i = null;
        this.j = null;
        for (int i = 0; i < aSN1Sequence.size(); i++) {
            if (aSN1Sequence.getObjectAt(i) instanceof ASN1Integer) {
                this.h = (ASN1Integer) aSN1Sequence.getObjectAt(i);
            } else if (aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.i = aSN1Integer;
                    if (aSN1Integer.getValue().intValue() < 1 || this.i.getValue().intValue() > 999) {
                        throw new IllegalArgumentException("Invalid millis field : not in (1..999).");
                    }
                } else if (tagNo != 1) {
                    throw new IllegalArgumentException("Invalig tag number");
                } else {
                    ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.j = aSN1Integer2;
                    if (aSN1Integer2.getValue().intValue() < 1 || this.j.getValue().intValue() > 999) {
                        throw new IllegalArgumentException("Invalid micros field : not in (1..999).");
                    }
                }
            } else {
                continue;
            }
        }
    }

    public static Accuracy getInstance(Object obj) {
        if (obj instanceof Accuracy) {
            return (Accuracy) obj;
        }
        if (obj != null) {
            return new Accuracy(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getMicros() {
        return this.j;
    }

    public ASN1Integer getMillis() {
        return this.i;
    }

    public ASN1Integer getSeconds() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1Integer aSN1Integer = this.h;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.i));
        }
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.j));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
