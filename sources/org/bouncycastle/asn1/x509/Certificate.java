package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class Certificate extends ASN1Object {
    public ASN1Sequence h;
    public TBSCertificate i;
    public AlgorithmIdentifier j;
    public DERBitString k;

    public Certificate(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.i = TBSCertificate.getInstance(aSN1Sequence.getObjectAt(0));
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.k = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static Certificate getInstance(Object obj) {
        if (obj instanceof Certificate) {
            return (Certificate) obj;
        }
        if (obj != null) {
            return new Certificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Certificate getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Time getEndDate() {
        return this.i.getEndDate();
    }

    public X500Name getIssuer() {
        return this.i.getIssuer();
    }

    public ASN1Integer getSerialNumber() {
        return this.i.getSerialNumber();
    }

    public DERBitString getSignature() {
        return this.k;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.j;
    }

    public Time getStartDate() {
        return this.i.getStartDate();
    }

    public X500Name getSubject() {
        return this.i.getSubject();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.i.getSubjectPublicKeyInfo();
    }

    public TBSCertificate getTBSCertificate() {
        return this.i;
    }

    public ASN1Integer getVersion() {
        return this.i.getVersion();
    }

    public int getVersionNumber() {
        return this.i.getVersionNumber();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
