package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class RecipientInfo extends ASN1Object implements ASN1Choice {
    public ASN1Encodable h;

    public RecipientInfo(ASN1Primitive aSN1Primitive) {
        this.h = aSN1Primitive;
    }

    public RecipientInfo(KEKRecipientInfo kEKRecipientInfo) {
        this.h = new DERTaggedObject(false, 2, kEKRecipientInfo);
    }

    public RecipientInfo(KeyAgreeRecipientInfo keyAgreeRecipientInfo) {
        this.h = new DERTaggedObject(false, 1, keyAgreeRecipientInfo);
    }

    public RecipientInfo(KeyTransRecipientInfo keyTransRecipientInfo) {
        this.h = keyTransRecipientInfo;
    }

    public RecipientInfo(OtherRecipientInfo otherRecipientInfo) {
        this.h = new DERTaggedObject(false, 4, otherRecipientInfo);
    }

    public RecipientInfo(PasswordRecipientInfo passwordRecipientInfo) {
        this.h = new DERTaggedObject(false, 3, passwordRecipientInfo);
    }

    public static RecipientInfo getInstance(Object obj) {
        if (obj == null || (obj instanceof RecipientInfo)) {
            return (RecipientInfo) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new RecipientInfo((ASN1Sequence) obj);
        }
        if (obj instanceof ASN1TaggedObject) {
            return new RecipientInfo((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public final KEKRecipientInfo a(ASN1TaggedObject aSN1TaggedObject) {
        return KEKRecipientInfo.getInstance(aSN1TaggedObject, aSN1TaggedObject.isExplicit());
    }

    public ASN1Encodable getInfo() {
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo != 1) {
                if (tagNo != 2) {
                    if (tagNo != 3) {
                        if (tagNo == 4) {
                            return OtherRecipientInfo.getInstance(aSN1TaggedObject, false);
                        }
                        throw new IllegalStateException("unknown tag");
                    }
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false);
                }
                return a(aSN1TaggedObject);
            }
            return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false);
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable);
    }

    public ASN1Integer getVersion() {
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo != 1) {
                if (tagNo != 2) {
                    if (tagNo != 3) {
                        if (tagNo == 4) {
                            return new ASN1Integer(0L);
                        }
                        throw new IllegalStateException("unknown tag");
                    }
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
                }
                return a(aSN1TaggedObject).getVersion();
            }
            return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable).getVersion();
    }

    public boolean isTagged() {
        return this.h instanceof ASN1TaggedObject;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h.toASN1Primitive();
    }
}
