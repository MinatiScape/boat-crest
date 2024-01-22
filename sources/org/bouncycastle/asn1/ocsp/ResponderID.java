package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class ResponderID extends ASN1Object implements ASN1Choice {
    public ASN1Encodable h;

    public ResponderID(ASN1OctetString aSN1OctetString) {
        this.h = aSN1OctetString;
    }

    public ResponderID(X500Name x500Name) {
        this.h = x500Name;
    }

    public static ResponderID getInstance(Object obj) {
        if (obj instanceof ResponderID) {
            return (ResponderID) obj;
        }
        if (obj instanceof DEROctetString) {
            return new ResponderID((DEROctetString) obj);
        }
        if (obj instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
            return aSN1TaggedObject.getTagNo() == 1 ? new ResponderID(X500Name.getInstance(aSN1TaggedObject, true)) : new ResponderID(ASN1OctetString.getInstance(aSN1TaggedObject, true));
        }
        return new ResponderID(X500Name.getInstance(obj));
    }

    public static ResponderID getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public byte[] getKeyHash() {
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable instanceof ASN1OctetString) {
            return ((ASN1OctetString) aSN1Encodable).getOctets();
        }
        return null;
    }

    public X500Name getName() {
        ASN1Encodable aSN1Encodable = this.h;
        if (aSN1Encodable instanceof ASN1OctetString) {
            return null;
        }
        return X500Name.getInstance(aSN1Encodable);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h instanceof ASN1OctetString ? new DERTaggedObject(true, 2, this.h) : new DERTaggedObject(true, 1, this.h);
    }
}
