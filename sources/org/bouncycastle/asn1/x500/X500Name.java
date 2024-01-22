package org.bouncycastle.asn1.x500;

import java.util.Enumeration;
import java.util.Objects;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.style.BCStyle;
/* loaded from: classes12.dex */
public class X500Name extends ASN1Object implements ASN1Choice {
    public static X500NameStyle l = BCStyle.INSTANCE;
    public boolean h;
    public int i;
    public X500NameStyle j;
    public RDN[] k;

    public X500Name(String str) {
        this(l, str);
    }

    public X500Name(ASN1Sequence aSN1Sequence) {
        this(l, aSN1Sequence);
    }

    public X500Name(X500NameStyle x500NameStyle, String str) {
        this(x500NameStyle.fromString(str));
        this.j = x500NameStyle;
    }

    public X500Name(X500NameStyle x500NameStyle, ASN1Sequence aSN1Sequence) {
        this.j = x500NameStyle;
        this.k = new RDN[aSN1Sequence.size()];
        Enumeration objects = aSN1Sequence.getObjects();
        int i = 0;
        while (objects.hasMoreElements()) {
            this.k[i] = RDN.getInstance(objects.nextElement());
            i++;
        }
    }

    public X500Name(X500NameStyle x500NameStyle, X500Name x500Name) {
        this.k = x500Name.k;
        this.j = x500NameStyle;
    }

    public X500Name(X500NameStyle x500NameStyle, RDN[] rdnArr) {
        this.k = rdnArr;
        this.j = x500NameStyle;
    }

    public X500Name(RDN[] rdnArr) {
        this(l, rdnArr);
    }

    public static X500NameStyle getDefaultStyle() {
        return l;
    }

    public static X500Name getInstance(Object obj) {
        if (obj instanceof X500Name) {
            return (X500Name) obj;
        }
        if (obj != null) {
            return new X500Name(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static X500Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
    }

    public static X500Name getInstance(X500NameStyle x500NameStyle, Object obj) {
        if (obj instanceof X500Name) {
            return new X500Name(x500NameStyle, (X500Name) obj);
        }
        if (obj != null) {
            return new X500Name(x500NameStyle, ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static void setDefaultStyle(X500NameStyle x500NameStyle) {
        Objects.requireNonNull(x500NameStyle, "cannot set style to null");
        l = x500NameStyle;
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof X500Name) || (obj instanceof ASN1Sequence)) {
            if (toASN1Primitive().equals(((ASN1Encodable) obj).toASN1Primitive())) {
                return true;
            }
            try {
                return this.j.areEqual(this, new X500Name(ASN1Sequence.getInstance(((ASN1Encodable) obj).toASN1Primitive())));
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    public ASN1ObjectIdentifier[] getAttributeTypes() {
        int i = 0;
        int i2 = 0;
        while (true) {
            RDN[] rdnArr = this.k;
            if (i == rdnArr.length) {
                break;
            }
            i2 += rdnArr[i].size();
            i++;
        }
        ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[i2];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            RDN[] rdnArr2 = this.k;
            if (i3 == rdnArr2.length) {
                return aSN1ObjectIdentifierArr;
            }
            RDN rdn = rdnArr2[i3];
            if (rdn.isMultiValued()) {
                AttributeTypeAndValue[] typesAndValues = rdn.getTypesAndValues();
                int i5 = 0;
                while (i5 != typesAndValues.length) {
                    aSN1ObjectIdentifierArr[i4] = typesAndValues[i5].getType();
                    i5++;
                    i4++;
                }
            } else if (rdn.size() != 0) {
                aSN1ObjectIdentifierArr[i4] = rdn.getFirst().getType();
                i4++;
            }
            i3++;
        }
    }

    public RDN[] getRDNs() {
        RDN[] rdnArr = this.k;
        int length = rdnArr.length;
        RDN[] rdnArr2 = new RDN[length];
        System.arraycopy(rdnArr, 0, rdnArr2, 0, length);
        return rdnArr2;
    }

    public RDN[] getRDNs(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        int i;
        RDN[] rdnArr = new RDN[this.k.length];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            RDN[] rdnArr2 = this.k;
            if (i2 == rdnArr2.length) {
                RDN[] rdnArr3 = new RDN[i3];
                System.arraycopy(rdnArr, 0, rdnArr3, 0, i3);
                return rdnArr3;
            }
            RDN rdn = rdnArr2[i2];
            if (rdn.isMultiValued()) {
                AttributeTypeAndValue[] typesAndValues = rdn.getTypesAndValues();
                for (int i4 = 0; i4 != typesAndValues.length; i4++) {
                    if (typesAndValues[i4].getType().equals(aSN1ObjectIdentifier)) {
                        i = i3 + 1;
                        rdnArr[i3] = rdn;
                        i3 = i;
                        break;
                    }
                }
                i2++;
            } else if (rdn.getFirst().getType().equals(aSN1ObjectIdentifier)) {
                i = i3 + 1;
                rdnArr[i3] = rdn;
                i3 = i;
                break;
                i2++;
            } else {
                i2++;
            }
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        if (this.h) {
            return this.i;
        }
        this.h = true;
        int calculateHashCode = this.j.calculateHashCode(this);
        this.i = calculateHashCode;
        return calculateHashCode;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.k);
    }

    public String toString() {
        return this.j.toString(this);
    }
}
