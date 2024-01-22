package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class SignedData extends ASN1Object {
    public static final ASN1Integer p = new ASN1Integer(1);
    public static final ASN1Integer q = new ASN1Integer(3);
    public static final ASN1Integer r = new ASN1Integer(4);
    public static final ASN1Integer s = new ASN1Integer(5);
    public ASN1Integer h;
    public ASN1Set i;
    public ContentInfo j;
    public ASN1Set k;
    public ASN1Set l;
    public ASN1Set m;
    public boolean n;
    public boolean o;

    public SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = ASN1Integer.getInstance(objects.nextElement());
        this.i = (ASN1Set) objects.nextElement();
        this.j = ContentInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive) objects.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.n = aSN1TaggedObject instanceof BERTaggedObject;
                    this.k = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 1) {
                    throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.getTagNo());
                } else {
                    this.o = aSN1TaggedObject instanceof BERTaggedObject;
                    this.l = ASN1Set.getInstance(aSN1TaggedObject, false);
                }
            } else {
                this.m = (ASN1Set) aSN1Primitive;
            }
        }
    }

    public SignedData(ASN1Set aSN1Set, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.h = a(contentInfo.getContentType(), aSN1Set2, aSN1Set3, aSN1Set4);
        this.i = aSN1Set;
        this.j = contentInfo;
        this.k = aSN1Set2;
        this.l = aSN1Set3;
        this.m = aSN1Set4;
        this.o = aSN1Set3 instanceof BERSet;
        this.n = aSN1Set2 instanceof BERSet;
    }

    public static SignedData getInstance(Object obj) {
        if (obj instanceof SignedData) {
            return (SignedData) obj;
        }
        if (obj != null) {
            return new SignedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public final ASN1Integer a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set, ASN1Set aSN1Set2, ASN1Set aSN1Set3) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (aSN1Set != null) {
            Enumeration objects = aSN1Set.getObjects();
            z = false;
            z2 = false;
            z3 = false;
            while (objects.hasMoreElements()) {
                Object nextElement = objects.nextElement();
                if (nextElement instanceof ASN1TaggedObject) {
                    ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(nextElement);
                    if (aSN1TaggedObject.getTagNo() == 1) {
                        z2 = true;
                    } else if (aSN1TaggedObject.getTagNo() == 2) {
                        z3 = true;
                    } else if (aSN1TaggedObject.getTagNo() == 3) {
                        z = true;
                    }
                }
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (z) {
            return new ASN1Integer(5L);
        }
        if (aSN1Set2 != null) {
            Enumeration objects2 = aSN1Set2.getObjects();
            while (objects2.hasMoreElements()) {
                if (objects2.nextElement() instanceof ASN1TaggedObject) {
                    z4 = true;
                }
            }
        }
        if (z4) {
            return s;
        }
        if (z3) {
            return r;
        }
        if (!z2 && !b(aSN1Set3) && CMSObjectIdentifiers.data.equals(aSN1ObjectIdentifier)) {
            return p;
        }
        return q;
    }

    public final boolean b(ASN1Set aSN1Set) {
        Enumeration objects = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            if (SignerInfo.getInstance(objects.nextElement()).getVersion().getValue().intValue() == 3) {
                return true;
            }
        }
        return false;
    }

    public ASN1Set getCRLs() {
        return this.l;
    }

    public ASN1Set getCertificates() {
        return this.k;
    }

    public ASN1Set getDigestAlgorithms() {
        return this.i;
    }

    public ContentInfo getEncapContentInfo() {
        return this.j;
    }

    public ASN1Set getSignerInfos() {
        return this.m;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        aSN1EncodableVector.add(this.i);
        aSN1EncodableVector.add(this.j);
        ASN1Set aSN1Set = this.k;
        if (aSN1Set != null) {
            if (this.n) {
                aSN1EncodableVector.add(new BERTaggedObject(false, 0, aSN1Set));
            } else {
                aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.k));
            }
        }
        ASN1Set aSN1Set2 = this.l;
        if (aSN1Set2 != null) {
            if (this.o) {
                aSN1EncodableVector.add(new BERTaggedObject(false, 1, aSN1Set2));
            } else {
                aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.l));
            }
        }
        aSN1EncodableVector.add(this.m);
        return new BERSequence(aSN1EncodableVector);
    }
}
