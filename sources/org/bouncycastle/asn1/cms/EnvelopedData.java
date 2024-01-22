package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class EnvelopedData extends ASN1Object {
    public ASN1Integer h;
    public OriginatorInfo i;
    public ASN1Set j;
    public EncryptedContentInfo k;
    public ASN1Set l;

    public EnvelopedData(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(1);
        int i = 2;
        if (objectAt instanceof ASN1TaggedObject) {
            this.i = OriginatorInfo.getInstance((ASN1TaggedObject) objectAt, false);
            i = 3;
            objectAt = aSN1Sequence.getObjectAt(2);
        }
        this.j = ASN1Set.getInstance(objectAt);
        int i2 = i + 1;
        this.k = EncryptedContentInfo.getInstance(aSN1Sequence.getObjectAt(i));
        if (aSN1Sequence.size() > i2) {
            this.l = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i2), false);
        }
    }

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, ASN1Set aSN1Set2) {
        this.h = new ASN1Integer(calculateVersion(originatorInfo, aSN1Set, aSN1Set2));
        this.i = originatorInfo;
        this.j = aSN1Set;
        this.k = encryptedContentInfo;
        this.l = aSN1Set2;
    }

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, Attributes attributes) {
        this.h = new ASN1Integer(calculateVersion(originatorInfo, aSN1Set, ASN1Set.getInstance(attributes)));
        this.i = originatorInfo;
        this.j = aSN1Set;
        this.k = encryptedContentInfo;
        this.l = ASN1Set.getInstance(attributes);
    }

    public static int calculateVersion(OriginatorInfo originatorInfo, ASN1Set aSN1Set, ASN1Set aSN1Set2) {
        if (originatorInfo == null && aSN1Set2 == null) {
            Enumeration objects = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                if (RecipientInfo.getInstance(objects.nextElement()).getVersion().getValue().intValue() != 0) {
                    return 2;
                }
            }
            return 0;
        }
        return 2;
    }

    public static EnvelopedData getInstance(Object obj) {
        if (obj instanceof EnvelopedData) {
            return (EnvelopedData) obj;
        }
        if (obj != null) {
            return new EnvelopedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static EnvelopedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public EncryptedContentInfo getEncryptedContentInfo() {
        return this.k;
    }

    public OriginatorInfo getOriginatorInfo() {
        return this.i;
    }

    public ASN1Set getRecipientInfos() {
        return this.j;
    }

    public ASN1Set getUnprotectedAttrs() {
        return this.l;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.i));
        }
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        if (this.l != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.l));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
