package org.bouncycastle.asn1.cms;

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
/* loaded from: classes12.dex */
public class AuthEnvelopedData extends ASN1Object {
    public ASN1Integer h;
    public OriginatorInfo i;
    public ASN1Set j;
    public EncryptedContentInfo k;
    public ASN1Set l;
    public ASN1OctetString m;
    public ASN1Set n;

    public AuthEnvelopedData(ASN1Sequence aSN1Sequence) {
        ASN1Set aSN1Set;
        ASN1Integer aSN1Integer = (ASN1Integer) aSN1Sequence.getObjectAt(0).toASN1Primitive();
        this.h = aSN1Integer;
        if (aSN1Integer.getValue().intValue() != 0) {
            throw new IllegalArgumentException("AuthEnvelopedData version number must be 0");
        }
        int i = 2;
        ASN1Primitive aSN1Primitive = aSN1Sequence.getObjectAt(1).toASN1Primitive();
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            this.i = OriginatorInfo.getInstance((ASN1TaggedObject) aSN1Primitive, false);
            aSN1Primitive = aSN1Sequence.getObjectAt(2).toASN1Primitive();
            i = 3;
        }
        ASN1Set aSN1Set2 = ASN1Set.getInstance(aSN1Primitive);
        this.j = aSN1Set2;
        if (aSN1Set2.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        int i2 = i + 1;
        this.k = EncryptedContentInfo.getInstance(aSN1Sequence.getObjectAt(i).toASN1Primitive());
        int i3 = i2 + 1;
        ASN1Primitive aSN1Primitive2 = aSN1Sequence.getObjectAt(i2).toASN1Primitive();
        if (aSN1Primitive2 instanceof ASN1TaggedObject) {
            this.l = ASN1Set.getInstance((ASN1TaggedObject) aSN1Primitive2, false);
            aSN1Primitive2 = aSN1Sequence.getObjectAt(i3).toASN1Primitive();
            i3++;
        } else if (!this.k.getContentType().equals(CMSObjectIdentifiers.data) && ((aSN1Set = this.l) == null || aSN1Set.size() == 0)) {
            throw new IllegalArgumentException("authAttrs must be present with non-data content");
        }
        this.m = ASN1OctetString.getInstance(aSN1Primitive2);
        if (aSN1Sequence.size() > i3) {
            this.n = ASN1Set.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i3).toASN1Primitive(), false);
        }
    }

    public AuthEnvelopedData(OriginatorInfo originatorInfo, ASN1Set aSN1Set, EncryptedContentInfo encryptedContentInfo, ASN1Set aSN1Set2, ASN1OctetString aSN1OctetString, ASN1Set aSN1Set3) {
        this.h = new ASN1Integer(0L);
        this.i = originatorInfo;
        this.j = aSN1Set;
        if (aSN1Set.size() == 0) {
            throw new IllegalArgumentException("AuthEnvelopedData requires at least 1 RecipientInfo");
        }
        this.k = encryptedContentInfo;
        this.l = aSN1Set2;
        if (!encryptedContentInfo.getContentType().equals(CMSObjectIdentifiers.data) && (aSN1Set2 == null || aSN1Set2.size() == 0)) {
            throw new IllegalArgumentException("authAttrs must be present with non-data content");
        }
        this.m = aSN1OctetString;
        this.n = aSN1Set3;
    }

    public static AuthEnvelopedData getInstance(Object obj) {
        if (obj == null || (obj instanceof AuthEnvelopedData)) {
            return (AuthEnvelopedData) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new AuthEnvelopedData((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid AuthEnvelopedData: " + obj.getClass().getName());
    }

    public static AuthEnvelopedData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ASN1Set getAuthAttrs() {
        return this.l;
    }

    public EncryptedContentInfo getAuthEncryptedContentInfo() {
        return this.k;
    }

    public ASN1OctetString getMac() {
        return this.m;
    }

    public OriginatorInfo getOriginatorInfo() {
        return this.i;
    }

    public ASN1Set getRecipientInfos() {
        return this.j;
    }

    public ASN1Set getUnauthAttrs() {
        return this.n;
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
        return new BERSequence(aSN1EncodableVector);
    }
}
