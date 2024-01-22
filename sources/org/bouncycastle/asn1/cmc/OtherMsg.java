package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class OtherMsg extends ASN1Object {
    public final BodyPartID h;
    public final ASN1ObjectIdentifier i;
    public final ASN1Encodable j;

    public OtherMsg(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.h = BodyPartID.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.j = aSN1Sequence.getObjectAt(2);
    }

    public OtherMsg(BodyPartID bodyPartID, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.h = bodyPartID;
        this.i = aSN1ObjectIdentifier;
        this.j = aSN1Encodable;
    }

    public static OtherMsg getInstance(Object obj) {
        if (obj instanceof OtherMsg) {
            return (OtherMsg) obj;
        }
        if (obj != null) {
            return new OtherMsg(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static OtherMsg getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BodyPartID getBodyPartID() {
        return this.h;
    }

    public ASN1ObjectIdentifier getOtherMsgType() {
        return this.i;
    }

    public ASN1Encodable getOtherMsgValue() {
        return this.j;
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
