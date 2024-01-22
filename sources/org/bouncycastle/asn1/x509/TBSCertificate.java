package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class TBSCertificate extends ASN1Object {
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
    public Extensions s;

    public TBSCertificate(ASN1Sequence aSN1Sequence) {
        int i;
        boolean z;
        boolean z2;
        this.h = aSN1Sequence;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.i = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i = 0;
        } else {
            this.i = new ASN1Integer(0L);
            i = -1;
        }
        if (this.i.getValue().equals(BigInteger.valueOf(0L))) {
            z2 = false;
            z = true;
        } else if (this.i.getValue().equals(BigInteger.valueOf(1L))) {
            z = false;
            z2 = true;
        } else if (!this.i.getValue().equals(BigInteger.valueOf(2L))) {
            throw new IllegalArgumentException("version number not recognised");
        } else {
            z = false;
            z2 = false;
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
        int size = (aSN1Sequence.size() - i2) - 1;
        if (size != 0 && z) {
            throw new IllegalArgumentException("version 1 certificate contains extra data");
        }
        while (size > 0) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i2 + size);
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 1) {
                this.q = DERBitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo == 2) {
                this.r = DERBitString.getInstance(aSN1TaggedObject, false);
            } else if (tagNo != 3) {
                continue;
            } else if (z2) {
                throw new IllegalArgumentException("version 2 certificate cannot contain extensions");
            } else {
                this.s = Extensions.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, true));
            }
            size--;
        }
    }

    public static TBSCertificate getInstance(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.n;
    }

    public Extensions getExtensions() {
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

    public ASN1Integer getVersion() {
        return this.i;
    }

    public int getVersionNumber() {
        return this.i.getValue().intValue() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
