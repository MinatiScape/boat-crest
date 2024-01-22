package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class TBSCertificateStructure extends ASN1Object implements X509ObjectIdentifiers, PKCSObjectIdentifiers {
    public ASN1Sequence h;
    public ASN1Integer i;
    public ASN1Integer j;
    public AlgorithmIdentifier k;
    public X500Name l;
    public Time m;
    public Time n;
    public X500Name o;
    public SubjectPublicKeyInfo p;
    public DERBitString q;
    public DERBitString r;
    public X509Extensions s;

    public TBSCertificateStructure(ASN1Sequence aSN1Sequence) {
        int i;
        this.h = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof DERTaggedObject) {
            this.i = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i = 0;
        } else {
            this.i = new ASN1Integer(0L);
            i = -1;
        }
        this.j = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i + 1));
        this.k = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i + 2));
        this.l = X500Name.getInstance(aSN1Sequence.getObjectAt(i + 3));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(i + 4);
        this.m = Time.getInstance(aSN1Sequence2.getObjectAt(0));
        this.n = Time.getInstance(aSN1Sequence2.getObjectAt(1));
        this.o = X500Name.getInstance(aSN1Sequence.getObjectAt(i + 5));
        int i2 = i + 6;
        this.p = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(i2));
        for (int size = (aSN1Sequence.size() - i2) - 1; size > 0; size--) {
            DERTaggedObject dERTaggedObject = (DERTaggedObject) aSN1Sequence.getObjectAt(i2 + size);
            int tagNo = dERTaggedObject.getTagNo();
            if (tagNo == 1) {
                this.q = DERBitString.getInstance(dERTaggedObject, false);
            } else if (tagNo == 2) {
                this.r = DERBitString.getInstance(dERTaggedObject, false);
            } else if (tagNo == 3) {
                this.s = X509Extensions.getInstance(dERTaggedObject);
            }
        }
    }

    public static TBSCertificateStructure getInstance(Object obj) {
        if (obj instanceof TBSCertificateStructure) {
            return (TBSCertificateStructure) obj;
        }
        if (obj != null) {
            return new TBSCertificateStructure(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.n;
    }

    public X509Extensions getExtensions() {
        return this.s;
    }

    public X500Name getIssuer() {
        return this.l;
    }

    public DERBitString getIssuerUniqueId() {
        return this.q;
    }

    public ASN1Integer getSerialNumber() {
        return this.j;
    }

    public AlgorithmIdentifier getSignature() {
        return this.k;
    }

    public Time getStartDate() {
        return this.m;
    }

    public X500Name getSubject() {
        return this.o;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.p;
    }

    public DERBitString getSubjectUniqueId() {
        return this.r;
    }

    public int getVersion() {
        return this.i.getValue().intValue() + 1;
    }

    public ASN1Integer getVersionNumber() {
        return this.i;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
