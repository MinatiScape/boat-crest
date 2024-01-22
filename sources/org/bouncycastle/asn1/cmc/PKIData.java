package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
/* loaded from: classes12.dex */
public class PKIData extends ASN1Object {
    public final TaggedAttribute[] h;
    public final TaggedRequest[] i;
    public final TaggedContentInfo[] j;
    public final OtherMsg[] k;

    public PKIData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("Sequence not 4 elements.");
        }
        int i = 0;
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
        this.h = new TaggedAttribute[aSN1Sequence2.size()];
        int i2 = 0;
        while (true) {
            TaggedAttribute[] taggedAttributeArr = this.h;
            if (i2 >= taggedAttributeArr.length) {
                break;
            }
            taggedAttributeArr[i2] = TaggedAttribute.getInstance(aSN1Sequence2.getObjectAt(i2));
            i2++;
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        this.i = new TaggedRequest[aSN1Sequence3.size()];
        int i3 = 0;
        while (true) {
            TaggedRequest[] taggedRequestArr = this.i;
            if (i3 >= taggedRequestArr.length) {
                break;
            }
            taggedRequestArr[i3] = TaggedRequest.getInstance(aSN1Sequence3.getObjectAt(i3));
            i3++;
        }
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        this.j = new TaggedContentInfo[aSN1Sequence4.size()];
        int i4 = 0;
        while (true) {
            TaggedContentInfo[] taggedContentInfoArr = this.j;
            if (i4 >= taggedContentInfoArr.length) {
                break;
            }
            taggedContentInfoArr[i4] = TaggedContentInfo.getInstance(aSN1Sequence4.getObjectAt(i4));
            i4++;
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.k = new OtherMsg[aSN1Sequence5.size()];
        while (true) {
            OtherMsg[] otherMsgArr = this.k;
            if (i >= otherMsgArr.length) {
                return;
            }
            otherMsgArr[i] = OtherMsg.getInstance(aSN1Sequence5.getObjectAt(i));
            i++;
        }
    }

    public PKIData(TaggedAttribute[] taggedAttributeArr, TaggedRequest[] taggedRequestArr, TaggedContentInfo[] taggedContentInfoArr, OtherMsg[] otherMsgArr) {
        this.h = taggedAttributeArr;
        this.i = taggedRequestArr;
        this.j = taggedContentInfoArr;
        this.k = otherMsgArr;
    }

    public static PKIData getInstance(Object obj) {
        if (obj instanceof PKIData) {
            return (PKIData) obj;
        }
        if (obj != null) {
            return new PKIData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public TaggedContentInfo[] getCmsSequence() {
        return this.j;
    }

    public TaggedAttribute[] getControlSequence() {
        return this.h;
    }

    public OtherMsg[] getOtherMsgSequence() {
        return this.k;
    }

    public TaggedRequest[] getReqSequence() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{new DERSequence(this.h), new DERSequence(this.i), new DERSequence(this.j), new DERSequence(this.k)});
    }
}
