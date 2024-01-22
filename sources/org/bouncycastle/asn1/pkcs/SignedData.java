package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes12.dex */
public class SignedData extends ASN1Object implements PKCSObjectIdentifiers {
    public ASN1Integer h;
    public ASN1Set i;
    public ContentInfo j;
    public ASN1Set k;
    public ASN1Set l;
    public ASN1Set m;

    public SignedData(ASN1Integer aSN1Integer, ASN1Set aSN1Set, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.h = aSN1Integer;
        this.i = aSN1Set;
        this.j = contentInfo;
        this.k = aSN1Set2;
        this.l = aSN1Set3;
        this.m = aSN1Set4;
    }

    public SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.h = (ASN1Integer) objects.nextElement();
        this.i = (ASN1Set) objects.nextElement();
        this.j = ContentInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive) objects.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.k = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 1) {
                    throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.getTagNo());
                } else {
                    this.l = ASN1Set.getInstance(aSN1TaggedObject, false);
                }
            } else {
                this.m = (ASN1Set) aSN1Primitive;
            }
        }
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

    public ASN1Set getCRLs() {
        return this.l;
    }

    public ASN1Set getCertificates() {
        return this.k;
    }

    public ContentInfo getContentInfo() {
        return this.j;
    }

    public ASN1Set getDigestAlgorithms() {
        return this.i;
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
        if (this.k != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.k));
        }
        if (this.l != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.l));
        }
        aSN1EncodableVector.add(this.m);
        return new BERSequence(aSN1EncodableVector);
    }
}
