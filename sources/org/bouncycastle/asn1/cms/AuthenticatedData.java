package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes12.dex */
public class AuthenticatedData extends ASN1Object {
    public ASN1Integer h;
    public OriginatorInfo i;
    public ASN1Set j;
    public AlgorithmIdentifier k;
    public AlgorithmIdentifier l;
    public ContentInfo m;
    public ASN1Set n;
    public ASN1OctetString o;
    public ASN1Set p;

    public AuthenticatedData(ASN1Sequence aSN1Sequence) {
        this.h = (ASN1Integer) aSN1Sequence.getObjectAt(0);
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(1);
        int i = 2;
        if (objectAt instanceof ASN1TaggedObject) {
            this.i = OriginatorInfo.getInstance((ASN1TaggedObject) objectAt, false);
            i = 3;
            objectAt = aSN1Sequence.getObjectAt(2);
        }
        this.j = ASN1Set.getInstance(objectAt);
        int i2 = i + 1;
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i));
        int i3 = i2 + 1;
        ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(i2);
        if (objectAt2 instanceof ASN1TaggedObject) {
            this.l = AlgorithmIdentifier.getInstance((ASN1TaggedObject) objectAt2, false);
            int i4 = i3 + 1;
            ASN1Encodable objectAt3 = aSN1Sequence.getObjectAt(i3);
            i3 = i4;
            objectAt2 = objectAt3;
        }
        this.m = ContentInfo.getInstance(objectAt2);
        int i5 = i3 + 1;
        ASN1Encodable objectAt4 = aSN1Sequence.getObjectAt(i3);
        if (objectAt4 instanceof ASN1TaggedObject) {
            this.n = ASN1Set.getInstance((ASN1TaggedObject) objectAt4, false);
            objectAt4 = aSN1Sequence.getObjectAt(i5);
            i5++;
        }
        this.o = ASN1OctetString.getInstance(objectAt4);
        if (aSN1Sequence.size() > i5) {
            this.p = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i5), false);
        }
    }

    public AuthenticatedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, ContentInfo contentInfo, ASN1Set aSN1Set2, ASN1OctetString aSN1OctetString, ASN1Set aSN1Set3) {
        if (!(algorithmIdentifier2 == null && aSN1Set2 == null) && (algorithmIdentifier2 == null || aSN1Set2 == null)) {
            throw new IllegalArgumentException("digestAlgorithm and authAttrs must be set together");
        }
        this.h = new ASN1Integer(calculateVersion(originatorInfo));
        this.i = originatorInfo;
        this.k = algorithmIdentifier;
        this.l = algorithmIdentifier2;
        this.j = aSN1Set;
        this.m = contentInfo;
        this.n = aSN1Set2;
        this.o = aSN1OctetString;
        this.p = aSN1Set3;
    }

    public static int calculateVersion(OriginatorInfo originatorInfo) {
        int i = 0;
        if (originatorInfo == null) {
            return 0;
        }
        Enumeration objects = originatorInfo.getCertificates().getObjects();
        while (true) {
            if (!objects.hasMoreElements()) {
                break;
            }
            Object nextElement = objects.nextElement();
            if (nextElement instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) nextElement;
                if (aSN1TaggedObject.getTagNo() == 2) {
                    i = 1;
                } else if (aSN1TaggedObject.getTagNo() == 3) {
                    i = 3;
                    break;
                }
            }
        }
        if (originatorInfo.getCRLs() != null) {
            Enumeration objects2 = originatorInfo.getCRLs().getObjects();
            while (objects2.hasMoreElements()) {
                Object nextElement2 = objects2.nextElement();
                if ((nextElement2 instanceof ASN1TaggedObject) && ((ASN1TaggedObject) nextElement2).getTagNo() == 1) {
                    return 3;
                }
            }
        }
        return i;
    }

    public static AuthenticatedData getInstance(Object obj) {
        if (obj instanceof AuthenticatedData) {
            return (AuthenticatedData) obj;
        }
        if (obj != null) {
            return new AuthenticatedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static AuthenticatedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Set getAuthAttrs() {
        return this.n;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.l;
    }

    public ContentInfo getEncapsulatedContentInfo() {
        return this.m;
    }

    public ASN1OctetString getMac() {
        return this.o;
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.k;
    }

    public OriginatorInfo getOriginatorInfo() {
        return this.i;
    }

    public ASN1Set getRecipientInfos() {
        return this.j;
    }

    public ASN1Set getUnauthAttrs() {
        return this.p;
    }

    public ASN1Integer getVersion() {
        return this.h;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.h);
        if (this.i != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.i));
        }
        aSN1EncodableVector.add(this.j);
        aSN1EncodableVector.add(this.k);
        if (this.l != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.l));
        }
        aSN1EncodableVector.add(this.m);
        if (this.n != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.n));
        }
        aSN1EncodableVector.add(this.o);
        if (this.p != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, this.p));
        }
        return new BERSequence(aSN1EncodableVector);
    }
}
