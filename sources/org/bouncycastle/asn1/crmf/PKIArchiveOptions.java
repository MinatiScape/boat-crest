package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class PKIArchiveOptions extends ASN1Object implements ASN1Choice {
    public static final int archiveRemGenPrivKey = 2;
    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;
    public ASN1Encodable h;

    public PKIArchiveOptions(ASN1OctetString aSN1OctetString) {
        this.h = aSN1OctetString;
    }

    public PKIArchiveOptions(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable encryptedKey;
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo == 0) {
            encryptedKey = EncryptedKey.getInstance(aSN1TaggedObject.getObject());
        } else if (tagNo == 1) {
            encryptedKey = ASN1OctetString.getInstance(aSN1TaggedObject, false);
        } else if (tagNo != 2) {
            throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
        } else {
            encryptedKey = ASN1Boolean.getInstance(aSN1TaggedObject, false);
        }
        this.h = encryptedKey;
    }

    public PKIArchiveOptions(EncryptedKey encryptedKey) {
        this.h = encryptedKey;
    }

    public PKIArchiveOptions(boolean z) {
        this.h = ASN1Boolean.getInstance(z);
    }

    public static PKIArchiveOptions getInstance(Object obj) {
        if (obj == null || (obj instanceof PKIArchiveOptions)) {
            return (PKIArchiveOptions) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new PKIArchiveOptions((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object: " + obj);
    }

    public int getType() {
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable instanceof EncryptedKey) {
            return 0;
        }
        return aSN1Encodable instanceof ASN1OctetString ? 1 : 2;
    }

    public ASN1Encodable getValue() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1Encodable aSN1Encodable = this.h;
        return aSN1Encodable instanceof EncryptedKey ? new DERTaggedObject(true, 0, this.h) : aSN1Encodable instanceof ASN1OctetString ? new DERTaggedObject(false, 1, this.h) : new DERTaggedObject(false, 2, this.h);
    }
}