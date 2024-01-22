package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class CertStatus extends ASN1Object implements ASN1Choice {
    public int h;
    public ASN1Encodable i;

    public CertStatus() {
        this.h = 0;
        this.i = DERNull.INSTANCE;
    }

    public CertStatus(int i, ASN1Encodable aSN1Encodable) {
        this.h = i;
        this.i = aSN1Encodable;
    }

    public CertStatus(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable aSN1Encodable;
        this.h = aSN1TaggedObject.getTagNo();
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo != 0) {
            if (tagNo == 1) {
                aSN1Encodable = RevokedInfo.getInstance(aSN1TaggedObject, false);
                this.i = aSN1Encodable;
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
            }
        }
        aSN1Encodable = DERNull.INSTANCE;
        this.i = aSN1Encodable;
    }

    public CertStatus(RevokedInfo revokedInfo) {
        this.h = 1;
        this.i = revokedInfo;
    }

    public static CertStatus getInstance(Object obj) {
        if (obj == null || (obj instanceof CertStatus)) {
            return (CertStatus) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new CertStatus((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public static CertStatus getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public ASN1Encodable getStatus() {
        return this.i;
    }

    public int getTagNo() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.h, this.i);
    }
}
