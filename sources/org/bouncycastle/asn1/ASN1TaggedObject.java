package org.bouncycastle.asn1;

import java.io.IOException;
/* loaded from: classes12.dex */
public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    public int h;
    public boolean i = false;
    public boolean j;
    public ASN1Encodable k;

    public ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        this.j = true;
        this.k = null;
        if (aSN1Encodable instanceof ASN1Choice) {
            this.j = true;
        } else {
            this.j = z;
        }
        this.h = i;
        if (!this.j) {
            boolean z2 = aSN1Encodable.toASN1Primitive() instanceof ASN1Set;
        }
        this.k = aSN1Encodable;
    }

    public static ASN1TaggedObject getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        try {
            return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
        } catch (IOException e) {
            throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
        }
    }

    public static ASN1TaggedObject getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return (ASN1TaggedObject) aSN1TaggedObject.getObject();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            if (this.h == aSN1TaggedObject.h && this.i == aSN1TaggedObject.i && this.j == aSN1TaggedObject.j) {
                ASN1Encodable aSN1Encodable = this.k;
                return aSN1Encodable == null ? aSN1TaggedObject.k == null : aSN1Encodable.toASN1Primitive().equals(aSN1TaggedObject.k.toASN1Primitive());
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive b() {
        return new DERTaggedObject(this.j, this.h, this.k);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive c() {
        return new DLTaggedObject(this.j, this.h, this.k);
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    public ASN1Primitive getObject() {
        ASN1Encodable aSN1Encodable = this.k;
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive();
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int i, boolean z) throws IOException {
        if (i != 4) {
            if (i != 16) {
                if (i != 17) {
                    if (z) {
                        return getObject();
                    }
                    throw new ASN1Exception("implicit tagging not implemented for tag: " + i);
                }
                return ASN1Set.getInstance(this, z).parser();
            }
            return ASN1Sequence.getInstance(this, z).parser();
        }
        return ASN1OctetString.getInstance(this, z).parser();
    }

    @Override // org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        int i = this.h;
        ASN1Encodable aSN1Encodable = this.k;
        return aSN1Encodable != null ? i ^ aSN1Encodable.hashCode() : i;
    }

    public boolean isEmpty() {
        return this.i;
    }

    public boolean isExplicit() {
        return this.j;
    }

    public String toString() {
        return "[" + this.h + "]" + this.k;
    }
}
