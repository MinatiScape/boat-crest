package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.EnvelopedData;
/* loaded from: classes12.dex */
public class EncryptedKey extends ASN1Object implements ASN1Choice {
    public EnvelopedData h;
    public EncryptedValue i;

    public EncryptedKey(EnvelopedData envelopedData) {
        this.h = envelopedData;
    }

    public EncryptedKey(EncryptedValue encryptedValue) {
        this.i = encryptedValue;
    }

    public static EncryptedKey getInstance(Object obj) {
        return obj instanceof EncryptedKey ? (EncryptedKey) obj : obj instanceof ASN1TaggedObject ? new EncryptedKey(EnvelopedData.getInstance((ASN1TaggedObject) obj, false)) : obj instanceof EncryptedValue ? new EncryptedKey((EncryptedValue) obj) : new EncryptedKey(EncryptedValue.getInstance(obj));
    }

    public ASN1Encodable getValue() {
        EncryptedValue encryptedValue = this.i;
        return encryptedValue != null ? encryptedValue : this.h;
    }

    public boolean isEncryptedValue() {
        return this.i != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        EncryptedValue encryptedValue = this.i;
        return encryptedValue != null ? encryptedValue.toASN1Primitive() : new DERTaggedObject(false, 0, this.h);
    }
}
