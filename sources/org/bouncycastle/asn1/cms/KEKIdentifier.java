package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class KEKIdentifier extends ASN1Object {
    public ASN1OctetString h;
    public ASN1GeneralizedTime i;
    public OtherKeyAttribute j;

    public KEKIdentifier(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt;
        this.h = (ASN1OctetString) aSN1Sequence.getObjectAt(0);
        int size = aSN1Sequence.size();
        if (size != 1) {
            if (size == 2) {
                boolean z = aSN1Sequence.getObjectAt(1) instanceof ASN1GeneralizedTime;
                objectAt = aSN1Sequence.getObjectAt(1);
                if (z) {
                    this.i = (ASN1GeneralizedTime) objectAt;
                    return;
                }
            } else if (size != 3) {
                throw new IllegalArgumentException("Invalid KEKIdentifier");
            } else {
                this.i = (ASN1GeneralizedTime) aSN1Sequence.getObjectAt(1);
                objectAt = aSN1Sequence.getObjectAt(2);
            }
            this.j = OtherKeyAttribute.getInstance(objectAt);
        }
    }

    public KEKIdentifier(byte[] bArr, ASN1GeneralizedTime aSN1GeneralizedTime, OtherKeyAttribute otherKeyAttribute) {
        this.h = new DEROctetString(bArr);
        this.i = aSN1GeneralizedTime;
        this.j = otherKeyAttribute;
    }

    public static KEKIdentifier getInstance(Object obj) {
        if (obj == null || (obj instanceof KEKIdentifier)) {
            return (KEKIdentifier) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new KEKIdentifier((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid KEKIdentifier: " + obj.getClass().getName());
    }

    public static KEKIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1GeneralizedTime getDate() {
        return this.i;
    }

    public ASN1OctetString getKeyIdentifier() {
        return this.h;
    }

    public OtherKeyAttribute getOther() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        ASN1GeneralizedTime aSN1GeneralizedTime = this.i;
        if (aSN1GeneralizedTime != null) {
            aSN1EncodableVector.add(aSN1GeneralizedTime);
        }
        OtherKeyAttribute otherKeyAttribute = this.j;
        if (otherKeyAttribute != null) {
            aSN1EncodableVector.add(otherKeyAttribute);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
