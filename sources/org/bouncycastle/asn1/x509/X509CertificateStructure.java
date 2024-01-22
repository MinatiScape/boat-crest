package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
/* loaded from: classes12.dex */
public class X509CertificateStructure extends ASN1Object implements X509ObjectIdentifiers, PKCSObjectIdentifiers {
    public ASN1Sequence h;
    public TBSCertificateStructure i;
    public AlgorithmIdentifier j;
    public DERBitString k;

    public X509CertificateStructure(ASN1Sequence aSN1Sequence) {
        this.h = aSN1Sequence;
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.i = TBSCertificateStructure.getInstance(aSN1Sequence.getObjectAt(0));
        this.j = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.k = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static X509CertificateStructure getInstance(Object obj) {
        if (obj instanceof X509CertificateStructure) {
            return (X509CertificateStructure) obj;
        }
        if (obj != null) {
            return new X509CertificateStructure(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static X509CertificateStructure getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
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

    public TBSCertificateStructure getTBSCertificate() {
        return this.i;
    }

    public int getVersion() {
        return this.i.getVersion();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.h;
    }
}
