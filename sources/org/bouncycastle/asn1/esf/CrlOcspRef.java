package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class CrlOcspRef extends ASN1Object {
    public CrlListID h;
    public OcspListID i;
    public OtherRevRefs j;

    public CrlOcspRef(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.h = CrlListID.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo == 1) {
                this.i = OcspListID.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("illegal tag");
            } else {
                this.j = OtherRevRefs.getInstance(aSN1TaggedObject.getObject());
            }
        }
    }

    public CrlOcspRef(CrlListID crlListID, OcspListID ocspListID, OtherRevRefs otherRevRefs) {
        this.h = crlListID;
        this.i = ocspListID;
        this.j = otherRevRefs;
    }

    public static CrlOcspRef getInstance(Object obj) {
        if (obj instanceof CrlOcspRef) {
            return (CrlOcspRef) obj;
        }
        if (obj != null) {
            return new CrlOcspRef(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CrlListID getCrlids() {
        return this.h;
    }

    public OcspListID getOcspids() {
        return this.i;
    }

    public OtherRevRefs getOtherRev() {
        return this.j;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.h != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.h.toASN1Primitive()));
        }
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.i.toASN1Primitive()));
        }
        if (this.j != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.j.toASN1Primitive()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
