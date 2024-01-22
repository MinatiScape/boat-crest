package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.EnvelopedData;
/* loaded from: classes12.dex */
public class POPOPrivKey extends ASN1Object implements ASN1Choice {
    public static final int agreeMAC = 3;
    public static final int dhMAC = 2;
    public static final int encryptedKey = 4;
    public static final int subsequentMessage = 1;
    public static final int thisMessage = 0;
    public int h;
    public ASN1Encodable i;

    public POPOPrivKey(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable dERBitString;
        int tagNo = aSN1TaggedObject.getTagNo();
        this.h = tagNo;
        if (tagNo != 0) {
            if (tagNo == 1) {
                dERBitString = SubsequentMessage.valueOf(ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue());
            } else if (tagNo != 2) {
                if (tagNo == 3) {
                    dERBitString = PKMACValue.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 4) {
                    throw new IllegalArgumentException("unknown tag in POPOPrivKey");
                } else {
                    dERBitString = EnvelopedData.getInstance(aSN1TaggedObject, false);
                }
            }
            this.i = dERBitString;
        }
        dERBitString = DERBitString.getInstance(aSN1TaggedObject, false);
        this.i = dERBitString;
    }

    public POPOPrivKey(SubsequentMessage subsequentMessage2) {
        this.h = 1;
        this.i = subsequentMessage2;
    }

    public static POPOPrivKey getInstance(Object obj) {
        if (obj instanceof POPOPrivKey) {
            return (POPOPrivKey) obj;
        }
        if (obj != null) {
            return new POPOPrivKey(ASN1TaggedObject.getInstance(obj));
        }
        return null;
    }

    public static POPOPrivKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, z));
    }

    public int getType() {
        return this.h;
    }

    public ASN1Encodable getValue() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.h, this.i);
    }
}
