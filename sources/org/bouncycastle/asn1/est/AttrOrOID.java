package org.bouncycastle.asn1.est;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.Attribute;
/* loaded from: classes12.dex */
public class AttrOrOID extends ASN1Object implements ASN1Choice {
    public final ASN1ObjectIdentifier h;
    public final Attribute i;

    public AttrOrOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.h = aSN1ObjectIdentifier;
        this.i = null;
    }

    public AttrOrOID(Attribute attribute) {
        this.h = null;
        this.i = attribute;
    }

    public static AttrOrOID getInstance(Object obj) {
        if (obj instanceof AttrOrOID) {
            return (AttrOrOID) obj;
        }
        if (obj != null) {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                    return new AttrOrOID(ASN1ObjectIdentifier.getInstance(aSN1Primitive));
                }
                if (aSN1Primitive instanceof ASN1Sequence) {
                    return new AttrOrOID(Attribute.getInstance(aSN1Primitive));
                }
            }
            if (obj instanceof byte[]) {
                try {
                    return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
                } catch (IOException unused) {
                    throw new IllegalArgumentException("unknown encoding in getInstance()");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
        }
        return null;
    }

    public Attribute getAttribute() {
        return this.i;
    }

    public ASN1ObjectIdentifier getOid() {
        return this.h;
    }

    public boolean isOid() {
        return this.h != null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.h;
        return aSN1ObjectIdentifier != null ? aSN1ObjectIdentifier : this.i.toASN1Primitive();
    }
}
