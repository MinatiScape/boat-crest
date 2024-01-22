package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;
/* loaded from: classes12.dex */
public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {
    public Vector h;
    public boolean i;

    /* loaded from: classes12.dex */
    public class a implements ASN1SetParser {
        public final int h;
        public int i;
        public final /* synthetic */ ASN1Set j;

        public a(ASN1Set aSN1Set) {
            this.j = aSN1Set;
            this.h = ASN1Set.this.size();
        }

        @Override // org.bouncycastle.asn1.InMemoryRepresentable
        public ASN1Primitive getLoadedObject() {
            return this.j;
        }

        @Override // org.bouncycastle.asn1.ASN1SetParser
        public ASN1Encodable readObject() throws IOException {
            int i = this.i;
            if (i == this.h) {
                return null;
            }
            ASN1Set aSN1Set = ASN1Set.this;
            this.i = i + 1;
            ASN1Encodable objectAt = aSN1Set.getObjectAt(i);
            return objectAt instanceof ASN1Sequence ? ((ASN1Sequence) objectAt).parser() : objectAt instanceof ASN1Set ? ((ASN1Set) objectAt).parser() : objectAt;
        }

        @Override // org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            return this.j;
        }
    }

    public ASN1Set() {
        this.h = new Vector();
        this.i = false;
    }

    public ASN1Set(ASN1Encodable aSN1Encodable) {
        Vector vector = new Vector();
        this.h = vector;
        this.i = false;
        vector.addElement(aSN1Encodable);
    }

    public ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        this.h = new Vector();
        this.i = false;
        for (int i = 0; i != aSN1EncodableVector.size(); i++) {
            this.h.addElement(aSN1EncodableVector.get(i));
        }
        if (z) {
            sort();
        }
    }

    public ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        this.h = new Vector();
        this.i = false;
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            this.h.addElement(aSN1EncodableArr[i]);
        }
        if (z) {
            sort();
        }
    }

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return getInstance(((ASN1SetParser) obj).toASN1Primitive());
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1Set) {
                return (ASN1Set) aSN1Primitive;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Set getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.isExplicit()) {
                return (ASN1Set) aSN1TaggedObject.getObject();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (aSN1TaggedObject.isExplicit()) {
            return aSN1TaggedObject instanceof BERTaggedObject ? new BERSet(object) : new DLSet(object);
        } else if (object instanceof ASN1Set) {
            return (ASN1Set) object;
        } else {
            if (object instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) object;
                return aSN1TaggedObject instanceof BERTaggedObject ? new BERSet(aSN1Sequence.toArray()) : new DLSet(aSN1Sequence.toArray());
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Set) {
            ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
            if (size() != aSN1Set.size()) {
                return false;
            }
            Enumeration objects = getObjects();
            Enumeration objects2 = aSN1Set.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Encodable e = e(objects);
                ASN1Encodable e2 = e(objects2);
                ASN1Primitive aSN1Primitive2 = e.toASN1Primitive();
                ASN1Primitive aSN1Primitive3 = e2.toASN1Primitive();
                if (aSN1Primitive2 != aSN1Primitive3 && !aSN1Primitive2.equals(aSN1Primitive3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive b() {
        if (this.i) {
            DERSet dERSet = new DERSet();
            dERSet.h = this.h;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.h.size(); i++) {
            vector.addElement(this.h.elementAt(i));
        }
        DERSet dERSet2 = new DERSet();
        dERSet2.h = vector;
        dERSet2.sort();
        return dERSet2;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive c() {
        DLSet dLSet = new DLSet();
        dLSet.h = this.h;
        return dLSet;
    }

    public final byte[] d(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public final ASN1Encodable e(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable) enumeration.nextElement();
        return aSN1Encodable == null ? DERNull.INSTANCE : aSN1Encodable;
    }

    public final boolean f(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i != min; i++) {
            if (bArr[i] != bArr2[i]) {
                return (bArr[i] & 255) < (bArr2[i] & 255);
            }
        }
        return min == bArr.length;
    }

    public ASN1Encodable getObjectAt(int i) {
        return (ASN1Encodable) this.h.elementAt(i);
    }

    public Enumeration getObjects() {
        return this.h.elements();
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        Enumeration objects = getObjects();
        int size = size();
        while (objects.hasMoreElements()) {
            size = (size * 17) ^ e(objects).hashCode();
        }
        return size;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }

    public ASN1SetParser parser() {
        return new a(this);
    }

    public int size() {
        return this.h.size();
    }

    public void sort() {
        if (this.i) {
            return;
        }
        this.i = true;
        if (this.h.size() > 1) {
            int size = this.h.size() - 1;
            boolean z = true;
            while (z) {
                int i = 0;
                byte[] d = d((ASN1Encodable) this.h.elementAt(0));
                z = false;
                int i2 = 0;
                while (i2 != size) {
                    int i3 = i2 + 1;
                    byte[] d2 = d((ASN1Encodable) this.h.elementAt(i3));
                    if (f(d, d2)) {
                        d = d2;
                    } else {
                        Object elementAt = this.h.elementAt(i2);
                        Vector vector = this.h;
                        vector.setElementAt(vector.elementAt(i3), i2);
                        this.h.setElementAt(elementAt, i3);
                        z = true;
                        i = i2;
                    }
                    i2 = i3;
                }
                size = i;
            }
        }
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[size()];
        for (int i = 0; i != size(); i++) {
            aSN1EncodableArr[i] = getObjectAt(i);
        }
        return aSN1EncodableArr;
    }

    public String toString() {
        return this.h.toString();
    }
}
