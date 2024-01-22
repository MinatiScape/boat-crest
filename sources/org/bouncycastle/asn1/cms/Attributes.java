package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DLSet;
/* loaded from: classes12.dex */
public class Attributes extends ASN1Object {
    public ASN1Set h;

    public Attributes(ASN1EncodableVector aSN1EncodableVector) {
        this.h = new DLSet(aSN1EncodableVector);
    }

    public Attributes(ASN1Set aSN1Set) {
        this.h = aSN1Set;
    }

    public static Attributes getInstance(Object obj) {
        if (obj instanceof Attributes) {
            return (Attributes) obj;
        }
        if (obj != null) {
            return new Attributes(ASN1Set.getInstance(obj));
        }
        return null;
    }

    public Attribute[] getAttributes() {
        int size = this.h.size();
        Attribute[] attributeArr = new Attribute[size];
        for (int i = 0; i != size; i++) {
            attributeArr[i] = Attribute.getInstance(this.h.getObjectAt(i));
        }
        return attributeArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
