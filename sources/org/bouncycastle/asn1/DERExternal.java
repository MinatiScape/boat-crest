package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/* loaded from: classes12.dex */
public class DERExternal extends ASN1Primitive {
    public ASN1ObjectIdentifier h;
    public ASN1Integer i;
    public ASN1Primitive j;
    public int k;
    public ASN1Primitive l;

    public DERExternal(ASN1EncodableVector aSN1EncodableVector) {
        int i = 0;
        ASN1Primitive d = d(aSN1EncodableVector, 0);
        if (d instanceof ASN1ObjectIdentifier) {
            this.h = (ASN1ObjectIdentifier) d;
            d = d(aSN1EncodableVector, 1);
            i = 1;
        }
        if (d instanceof ASN1Integer) {
            this.i = (ASN1Integer) d;
            i++;
            d = d(aSN1EncodableVector, i);
        }
        if (!(d instanceof ASN1TaggedObject)) {
            this.j = d;
            i++;
            d = d(aSN1EncodableVector, i);
        }
        if (aSN1EncodableVector.size() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        }
        if (!(d instanceof ASN1TaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) d;
        g(aSN1TaggedObject.getTagNo());
        this.l = aSN1TaggedObject.getObject();
    }

    public DERExternal(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, int i, ASN1Primitive aSN1Primitive2) {
        f(aSN1ObjectIdentifier);
        i(aSN1Integer);
        e(aSN1Primitive);
        g(i);
        h(aSN1Primitive2.toASN1Primitive());
    }

    public DERExternal(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, DERTaggedObject dERTaggedObject) {
        this(aSN1ObjectIdentifier, aSN1Integer, aSN1Primitive, dERTaggedObject.getTagNo(), dERTaggedObject.toASN1Primitive());
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int a() throws IOException {
        return getEncoded().length;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        ASN1Primitive aSN1Primitive2;
        ASN1Integer aSN1Integer;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        if (aSN1Primitive instanceof DERExternal) {
            if (this == aSN1Primitive) {
                return true;
            }
            DERExternal dERExternal = (DERExternal) aSN1Primitive;
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = this.h;
            if (aSN1ObjectIdentifier2 == null || ((aSN1ObjectIdentifier = dERExternal.h) != null && aSN1ObjectIdentifier.equals(aSN1ObjectIdentifier2))) {
                ASN1Integer aSN1Integer2 = this.i;
                if (aSN1Integer2 == null || ((aSN1Integer = dERExternal.i) != null && aSN1Integer.equals(aSN1Integer2))) {
                    ASN1Primitive aSN1Primitive3 = this.j;
                    if (aSN1Primitive3 == null || ((aSN1Primitive2 = dERExternal.j) != null && aSN1Primitive2.equals(aSN1Primitive3))) {
                        return this.l.equals(dERExternal.l);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final ASN1Primitive d(ASN1EncodableVector aSN1EncodableVector, int i) {
        if (aSN1EncodableVector.size() > i) {
            return aSN1EncodableVector.get(i).toASN1Primitive();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public final void e(ASN1Primitive aSN1Primitive) {
        this.j = aSN1Primitive;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.h;
        if (aSN1ObjectIdentifier != null) {
            byteArrayOutputStream.write(aSN1ObjectIdentifier.getEncoded(ASN1Encoding.DER));
        }
        ASN1Integer aSN1Integer = this.i;
        if (aSN1Integer != null) {
            byteArrayOutputStream.write(aSN1Integer.getEncoded(ASN1Encoding.DER));
        }
        ASN1Primitive aSN1Primitive = this.j;
        if (aSN1Primitive != null) {
            byteArrayOutputStream.write(aSN1Primitive.getEncoded(ASN1Encoding.DER));
        }
        byteArrayOutputStream.write(new DERTaggedObject(true, this.k, this.l).getEncoded(ASN1Encoding.DER));
        aSN1OutputStream.f(32, 8, byteArrayOutputStream.toByteArray());
    }

    public final void f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.h = aSN1ObjectIdentifier;
    }

    public final void g(int i) {
        if (i >= 0 && i <= 2) {
            this.k = i;
            return;
        }
        throw new IllegalArgumentException("invalid encoding value: " + i);
    }

    public ASN1Primitive getDataValueDescriptor() {
        return this.j;
    }

    public ASN1ObjectIdentifier getDirectReference() {
        return this.h;
    }

    public int getEncoding() {
        return this.k;
    }

    public ASN1Primitive getExternalContent() {
        return this.l;
    }

    public ASN1Integer getIndirectReference() {
        return this.i;
    }

    public final void h(ASN1Primitive aSN1Primitive) {
        this.l = aSN1Primitive;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.h;
        int hashCode = aSN1ObjectIdentifier != null ? aSN1ObjectIdentifier.hashCode() : 0;
        ASN1Integer aSN1Integer = this.i;
        if (aSN1Integer != null) {
            hashCode ^= aSN1Integer.hashCode();
        }
        ASN1Primitive aSN1Primitive = this.j;
        if (aSN1Primitive != null) {
            hashCode ^= aSN1Primitive.hashCode();
        }
        return hashCode ^ this.l.hashCode();
    }

    public final void i(ASN1Integer aSN1Integer) {
        this.i = aSN1Integer;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }
}
