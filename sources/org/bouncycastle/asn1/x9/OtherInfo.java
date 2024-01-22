package org.bouncycastle.asn1.x9;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class OtherInfo extends ASN1Object {
    public KeySpecificInfo h;
    public ASN1OctetString i;
    public ASN1OctetString j;

    public OtherInfo(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = KeySpecificInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.i = (ASN1OctetString) aSN1TaggedObject.getObject();
            } else if (aSN1TaggedObject.getTagNo() == 2) {
                this.j = (ASN1OctetString) aSN1TaggedObject.getObject();
            }
        }
    }

    public OtherInfo(KeySpecificInfo keySpecificInfo, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2) {
        this.h = keySpecificInfo;
        this.i = aSN1OctetString;
        this.j = aSN1OctetString2;
    }

    public static OtherInfo getInstance(Object obj) {
        if (obj instanceof OtherInfo) {
            return (OtherInfo) obj;
        }
        if (obj != null) {
            return new OtherInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public KeySpecificInfo getKeyInfo() {
        return this.h;
    }

    public ASN1OctetString getPartyAInfo() {
        return this.i;
    }

    public ASN1OctetString getSuppPubInfo() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.i));
        }
        aSN1EncodableVector.add(new DERTaggedObject(2, this.j));
        return new DERSequence(aSN1EncodableVector);
    }
}
