package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
/* loaded from: classes12.dex */
public class EncryptedData extends ASN1Object {
    public ASN1Integer h;
    public EncryptedContentInfo i;
    public ASN1Set j;

    public EncryptedData(ASN1Sequence aSN1Sequence) {
        this.h = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.i = EncryptedContentInfo.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 3) {
            this.j = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(2), false);
        }
    }

    public EncryptedData(EncryptedContentInfo encryptedContentInfo) {
        this(encryptedContentInfo, null);
    }

    public EncryptedData(EncryptedContentInfo encryptedContentInfo, ASN1Set aSN1Set) {
        this.h = new ASN1Integer(aSN1Set == null ? 0L : 2L);
        this.i = encryptedContentInfo;
        this.j = aSN1Set;
    }

    public static EncryptedData getInstance(Object obj) {
        if (obj instanceof EncryptedData) {
            return (EncryptedData) obj;
        }
        if (obj != null) {
            return new EncryptedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public EncryptedContentInfo getEncryptedContentInfo() {
        return this.i;
    }

    public ASN1Set getUnprotectedAttrs() {
        return this.j;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        ASN1Set aSN1Set = this.j;
        if (aSN1Set != null) {
            aSN1EncodableVector.add(new BERTaggedObject(false, 1, aSN1Set));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
