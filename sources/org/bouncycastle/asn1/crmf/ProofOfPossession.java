package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class ProofOfPossession extends ASN1Object implements ASN1Choice {
    public static final int TYPE_KEY_AGREEMENT = 3;
    public static final int TYPE_KEY_ENCIPHERMENT = 2;
    public static final int TYPE_RA_VERIFIED = 0;
    public static final int TYPE_SIGNING_KEY = 1;
    public int h;
    public ASN1Encodable i;

    public ProofOfPossession() {
        this.h = 0;
        this.i = DERNull.INSTANCE;
    }

    public ProofOfPossession(int i, POPOPrivKey pOPOPrivKey) {
        this.h = i;
        this.i = pOPOPrivKey;
    }

    public ProofOfPossession(ASN1TaggedObject aSN1TaggedObject) {
        ASN1Encodable aSN1Encodable;
        int tagNo = aSN1TaggedObject.getTagNo();
        this.h = tagNo;
        if (tagNo == 0) {
            aSN1Encodable = DERNull.INSTANCE;
        } else if (tagNo == 1) {
            aSN1Encodable = POPOSigningKey.getInstance(aSN1TaggedObject, false);
        } else if (tagNo != 2 && tagNo != 3) {
            throw new IllegalArgumentException("unknown tag: " + this.h);
        } else {
            aSN1Encodable = POPOPrivKey.getInstance(aSN1TaggedObject, true);
        }
        this.i = aSN1Encodable;
    }

    public ProofOfPossession(POPOSigningKey pOPOSigningKey) {
        this.h = 1;
        this.i = pOPOSigningKey;
    }

    public static ProofOfPossession getInstance(Object obj) {
        if (obj == null || (obj instanceof ProofOfPossession)) {
            return (ProofOfPossession) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new ProofOfPossession((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("Invalid object: " + obj.getClass().getName());
    }

    public ASN1Encodable getObject() {
        return this.i;
    }

    public int getType() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.h, this.i);
    }
}
